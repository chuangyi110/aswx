#!/bin/bash

# 请注意
# 本脚本的作用是把本项目编译的结果保存到deploy文件夹中
# 1. 把项目数据库文件拷贝到deploy/db
# 2. 编译aswxmall-admin
# 3. 编译aswxmall-all模块，然后拷贝到deploy/aswxmall

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null && pwd )"
cd $DIR/../..
ASWXMALL_HOME=$PWD
echo "ASWXMALL_HOME $ASWXMALL_HOME"

# 复制数据库
cat $ASWXMALL_HOME/aswxmall-db/sql/aswxmall_schema.sql > $ASWXMALL_HOME/deploy/db/aswxmall.sql
cat $ASWXMALL_HOME/aswxmall-db/sql/aswxmall_table.sql >> $ASWXMALL_HOME/deploy/db/aswxmall.sql
cat $ASWXMALL_HOME/aswxmall-db/sql/aswxmall_data.sql >> $ASWXMALL_HOME/deploy/db/aswxmall.sql

cd $ASWXMALL_HOME/aswxmall-admin
# 安装阿里node镜像工具
npm install -g cnpm --registry=https://registry.npm.taobao.org
# 安装node项目依赖环境
cnpm install
cnpm run build:dep

cd $ASWXMALL_HOME
mvn clean package
cp -f $ASWXMALL_HOME/aswxmall-all/target/aswxmall-all-*-exec.jar $ASWXMALL_HOME/deploy/aswxmall/aswxmall.jar