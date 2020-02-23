/*
 *
 *  Copyright 2015-2016 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 */

package springfox.documentation.spring.web.scanners;

import com.google.common.base.Optional;
import com.sshs.core.version.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.Operation;
import springfox.documentation.spi.service.contexts.ApiSelector;
import springfox.documentation.spi.service.contexts.PathContext;
import springfox.documentation.spi.service.contexts.RequestMappingContext;
import springfox.documentation.spring.web.plugins.DocumentationPluginsManager;
import springfox.documentation.spring.web.readers.operation.OperationReader;

import java.util.List;

import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Ordering.natural;

@Component
public class ApiDescriptionReader {

    private static Logger log = LoggerFactory.getLogger(ApiDescriptionReader.class);
    private final OperationReader operationReader;
    private final DocumentationPluginsManager pluginsManager;
    private final ApiDescriptionLookup lookup;

    @Autowired
    public ApiDescriptionReader(
            @Autowired CachingOperationReader operationReader,
            DocumentationPluginsManager pluginsManager,
            ApiDescriptionLookup lookup) {
        this.operationReader = operationReader;
        this.pluginsManager = pluginsManager;
        this.lookup = lookup;
    }

    public List<ApiDescription> read(RequestMappingContext outerContext) {
        PatternsRequestCondition patternsCondition = outerContext.getPatternsCondition();
        ApiSelector selector = outerContext.getDocumentationContext().getApiSelector();

        List<ApiDescription> apiDescriptionList = newArrayList();
        for (String path : matchingPaths(selector, patternsCondition)) {
            String methodName = outerContext.getName();
            try {
                RequestMappingContext operationContext = outerContext.copyPatternUsing(path);

                List<Operation> operations = operationReader.read(operationContext);
                if (operations.size() > 0) {
                    String oPath = path;
                    Optional<Version> version = operationContext.findControllerAnnotation(Version.class);
                    Optional<RequestMapping> requestMapping = operationContext.findControllerAnnotation(RequestMapping.class);
                    if (version.isPresent() && requestMapping.isPresent()) {
                        int v = version.get().value();
                        for (String reqPath : requestMapping.get().path()) {
                            oPath = oPath.replace(reqPath, reqPath.replaceAll("\\{(.*?)\\}", "v" + v));
                        }
                    }
                    operationContext.apiDescriptionBuilder()
                            .groupName(outerContext.getGroupName())
                            .operations(operations)
                            .pathDecorator(pluginsManager.decorator(new PathContext(outerContext, from(operations).first())))
                            .path(oPath)
                            .description(methodName)
                            .hidden(false);
                    ApiDescription apiDescription = operationContext.apiDescriptionBuilder().build();
                    lookup.add(outerContext.key(), apiDescription);
                    apiDescriptionList.add(apiDescription);
                }
            } catch (Error e) {
                String contentMsg = "Skipping process path[" + path + "], method[" + methodName + "] as it has an error.";
                log.error(contentMsg, e);
            }
        }
        return apiDescriptionList;
    }

    private List<String> matchingPaths(ApiSelector selector, PatternsRequestCondition patternsCondition) {
        return natural().sortedCopy(from(patternsCondition.getPatterns())
                .filter(selector.getPathSelector()));
    }

}