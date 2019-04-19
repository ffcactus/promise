all:
	mvn clean install -DskipTests

config:
	mvn -pl platform/config clean install	
registry:
	mvn -pl platform/registry clean install
gateway:
	mvn -pl platform/gateway clean install

deploy-db:
	docker-compose -f docker-compose.db.yml up -d

undeploy-db:
	docker-compose -f docker-compose.db.yml down

restart-db: undeploy-db deploy-db

deploy:
	docker-compose -f docker-compose.yml -f docker-compose.env.yml up -d
	docker container ls
    	
undeploy:
	docker-compose -f docker-compose.yml -f docker-compose.env.yml down
	
restart: undeploy deploy
	
