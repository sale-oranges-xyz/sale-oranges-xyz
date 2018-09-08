1. zuul 网关截取，查看 https://www.jianshu.com/p/8e78c0716365
2. zuul 过滤器的执行是有先后顺序的，查看 https://www.jianshu.com/p/ff863d532767
   比如我要获取PreDecorationFilter执行后添加在currentContext的内容   
   过滤器的filterOrder序号就不能大于5，因为PreDecorationFilter的filterOrder为5