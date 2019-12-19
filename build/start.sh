echo "docker stop demo-test"
docker stop demo-test

echo "docker rm demo-test"
docker rm demo-test

echo "sh build.sh"
sh build.sh

echo "docker run -d --name demo-test -p 8100:8100 demo-test-v1.0"
docker run -d --name demo-test -p 8100:8100 demo-test-v1.0

echo "docker images | grep none | awk {'print $3'} | xargs docker rmi -f"
docker images | grep none | awk {'print $3'} | xargs docker rmi -f

echo "docker images"
docker images
