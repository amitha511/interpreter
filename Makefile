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

######################## BUILD TARGETS ###########################

install:
	@ $(MVN) clean install

build:
	@ docker build -t taboola --build-arg JAR_PATH=target/com.ofir.taboola-1.0-SNAPSHOT.jar .

run:
	@ docker run taboola

clean:
	@- rm -rf ./target
	@- docker image rm -f taboola


help:
	@ echo "Usage   :  make <target>"
	@ echo "Targets :"
	@ echo "   install .......... Builds a jar"
	@ echo "   build .......... Builds a docker image"
	@ echo "   run .......... Runs the docker image"