.PHONY =: clean image deploy
.DEFAULT_GOAL := deploy
.SILENT : clean image deploy

deps:
	docker pull openjdk:11

clean:
	@docker-compose down
	@docker rmi nd035-eureka
	#docker rmi nd035-pricing-service
	#docker rmi nd035-boogle-maps
	#docker rmi nd035-vehicles-api

image:
	@docker-compose build

deploy:
	@docker-compose up
