echo "export MINIVILLE11="$HOME/minivilles11"" >> $HOME/.bashrc

if [ ! -d "$MINIVILLE11" ]
then
    mkdir "$MINIVILLE11"
fi
if [ ! -d "$MINIVILLE11" ]
then
    mkdir "$MINIVILLE11/PartieInit"
fi


javac -d "$MINIVILLE11" @sources.list

cp InitTemplate.ini "$MINIVILLE11/PartieInit/"