cd ..
mvn install -Dmaven.test.skip=true
mv target/*.jar build/

cd build
docker build -t demo-test-v1.0 .
