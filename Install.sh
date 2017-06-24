
if [ ! -d "$MINIVILLE11" ]
then
    mkdir "$MINIVILLE11"
fi
if [ ! -d "$MINIVILLE11/PartieInit" ]
then
    mkdir "$MINIVILLE11/PartieInit"
fi
if [ ! -d "$MINIVILLE11/images" ]
then
    mkdir "$MINIVILLE11/images"
fi
cp InitTemplate.ini "$MINIVILLE11/PartieInit/"
cp "images" "$MINIVILLE11/images" -rf

clear
javac -d "$MINIVILLE11" @sources.list
echo "appuyer sur une touche pour continuer..."
read mot