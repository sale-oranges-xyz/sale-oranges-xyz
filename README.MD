#### 指导思想（自己写的公众号文章）
https://mp.weixin.qq.com/s/bKPCT3SivnnEXHVtAtE22A   
https://mp.weixin.qq.com/s/0g9oUsE6vRIpQcz4hSWXhw   
https://mp.weixin.qq.com/s/ERxlPKjuX8nWB2awHVZrlQ   

##### 项目结构
```
 | - admin-api-gateway 后台管理部分api网关
 | - common-nice-maked 存放项目的公用模块
    | - common-biz-core 聚合服务器方面通用模块
    | - common-base-core 父模块，用于控制spring cloud版本号，其他微服务必须继承该模块
    | - common-core 存放封装的公用代码库
    | - common-feign feign 部分通用配置
    | - common-gateway zuul网关部分通用配置
    | - common-jwt  token 接口相关实现
    | - common-redis redis使用配置
    | - common-security 微服务通用security配置
    | - common-swagger 接口api说明，前后端交互使用。注意api接口包名要以*.controller命名
    | - common-token token 部分抽象接口
    | - common-web web 部分通用代码
 | - domains 存放实体类与业务逻辑处理
    | - admin-user 微服务：后台管理中心
        | - admin-user-api 管理员api访问模块
        | - admin-user-core 领域模型数据
        | - admin-user-dto 用户管理dto模块
        | - admin-user-remote 暴露可供远程访问方法
    | - member-user 微服务: 会员中心
        | - member-user-api 会员api访问模块
        | - member-user-core 领域模型数据
        | - member-user-dto 会员dto模块
        | - member-user-remote 暴露可供远程访问方法
 | - config-server-center 配置中心
 | - member-api-gateway  前端用户部分api网关
 | - register-center 服务注册中心
```
##### 说明
```
2.创建启动类，需要在com.github.geng目录之前，原因是启动配置文件都是在com.github.geng.*
	当然，也可以不这样处理，但是必须使用@ComponentScans 扫描指定包名。
3.创建时间与修改时间使用时间戳，是因为考虑到不同时区问题
4.service层定义接口原因：
    (1).service方法是模块暴露给其他模块的,修改时需要特别注意
    (2).spring有两种动态代理jdk与cglib，jdk8动态代理性能提升
5.*-remote层：远程访问模块，使用feign暴露给其他微服务远程调用方法
6.dto(Data Transfer Object)层：api接口传输对象,controller层之间交互
7.一般初始化sql，放在resources下
8.为什么实体类与业务逻辑分成两个模块
    (1).因为微服务远程调用需要用到其他项目实体类，不想重复写两套代码
    (2).重复写两套代码，维护难度加大
9.用户部分网关与后台管理部分网关，用户验证逻辑不一致
  实际情况访问量也有很大差异，拆开
```
##### 项目规范
```
1.spring cloud 的微服务之间调用都是通过http请求，即都是通过controller层交
  而controller层传输数据都是dto(Data Transfer Object)数据
  为了减少重复代码，应把dto层独立成单独maven模块
```
##### 注意点整理
```
1.@Table与@Entity区别，参考 https://www.cnblogs.com/alsf/p/7822241.html
2.考虑集成flyway
3.云端配置与消息插件整合，效率才会最优
```
