cd ..
echo "当前目录："
pwd
mv target/*.jar build/
ls -l

cd build
echo "当前目录："
pwd
docker build -t demo-test-v1.0 .
docker images

