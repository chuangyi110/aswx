## deploy

### 项目打包

1. 在主机或者开发机打包项目到deploy；
    ```
    cd aswxmall
    cat ./aswxmall-db/sql/aswxmall_schema.sql > ./deploy/db/aswxmall.sql
    cat ./aswxmall-db/sql/aswxmall_table.sql >> ./deploy/db/aswxmall.sql
    cat ./aswxmall-db/sql/aswxmall_data.sql >> ./deploy/db/aswxmall.sql
    
    cd ./aswxmall-admin
    cnpm install
    cnpm run build:dep
    
    cd ..
    mvn clean package
    cp -f ./aswxmall-all/target/aswxmall-all-*-exec.jar ./deploy/aswxmall/aswxmall.jar
    ```
    这里的工作是：
    1. 把数据库文件拷贝到deploy/db文件夹
    2. 编译aswxmall-admin项目
    3. 编译aswxmall-all模块，同时把aswxmall-admin编译得到的静态文件拷贝到
       aswxmall-all模块的static目录
       
2. 修改aswxmall文件夹下面的*.yml外部配置文件，当aswxmall-all模块启动时会
    加载外部配置文件，而覆盖默认jar包内部的配置文件。
    例如，配置文件中一些地方需要设置成远程主机的IP地址
    
此时deploy部署包结构如下：

* bin

存放远程主机运行的脚本，包括deploy.sh脚本和reset.sh脚本

* db

存放aswxmall数据库文件

* aswxmall

存放远程主机运行的代码，包括aswxmall-all二进制可执行包和aswxmall外部配置文件

* util
存放开发主机运行的脚本，包括package.sh脚本和lazy.sh脚本。
由于是本地开发主机运行，因此开发者可以不用上传到远程主机。

### 项目部署

1. 远程主机环境（MySQL和JDK1.8）已经安装好，请确保云主机的安全组已经允许相应的端口。
2. 导入db/aswxmall.sql
    ```bash
    cd /home/ubuntu/deploy/db
    mysql -h localhost -u $ROOT -p$PASSWORD < aswxmall.sql
    ```
3. 启动服务
    ```bash
    sudo service aswxmall stop
    sudo ln -f -s /home/ubuntu/deploy/aswxmall/aswxmall.jar /etc/init.d/aswxmall
    sudo service aswxmall start
    ```
4. 测试是否部署成功(xxx.xxx.xxx.xxx是云主机IP）：
    ```
    http://xxx.xxx.xxx.xxx:8080/wx/index/index
    http://xxx.xxx.xxx.xxx:8080/admin/index/index
    http://xxx.xxx.xxx.xxx:8080/#/login
    ```

### 项目辅助脚本

在前面的项目打包和项目部署中都是采用手动命令来部署。
这里可以写一些脚本简化：

* util/packet.sh

在开发主机运行可以自动项目打包

* util/lazy.sh

在开发主机运行可以自动项目打包、项目上传远程主机、自动登录系统执行项目部署脚本。
    
注意：
> 1. 开发者需要在util/lazy.sh中设置相应的远程主机登录账号和密钥文件路径。
> 2. 开发者需要在bin/reset.sh设置远程主机的MySQL的root登录账户。
    
* bin/deploy.sh

在远程主机运行可以自动部署服务

* bin/reset.sh

在远程主机运行可以自动项目导入数据、删除本地上传图片、再执行bin/deploy.sh部署服务。

注意：
> 开发者需要在bin/reset.sh设置远程主机的MySQL的root登录账户。

总结，当开发者设置好配置信息以后，可以在本地运行lazy.sh脚本自动一键部署:
```bash
cd aswxmall
./deploy/util/lazy.sh
```

不过由于需要设置的信息会包含敏感安全信息，强烈建议开发者参考这里的deploy文件夹，
然后实现自己的deploy文件夹，妥善处置外部配置文件和脚本中的敏感安全信息!!!