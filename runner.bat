@echo off

::===============================Configuration==================================
set browser=headless

set tags=@Login
::==============================================================================

if defined browser (
    if defined tags (
        call mvn clean test -Dbrowser=%browser% -Dcucumber.filter.tags="%tags%"
    ) else (
        echo TAGS IS REQUIRED!
    )
) else (
    echo BROWSER IS REQUIRED!
)



