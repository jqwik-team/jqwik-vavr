#!/bin/sh

if [ -z "$1" ]
  then
    echo "version required"
    exit 1
fi

git tag -a $1 -m "Release $1"
git push origin $1
