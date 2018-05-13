#### sale-orange-xyz 取首字母大写会是另外一个意思

##### 项目结构
```
 | - common-nice 存放项目的公用模块
    | - common-core 存放封装的公用代码库
    | - common-redis redis使用配置
    | - common-swagger 接口api说明，前后端交互使用。注意api接口包名要以*.controller命名
    | - common-mvc  spring mvc 相关方面的配置
    | - common-jwt  token 相关配置
 | - register-center 服务注册中心
 | - domains 存放实体类与业务逻辑处理
    | - admin-users 微服务：系统管理用户
        | - users 管理用户实体类
        | - business 业务逻辑实现
```
##### 说明
```
1.部分依赖jar包声明为provided,目的是减少重复引用
2.创建启动类，必须在com.github.geng目录之前，原因是启动配置文件都是在com.github.geng.*
	当然，也可以使用@ComponentScans 扫描指定包名。
3.创建时间与修改时间使用时间戳，是因为考虑到不同时区问题
4.service层定义接口原因：
    (1).service方法是模块暴露给其他模块的,修改时需要特别注意 
    (2).spring有两种动态代理jdk与cglib，jdk8动态代理性能提升
5.remote层：微服务远程调用方法
6.dto(Data Transfer Object)层：api接口传输对象
7.一般初始化sql，放在resources下
8.为什么实体类与业务逻辑要分成两个模块
    (1).因为微服务远程调用需要用到其他项目实体类，不想重复写两套代码
    (2).重复写两套代码，维护难度加大
	
```
