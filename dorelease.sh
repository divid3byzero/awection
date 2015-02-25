#!/bin/bash
function advance_version () {
    local v=$1
    # Get the last number. First remove any suffixes (such as '-SNAPSHOT').
    local cleaned=`echo $v | sed -e 's/[^0-9][^0-9]*$//'`
    local last_num=`echo $cleaned | sed -e 's/[0-9]*\.//g'`
    local next_num=$(($last_num+1))
    # Finally replace the last number in version string with the new one.
    echo $v | sed -e "s/[0-9][0-9]*\([^0-9]*\)$/$next_num/"
}

export GIT_MERGE_AUTOEDIT=no
VERSION=`mvn org.apache.maven.plugins:maven-help-plugin:2.1.1:evaluate -Dexpression=project.version | grep -v '\[' | grep -v Download`
RELEASE_VERSION=`echo $VERSION | sed -e 's/[^0-9][^0-9]*$//'`
echo $RELEASE_VERSION
NEW_VERSION=`advance_version $VERSION`
NEW_VERSION=$NEW_VERSION-SNAPSHOT
echo $NEW_VERSION

git flow release start $RELEASE_VERSION
mvn versions:set -DnewVersion=$RELEASE_VERSION versions:commit -q
git add -A
git commit -m "Bump version to $RELEASE_VERSION"
git flow release finish -m "$RELEASE_VERSION" $RELEASE_VERSION
mvn versions:set -DnewVersion=$NEW_VERSION versions:commit -q
git add -A
git commit -m "Bump version to $NEW_VERSION"
git push origin --all
git push origin --tags
