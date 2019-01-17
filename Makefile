all:
	mvn -U clean install -DskipTests

deploy:
	docker-compose -f docker-compose.yml up -d
	docker container ls
	
undeploy:
	docker-compose -f docker-compose.yml down