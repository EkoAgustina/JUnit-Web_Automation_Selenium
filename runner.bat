@echo off

::Enter browser, example => browser=chrome
set browser=edge

::Enter tags, example => tags=@smoke
set tags=@smokeTest

if defined browser (
    if defined tags (
        call mvn clean test -Dbrowser=%browser% -Dcucumber.filter.tags="%tags%"
    ) else (
        echo TAGS IS REQUIRED!
    )
) else (
    echo BROWSER IS REQUIRED!
)



