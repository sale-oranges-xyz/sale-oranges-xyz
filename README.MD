#### 指导思想
认识HTTP响应状态: https://mp.weixin.qq.com/s/bKPCT3SivnnEXHVtAtE22A   
我理解的微服务系统: https://mp.weixin.qq.com/s/0g9oUsE6vRIpQcz4hSWXhw   
设计一个微服务用户API网关: https://mp.weixin.qq.com/s/ERxlPKjuX8nWB2awHVZrlQ   
分布式事务解决方案: http://springcloud.cn/view/374  

##### 项目结构
```
 | - admin-api-gateway 后台管理部分api网关
 | - domains 存放实体类与业务逻辑处理
    | - admin-user 微服务：后台管理中心
        | - admin-user-api 启动项目
        | - admin-user-dto 用户管理dto模块
        | - admin-user-redis 用户管理redis模块
    | - member-user 微服务: 会员中心
        | - member-user-api 启动项目
        | - member-user-dto 会员dto模块
 | - config-center 微服务: 服务配置中心
    | - config-center-api 启动项目
    | - config-center-dto 服务配置dto模块
 | - member-api-gateway  前端用户部分api网关
 | - register-center 服务注册中心
```
##### 系统模块common-*，参考 https://github.com/sale-oranges-xyz/common-backstage
##### 项目启动顺序
1. 服务注册中心 register-center
2. 启动kafka, spring-cloud-bus 依赖
3. 启动admin-user-api
4. 启动admin-user-api-gateway
##### 说明
```
2.创建启动类，需要在com.github.geng目录之前，原因是启动配置文件都是在com.github.geng.*
	当然，也可以不这样处理，但是必须使用@ComponentScans 扫描指定包名。
3.dto时间与修改时间使用时间戳，考虑到不同时区问题
4.service层定义接口原因：
    (1).service方法是模块暴露给其他模块的,修改时需要特别注意
    (2).spring有两种动态代理jdk与cglib，jdk8动态代理性能提升
5.dto(Data Transfer Object)层：api接口传输对象,controller层之间交互
6.一般初始化sql，放在resources下
7.用户部分网关与后台管理部分网关，用户验证逻辑不一致
  实际情况访问量也有很大差异，拆开 
```
##### 项目规范
```
1.spring cloud 的微服务之间调用都是通过http请求，即都是通过controller层交
  而controller层传输数据都是dto(Data Transfer Object)数据
  为了减少重复代码，应把dto层独立成单独maven模块
```
##### 项目注意点及错误整理
```
1.@Table与@Entity区别，参考 https://www.cnblogs.com/alsf/p/7822241.html
2. 使用feign 出现 PathVariable annotation was empty on param 0异常。
    参考 https://blog.csdn.net/u014519194/article/details/69392233
3. java 泛型有个难受的地方，泛型约束的内部无法直接new
    参考 http://www.cnblogs.com/beautiful-code/p/5271295.html    
```
