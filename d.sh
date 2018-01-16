#!/usr/bin/env bash

cd /Users/vnazarov/workspace/spotadv/spotadvisor
mvn --projects backend spring-boot:run -Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"