docker stop demo-test

docker rm demo-test

sh build.sh

docker run -d --name demo-test -p 8100:8100 demo-test-v1.0

docker images | grep none | awk {'print $3'} | xargs docker rmi -f
