### 有问题请加下面的联系方式，免费指导
QQ: 702414962
微信:13736073041
#### 说明

​	由于时间紧，所以很多功能需要完善，需要添加，具体的看下面的【程序中的不足之处】

​	生成试卷后会计算难度系数，然后于用户输入的难度系数比较，如果上下偏差大于 Wave值（这个值需要自己指定） 那么会再生成试卷进行比较，循环三次仍然不符合，那么生成试卷失败

​	指定Wave方法

​		修改application.yml中的wave的值

#### 使用技术

- **Spring**	

- **SpringMVC** 

- **Mybatis** （这里使用嵌套查询，为数据库分库分表做准备）

- **Spring Boot** （整合前面三者，并且内嵌tomcat）

#### UML类图
    见项目【UML类图.jpg】 
#### 样式


#### 数据库脚本

​	见项目【src/main/resources/com/papermarking/新建表语句.sql】

#### 程序中的不足之处

- **未增加认证功能**

   由于是内部系统，并没有做认证功能，换句话说就是谁都可以使用这个系统。  （如以后需要加上 推荐使用无状态的身份认证---JWT）

- **未增加题目上传图片功能**

  目前并没有添加题目可以上传图片功能

- **未增加自定义题型功能**

  题型目前是固定的，只有选择，判断，简答，填空四种，这是设计时的缺陷，由于时间比较紧，重新设计再次编码需要大量时间，所以这里将题型固定。

- **部分代码效率低下**

  有些逻辑是在sevice层使用循环去调用mapper方法，这样效率很差，会多次创建mapper的代理类和多次连接数据库。

- **未考虑高并发问题**

  无数据库索引

- **未分页**

  推荐使用Mybatis插件 PageHelper , Mybatis自带分页功能，但是是假分页，是在内存中进行筛选

- **前端优化**

  
