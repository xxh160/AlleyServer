# Databases 概述

## 变更记录

|Time|Content|
|:---:|:---:|
|2021-05-20|post_auth，增加comment列；post，增加label_id；comment，将upper_type改为upper_type_id；user_auth，增加official列；增加label表；增加invitation_code表|

## 实体类表

### user

|Column|Type|Description|
|:---:|:---:|:---:|
|id|int|主键，可自增|
|auth_id|int|对应user_auth权限行id|
|sign|varchar(100)|个人介绍|

个人介绍是写在信息概览的地方的。

### post

|Column|Type|Description|
|:---:|:---:|:---:|
|id|int|主键，可自增|
|auth_id|int|对应权限行id|
|user_id|int|对应发帖者id|
|label_id|int|对应label的id|
|title|varchar(64)|标题|
|content|varchar(500)|帖子正文|
|like_num|int|点赞数|
|comment_num|int|评论数|
|create_t|datetime|创建时间|
|last_modified_t|datetime|最后一次修改时间|
|anchor_id|int|锚点id，对应哪个锚点|
|addr_x|int|经度|
|addr_y|int|纬度|

### comment

|Column|Type|Description|
|:---:|:---:|:---:|
|id|int|主键，可自增|
|upper_type_id|int|表明父级的类型，帖子或者评论|
|upper_id|int|对应父级的id，和上一行结合起来用|
|content|varchar(200)|正文|
|like_num|int|点赞数|
|create_t|datetime|创建时间|
|last_modified_t|datetime|最后一次修改时间|

### anchor

暂定。

|Column|Type|Description|
|:---:|:---:|:---:|
|id|int|主键，可自增|
|name|varchar(64)|名称|
|addr_x|int|经度|
|addr_y|int|纬度|

### user_auth

特意用auth表是方便扩展，post_auth同。

权限的默认值均为false。

|Column|Type|Description|
|:---:|:---:|:---:|
|id|int|主键，可自增|
|official|int|是否为官方用户|
|chat|boolean|是否允许发起聊天|
|position|boolean|是否允许共享位置|
|make_friend|boolean|是否允许申请好友|
|show_wx_info|boolean|是否允许显示微信个人信息|

微信个人信息不包括头像、个人签名、用户名，但包括微信号。

### post_auth

|Column|Type|Description|
|:---:|:---:|:---:|
|id|int|主键，可自增|
|visible|boolean|是否可见|
|comment|boolean|是否可评论|

### label

常量表，应由系统管理员修改。

|Column|Type|Description|
|:---:|:---:|:---:|
|id|int|主键，可自增|
|name|varchar(64)|label名称|

### invitation_code

常量表，应由系统管理员修改。

用于认证官方用户。

官方用户申请流程：

1. 线下或线上提交申请；
2. 线下验证；
3. 管理员向此table插入新邀请码
4. 用户在官方用户认证页面输入邀请码并得到验证

|Column|Type|Description|
|:---:|:---:|:---:|
|id|int|主键，可自增|
|code|varchar(64)|邀请码|
|user_id|int|对应认证用户的id|
|description|varchar(100)|官方用户描述|

## 关系表

### user_post_rel

user和post为一对多关系。

表明user发布了哪些帖子。

|Column|Type|Description|
|:---:|:---:|:---:|
|user_id|int|发帖者id|
|post_id|int|帖子id|

### user_like_post

表明user点赞了哪些post。

|Column|Type|Description|
|:---:|:---:|:---:|
|user_id|int|发帖者id|
|post_id|int|帖子id|

### user_comment_rel

表明user发表了哪些评论。

|Column|Type|Description|
|:---:|:---:|:---:|
|user_id|int|发帖者id|
|comment_id|int|评论id|

### user_like_comment

表明user点赞了哪些comment。

|Column|Type|Description|
|:---:|:---:|:---:|
|user_id|int|发帖者id|
|comment_id|int|评论id|

### post_comment_rel

表明帖子有哪些comment。

|Column|Type|Description|
|:---:|:---:|:---:|
|post_id|int|帖子id|
|comment_id|int|评论id|

### comment_rel

表明评论有哪些评论。

|Column|Type|Description|
|:---:|:---:|:---:|
|father_id|int|父级评论id|
|child_id|int|子评论id|