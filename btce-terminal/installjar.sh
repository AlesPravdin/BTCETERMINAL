#!/bin/sh
mvn install:install-file -Dfile=./build/libs/btce-terminal.jar -DgroupId=by.groovycoin -DartifactId=btce-terminal -Dversion=0.9.1 -Dpackaging=jar