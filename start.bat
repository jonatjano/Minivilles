javac @options.list @sources.list
cd class
java minivilles.GestionJeu
cd ..
if "%1"=="nopause" goto fin
PAUSE
:fin