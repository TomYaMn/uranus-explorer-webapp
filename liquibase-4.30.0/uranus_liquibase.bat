@echo on
echo "Run Liquibase for uranus"

:: Set the path to the Downloads folder
set DOWNLOADS_FOLDER=%USERPROFILE%\Downloads

:: Run Liquibase command and redirect output to a text file in the Downloads folder
CALL liquibase.bat --defaultsFile=uranus_liquibase.properties update > "%DOWNLOADS_FOLDER%\liquibase_output.txt" 2>&1

:: Display a message after Liquibase runs
echo Liquibase update completed. The output is saved to %DOWNLOADS_FOLDER%\liquibase_output.txt
pause
