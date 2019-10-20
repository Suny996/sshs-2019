package com.sshs.core.aop;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sshs.core.util.SystemUtil;
import com.sshs.core.util.UuidUtil;
import com.sshs.core.customise.mapper.CommonMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 系统日志：切面处理类
 *
 * @author Suny
 * @date 2019-02-21
 */
@Aspect
@Component
public class SysLogAspect {

    @Resource
    BaseMapper<SysLog> mapper;

    /**
     * 定义切点 @Pointcut
     * 在注解的位置切入代码
     */
    @Pointcut("@annotation( com.sshs.core.aop.SLog)")
    public void logPointCut() {
    }

    /**
     * 切面 配置通知
     */
    @AfterReturning("logPointCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        //System.out.println("切面。。。。。");
        //保存日志
        SysLog sysLog = new SysLog();

        sysLog.setLogId(UuidUtil.get32UUID());
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取操作
        SLog sLog = method.getAnnotation(SLog.class);
        if (sLog != null) {
            String value = sLog.value();
            //保存获取的操作
            sysLog.setAction(value);
            sysLog.setModule(sLog.module());
        }

        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getSimpleName();
        //获取请求的方法名
        String methodName = method.getName();
        sysLog.setMethod(className + "." + methodName);
        try {
            //请求的参数
            Object[] args = joinPoint.getArgs();
            //将参数所在的数组转换成json
            String params = JSON.toJSONString(args);
            sysLog.setParams(params);
        } catch (Exception e) {
            sysLog.setParams("get Params Exception!");
        }
        sysLog.setOptDate(new Date());
        try {
            //获取用户名
            sysLog.setUserCode(SystemUtil.getCurrentUser().getUserCode());
            sysLog.setUserName(SystemUtil.getCurrentUser().getUserName());
        } catch (Exception e) {
        }
        //获取用户ip地址
        sysLog.setClientIp(SystemUtil.getRemoteId());

        //调用service保存SysLog实体类到数据库
        mapper.insert(sysLog);
    }

}