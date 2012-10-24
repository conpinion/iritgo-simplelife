#!/bin/bash

set -e

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd ${DIR}

optClean=
optStage=1
optFormat=
optSettings=
optMavenSettings=
optBatchMode=
optUseGradle=

shortOptions=c1gh
longOptions=format,settings:,batchmode,gradle

options=("$(getopt -u -o ${shortOptions} --long ${longOptions} -- $@)")

function processOptions
{
	while [ $# -gt 0 ]
	do
		case $1 in
			-c)
				optClean=1
				;;
			-1)
				optStage=1
				;;
			--format)
				optFormat=1
				;;
			--settings)
				shift
				optSettings="--settings $1"
				optMavenSettings="-s $HOME/.m2/$1-settings.xml"
				optGradleSettings="$1"
				;;
			--batchmode)
			        optBatchMode=1;;
			-g|--gradle)
				optUseGradle=1
				;;
			-h) printf "Usage: %s: [options]\n" $0
			    printf "Options:\n"
			    printf " -c                              Perform a clean build\n"
			    printf " -1                              Start from stage 1\n"
			    printf " --format                        Format the source code\n"
			    printf " --settings                      Alternate Maven settings file\n"
			    printf " --batchmode                     Run maven in batch mode\n"
			    printf " -g,--gradle                     Use Grade instead of Maven\n"
			    printf "                                 (Leave out the path and the extension)\n"
			    printf " -h                              Print this help\n"
			    exit 0
			    ;;
		esac
		shift
	done
}

processOptions ${options}

MVN="mvn"
if [ ! -z "$optMavenSettings" ]
then
	MVN="$MVN $optMavenSettings"
fi
if [ ! -z "$optBatchMode" ]
then
	MVN="$MVN -B"
fi

BUILD="./build.sh"
if [ ! -z "$optSettings" ]
then
	BUILD="$BUILD $optSettings"
fi
if [ ! -z "$optBatchMode" ]
then
	BUILD="$BUILD $optBatchMode"
fi

if [ ! -z "${optGradleSettings}" ]
then
	GRADLE="gradle ${optGradleSettings}"
else
	GRADLE="gradle"
fi

shift $(($OPTIND - 1))

if [ ! -z "${optFormat}" ]
then
	${MVN} java-formatter:format
	${MVN} license:format
	exit 0
fi

if [ ! -z "${optClean}" ]
then
	if [ ! -z "${optUseGradle}" ]
	then
		${GRADLE} clean
	else
		${MVN} clean
	fi
fi

if [ ! -z "${optUseGradle}" ]
then
	${GRADLE} install
else
	${MVN} install
fi
