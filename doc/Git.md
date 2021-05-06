# Git规范

## reference

- [git flow 工作流](https://github.com/xirong/my-git/blob/master/git-workflow-tutorial.md#23-gitflow工作流)

- [Commit message 和 Change log 编写指南](http://www.ruanyifeng.com/blog/2016/01/commit_message_change_log.html)

## Branch规范

分支的命名限定在以下范围（master | dev | develop | release | feature/xxx | hotfix/xxx | fix/xxx ） xxx为任意内容

样例：

1. feature/酒店信息管理

2. hotfix/订单流程更改 

## Commit规范

每次commit时，添加的描述信息需要有一个特定的前缀，限定在以下范围(feat|doc|test|docs|chore|refactor|fix|style| perf): xxx    **注意冒号后面有一个空格**

feat - 新功能（feature）

doc - 文档（documentation）

style - 格式（不影响代码运行的变动）

refactor - 重构（即不是新增功能，也不是修改bug的代码变动）

fix - 修补bug

test - 增加测试

chore - 构建过程或辅助工具的变动

样例：

1. feat: 查看酒店信息功能完成

2. fix: 浏览酒店bug修复

## 开发流程

只有release分支可以进行Test环境部署，clone项目之后新建release分支

```bash
git checkout -b release
git add .
git commit -m 'feat: xxx'
git push --set-upstream origin release
````

只有master分支可以进行Prod环境部署

```bash
git checkout -b master
git merge release
git push origin master
```