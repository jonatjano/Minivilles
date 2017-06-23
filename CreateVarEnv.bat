mkdir "%windir%\system32\test" 2>nul
if NOT "%errorlevel%" == "0" goto :err
rmdir %windir%\system32\test
reg add "HKEY_LOCAL_MACHINE\SYSTEM\ControlSet001\Control\Session Manager\Environment" /v MINIVILLE11 /t REG_SZ /d "%APPDATA%/minivilles"
cls
goto :svt
:err
cls
echo "Veuillez lancez en tant qu'administrateur !"
:svt
PAUSE