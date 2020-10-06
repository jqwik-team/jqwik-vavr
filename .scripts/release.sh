#!/bin/sh

if [ -z "$1" ]
  then
    echo "release version required"
    exit 1
fi
if [ -z "$2" ]
  then
    echo "new development version required"
    exit 1
fi
release_version=$1
development_version="$2-SNAPSHOT"

echo "Release notes by git commit message generated (see .release_notes_$release_version.txt)"
git log $(git describe --tags --abbrev=0)..HEAD --pretty=format:"%s" -i -E --grep="^\s*(\[FEATURE\]|\[FIX\])]" > .release_notes_"$release_version".txt
#git log $(git describe --tags --abbrev=0)..HEAD --pretty=format:"%s" -i -E --grep="^\s*(\[INTERNAL\]|\[FEATURE\]|\[FIX\]|\[DOC\])*(\[FEATURE\]|\[FIX\])]" > .release_notes_"$release_version".txt

echo "Start release $release_version by adding tag (see Travis CI)"
git tag -a $release_version -m "[INTERNAL] Release $release_version"
git push origin $release_version

echo "Update development version to $development_version"
mvn -q org.codehaus.mojo:versions-maven-plugin:2.5:set -DnewVersion=$development_version
mvn -q org.codehaus.mojo:versions-maven-plugin:2.5:commit
git ls-files --modified | grep 'pom\.xml$' | xargs git add
git commit -m "[INTERNAL] Update development version to $development_version"

echo "Please commit and push the change with 'git push origin' and update release notes in GitHub!"
echo "Please update version on README.md!"