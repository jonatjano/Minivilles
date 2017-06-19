javac @options.list @sources.list
cd class
java minivilles.metier.GestionJeu
cd ..
if "%1"=="nopause" goto fin
PAUSE
:fin