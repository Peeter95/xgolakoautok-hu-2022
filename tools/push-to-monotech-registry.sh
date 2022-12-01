#!/bin/bash

clj -X:prod
docker build -t registry.monotech.hu/xxx -f Dockerfile .
docker push registry.monotech.hu/xxx
