# 项目说明
> 优雅的使用 hive udf 可以优化我们的任务，提升工作效率。

### 一、什么是 UDF?
UDF全称为User Defined Function，即用户自定义函数。

### 二、UDF 分类？

|UDF 分类|描述
|--|--|
|UDF（User Defined Scalar Function）|用户自定义标量值函数（User Defined Scalar Function）。其输入与输出是一对一的关系，即读入一行数据，写出一条输出值 。|
|UDTF（User Defined Table Valued Function）|自定义表值函数，是用来解决一次函数调用输出多行数据场景的，也是唯一能返回多个字段的自定义函数。而UDF只能一次计算输出一条返回值。|
|UDAF（User Defined Aggregation Function）|自定义聚合函数，其输入与输出是多对一的关系， 即将多条输入记录聚合成一条输出值。可以与SQL中的Group By语句联用。|

### 三、功能和代码展示

- **json 数据处理，获取所有的 json key 字段**：[代码](https://github.com/aikuyun/hive_custom_udf/blob/master/src/main/java/cuteximi/GetAllKeys.java)
- 待续...
