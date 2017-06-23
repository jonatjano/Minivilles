set MINIVILLE11=C:\Program Files (x86)\minivilles11

if not exist "C:\Program Files (x86)\minivilles11" mkdir "C:\Program Files (x86)\minivilles11"
if not exist "C:\Program Files (x86)\minivilles11\PartieInit" mkdir "C:\Program Files (x86)\minivilles11\PartieInit"

javac -d "C:\Program Files (x86)\minivilles11" @sources.list
copy file1 "C:\Program Files (x86)\minivilles11\PartieInit"