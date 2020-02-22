# sshs-2019
java
1、支持Swagger，可以通过参数 sshs.swagger.enable关闭；
2、集成MybatisPlus，并扩展QueryWraper以支持根据属性查询而非字段名称查询；
3、支持session及token结合认证，token引入jwt；

4、将错误码写入到枚举类中以方便维护；
5、引入Nacos实现服务注册机配置中心功能；
6、接口bean及interface分开，方便支持分布式部署
7、加入参数验证validate
8、加入接口版本管理机制