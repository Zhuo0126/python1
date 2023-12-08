
@echo off

rem 获取当前目录路径
set "currentDirectory=%CD%"

for /d %%i in ("%currentDirectory%\fep-server-bin-*") do (
    echo Deleting folder: %%i
    rd /s /q "%%i"
)

for %%j in ("%currentDirectory%\fep-server-bin-*") do (
    echo Deleting file: %%j
    del "%%j"
)

