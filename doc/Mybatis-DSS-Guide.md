# 数据库使用指南

## Reference

- [**mall**](http://www.macrozheng.com/#/reference/mybatis_dynamic_sql)

## 目录

- **Page Helper**
- **Dynamic Sql**

## Page Helper

在所有返回列表的数据操作前增加一行代码：

```
PageHelper.startPage(pageNum, pageSize);
```

`pageNum`和`pageSize`是传入参数。

其中`pageNum`是一定要前端输入的，但后者可以定义为常量。

之后的操作可以用`pageInfo<>`构造，但也可以不用。

## Dynamic Sql

What is the `SelectDSLCompleter`?

可以理解是`cpp`里的函数指针类型，它可以接收`lambda`函数。

例如：

```
List<UmsAdmin> list = adminMapper.select(
    c -> c.where(UmsAdminDynamicSqlSupport.username, isEqualToWhenPresent(username))
          .and(UmsAdminDynamicSqlSupport.status, isIn(statusList))
          .orderBy(UmsAdminDynamicSqlSupport.createTime.descending())
    );
```

很容易发现上边的语句可以翻译为：

```sql
select q
from admin
where username = ${username}
  and status in (...${statusList})
order by ${createTime} desc
```

更多用法见[Reference](#Reference)。

艹发现我完全没必要写。不过写了就写了，主要还是看那个参考文档。