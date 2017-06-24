
if [ ! -d "$MINIVILLE11" ]
then
    mkdir "$MINIVILLE11"
fi
if [ ! -d "$MINIVILLE11/Sauvegarde" ]
then
    mkdir "$MINIVILLE11/Sauvegarde"
fi
"$MINIVILLE11/PartieInit/"
cp "images" "$MINIVILLE11" -rf
cp "PartieInit" "$MINIVILLE11" -rf

clear
javac -d "$MINIVILLE11" -encoding utf-8 @sources.list
echo "appuyer sur une touche pour continuer..."
read mot