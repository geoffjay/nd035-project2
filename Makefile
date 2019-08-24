.PHONY =: all boogle eureka pricing vehicles clean image deploy
.DEFAULT_GOAL := build
.SILENT : clean image deploy

all: build

deps:
	docker pull openjdk:11

boogle:
	@cd boogle-maps; mvn package

eureka:
	@cd eureka; mvn package

pricing:
	@cd pricing-service; mvn package

vehicles:
	@cd vehicles-api; mvn package

build: boogle eureka pricing vehicles

clean:
	@cd boogle-maps; mvn clean
	@cd eureka; mvn clean
	@cd pricing-service; mvn clean
	@cd vehicles-api; mvn clean
	#@docker-compose down
	#@docker rmi nd035-eureka
	#@docker rmi nd035-pricing-service
	#@docker rmi nd035-boogle-maps
	#@docker rmi nd035-vehicles-api

image:
	@docker-compose build

deploy:
	@docker-compose up
