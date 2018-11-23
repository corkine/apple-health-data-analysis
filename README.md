# Apple Health Data Analysis

> 版本 1.0

## 关于数据

Apple Health 健康应用程序记录了一些数据，包括心率、活动、卡路里消耗、站立、身高、体重、性别、各种营养摄入等等。

数据导出的方法为：健康 -> 健康数据标签 -> 右上角头像标志 -> 导出健康数据按钮。

导出的数据为 zip 压缩文件，有一个叫做 export.xml 的文件。

如果使用了 Apple Watch，那么数据量还是非常丰富的，比如每日站立、每日卡路里、每日活动分钟数。此外，带着手表时每隔五分钟的心率数据、锻炼数据、GPS 记录和时间、消耗卡路里数均记录在此。

如果不是国行，那么还有心率变异性、最大耗氧量的计算。后者需要进行30分钟以上的锻炼，才会生成此数据。

## 处理方法

参见这一篇博客：https://sspai.com/post/42135

### 使用 JavaScript 处理

一般而言，在使用 Python 分析之前，需要导出为 CSV 格式数据，一个最方便的选择是：http://ericwolter.com/projects/health-export.html

但是在线转换有隐私的问题，虽然作者已经保证不收集信息。对我而言，最大的问题，是慢，JavaScript 的速度，对于 365 天的 Apple Watch 数据而言，需要 10 - 20 分钟处理，CPU 占用极高。

### 使用 Python 处理

Github 上有人提供 Python 处理脚本：

https://github.com/tdda/applehealthdata/blob/master/applehealthdata.py

### 使用 Scala 处理

本程序的 /main/scala 文件夹下的 ConvData.scala 可以用来将从苹果健康程序导出的 xml 文件进行解析，转换成为 CSV 文件，方便 Python 进一步的分析。

相比较 JavaScript，Scala 处理速度提升了 10  倍（得益于 JVM 和 DOM4J 库），在具有静态类型检查的同时，语法相较于 Python 更为优雅。

### 使用 Java 处理

本程序的 /main/java/ConvData.java 提供了大部分处理所需要的代码。

## 分析（预计版本 2.0）

分析使用 Python 进行最好，待补充。

## 服务（预计版本 3.0）

本库提供了很多 Entity 类，这些类是为将 XML 数据映射为 POJO，进一步通过 JPA 将数据映射到 MySQL 数据库而准备的。

通过 Python 探索一些合适的指标，然后通过 Hibernate、Spring 和 Spring MVC 提供基于 Web 的服务，前端使用 JavaScript 和 echat.js 进行展示。