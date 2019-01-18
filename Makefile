all:
	mvn -U clean install -T 4.0C -DskipTests

config:
	mvn -pl platform/config clean install
	
registry:
	mvn -pl platform/registry clean install
	
deploy:
	docker-compose -f docker-compose.yml -f docker-compose.test.yml up -d
	docker container ls
	
undeploy:
	docker-compose -f docker-compose.yml -f docker-compose.test.yml down
	
