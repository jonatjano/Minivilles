
if [ ! -d "$MINIVILLE11" ]
then
    mkdir "$MINIVILLE11"
fi
if [ ! -d "$MINIVILLE11/PartieInit" ]
then
    mkdir "$MINIVILLE11/PartieInit"
fi
cp PartieInit/InitTemplate.ini "$MINIVILLE11/PartieInit/"
cp "images" "$MINIVILLE11" -rf

clear
javac -d "$MINIVILLE11" -encoding utf-8 @sources.list
echo "appuyer sur une touche pour continuer..."
read mot