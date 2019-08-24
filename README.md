# Udacity Java Nanodegree Vehicles API Project

## Dependencies

The project requires the use of Maven and Spring Boot, along with Java 11.

## Building

If Java 11 is already set in the environment this is being built on all of the
services in this project can be built using the included `Makefile`.

```sh
make
```

If some other version is set it may be necessary to set the `JAVA_HOME`
variable for the build. For example, on Linux.

```sh
JAVA_HOME=/usr/lib/jvm/java-11-openjdk make
```

## Running

### Manually

### Docker

If `docker` and `docker-compose` are installed everything can be run using the
included stack file. This assumes that previous steps for building the services
have been prepared. If not, these are the only steps required to do this.

```sh
make
make image
```

Running everything can now be done with:

```sh
docker-compose up
```
