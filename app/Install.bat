if not exist "%MINIVILLE11%" mkdir "%MINIVILLE11%"
if not exist "%MINIVILLE11%\PartieInit" mkdir "%MINIVILLE11%\PartieInit"
if not exist "%MINIVILLE11%\images" mkdir "%MINIVILLE11%\images"
if not exist "%MINIVILLE11%\Sauvegarde" mkdir "%MINIVILLE11%\Sauvegarde"
xcopy "images" "%MINIVILLE11%\images" /s /e /Y
xcopy "PartieInit" "%MINIVILLE11%\PartieInit\" /s /e /Y


javac -d "%MINIVILLE11%" -encoding utf-8 @sources.list
PAUSE