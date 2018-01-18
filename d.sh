#!/usr/bin/env bash

cd /Users/vnazarov/workspace/spotadv/spotadvisor
mvn --projects backend spring-boot:run -Drun.jvmArguments="-Dspring.profiles.active=dev -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"