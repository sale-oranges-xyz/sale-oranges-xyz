##### 项目结构
```
 | - admin-api-gateway 后台管理部分api网关
 | - cloud-conig 云端配置
 | - common-nice 存放项目的公用模块
    | - common-auth-client 服务器间调用授权与客户授权模块,远程调用处理
    | - common-core 存放封装的公用代码库
    | - common-jwt  token 相关配置
    | - common-mvc  spring mvc 相关方面的配置
    | - common-redis redis使用配置
    | - common-security 微服务通用security配置
    | - common-admin-security 微服务管理端通用security配置
    | - common-swagger 接口api说明，前后端交互使用。注意api接口包名要以*.controller命名
 | - domains 存放实体类与业务逻辑处理
    | - admin-users 微服务：管理用户中心
        | - admin-users-dto 用户管理传输实体类
        | - admin-users-business 业务逻辑实现
    | - auth-center 微服务：鉴权中心
        | - auth-center-dto http传输数据
        | - auth-center-business 业务逻辑实现
    | - domains-biz-core 聚合通用业务模块的模块
    | - domains-dto-core 聚合用于dto模块的模块
    | - member-center 微服务: 会员中心
        | - member-center-dto http传输数据
        | - member-center-biz 业务逻辑实现
 | - member-api-gateway  前端用户部分api网关      
 | - monitor  服务监控中心
 | - register-center 服务注册中心
```
##### 说明
```
1.部分依赖jar包声明为provided,目的是减少重复引用
2.创建启动类，需要在com.github.geng目录之前，原因是启动配置文件都是在com.github.geng.*
	当然，也可以不这样处理，但是必须使用@ComponentScans 扫描指定包名。
3.创建时间与修改时间使用时间戳，是因为考虑到不同时区问题
4.service层定义接口原因：
    (1).service方法是模块暴露给其他模块的,修改时需要特别注意 
    (2).spring有两种动态代理jdk与cglib，jdk8动态代理性能提升
5.feign层：微服务远程调用方法
6.dto(Data Transfer Object)层：api接口传输对象,controller层之间交互
7.一般初始化sql，放在resources下
8.为什么实体类与业务逻辑要分成两个模块
    (1).因为微服务远程调用需要用到其他项目实体类，不想重复写两套代码
    (2).重复写两套代码，维护难度加大
9.用户部分网关与后台管理部分网关，用户验证逻辑不一致，实际情况访问量也有很大差异，拆开
```
##### 项目规范
```
1.spring cloud 的微服务之间调用都是通过http请求，即都是通过controller层交互。
	而controller层传输数据都是dto(Data Transfer Object)数据。
	为了减少重复代码，应把dto层独立成单独maven模块
2.考虑集成flyway
3.云端配置与消息插件整合，效率才会最优
```
##### 注意点整理
```
1.@Table与@Entity区别，参考 https://www.cnblogs.com/alsf/p/7822241.html
2.启动顺序：register-center -> auth-center | admin-users -> api-gateway 
3.项目里的feign的远程调用，是不经过网关的。
    所以一个微服务有没有权限访问其他微服，需要在各自的服务filter做处理
    
```