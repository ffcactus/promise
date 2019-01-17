all:
	mvn -U clean install

deploy:
	docker-compose -f docker-compose.yml up -d
	
undeploy:
	docker-compose -f docker-compose.yml down