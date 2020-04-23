#!/usr/bin/env bash

VERSION=$1

cp -p "../target/micro-gameoflife-gridworker-${VERSION}.jar" .
sed -i "s/<VERSION>/${VERSION}/g" Dockerfile
docker build -t "mgol-gridworker:${VERSION}" .
sed -i "s/${VERSION}/<VERSION>/g" Dockerfile
rm "micro-gameoflife-gridworker-${VERSION}.jar"
