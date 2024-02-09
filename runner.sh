#!/bin/bash

#===============================Configuration====================================
browser=chrome
tags="@Login"
#==============================================================================

if [ -n "$browser" ]; then
    if [ -n "$tags" ]; then
        mvn clean test -Dbrowser="$browser" -Dcucumber.filter.tags="$tags"
    else
        echo "TAGS IS REQUIRED!"
    fi
else
    echo "BROWSER IS REQUIRED!"
fi
