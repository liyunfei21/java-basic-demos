echo "cd .."
cd ..

echo "pwd"
pwd

echo "mv target/*.jar build/"
mv target/*.jar build/

echo "ls -l"
ls -l

echo "cd build"
cd build

echo "pwd"
pwd

echo "docker build -t demo-test-v1.0 ."
docker build -t demo-test-v1.0 .

echo "docker images"
docker images

