##################################################################
#          Project Convenience Makefile Wrapper for Maven        #
##################################################################

# This makefile is just a convenience wrapper for the Maven
# program. The actual building rules for this project may
# be found in the Maven "pom.xml" file located in this folder.

######################### DEFINITIONS ############################

# Define the commandline invocation of Maven if necessary:
ifeq ($(MVN),)
    MVN  := docker run -it --rm --name my-maven-project -v `pwd`:/usr/src/mymaven -w /usr/src/mymaven maven:3.8.4-openjdk-17 mvn
endif

ifeq (run,$(firstword $(MAKECMDGOALS)))
  # use the rest as arguments for "run"
  RUN_ARGS := $(wordlist 2,$(words $(MAKECMDGOALS)),$(MAKECMDGOALS))
  # ...and turn them into do-nothing targets
  $(eval $(RUN_ARGS):;@:)
endif

export ROOT_DIR=${PWD}

######################## BUILD TARGETS ###########################

help:
	@ echo "Usage:  make <target>"
	@ echo "Targets:"
	@ echo "   build 		 ... Builds a jar, and a docker image"
	@ echo "   test 		 ... Runs unit tests"
	@ echo "   -s run {file_path} 	 ... Runs the docker image"

build-jar:
	@ $(MVN) clean install

build-docker:
	@ docker build -t taboola --build-arg JAR_PATH=target/com.ofir.taboola-1.0-SNAPSHOT.jar .

build: build-jar build-docker

test:
	@ $(MVN) test

run:
	@ docker run -v ${ROOT_DIR}/examples/:/app/examples/ taboola $(RUN_ARGS)

clean:
	@- rm -rf ./target
	@- docker image rm -f taboola