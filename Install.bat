if not exist "%MINIVILLE11%" mkdir "%MINIVILLE11%"
if not exist "%MINIVILLE11%\PartieInit" mkdir "%MINIVILLE11%\PartieInit"

javac -d "%MINIVILLE11%" @sources.list

copy InitTemplate.ini "%MINIVILLE11%\PartieInit\"