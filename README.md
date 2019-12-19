## 环境
1. jdk1.8
2. maven
3. git
## 编译
1. git clone url
2. mvn clean install -Dmaven.test.skip=true
3. mvn spring-boot:run
## docker 部署
1. 安装docker: `yum install docker`
2. cd build/
3. sh build.sh