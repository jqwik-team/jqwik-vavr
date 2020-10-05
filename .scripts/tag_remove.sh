#!/bin/sh

if [ -z "$1" ]
  then
    echo "version required"
    exit 1
fi

git tag -d $1
git push origin --delete $1