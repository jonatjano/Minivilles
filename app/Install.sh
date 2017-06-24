
if [ ! -d "$MINIVILLE11" ]
then
    mkdir "$MINIVILLE11"
fi
"$MINIVILLE11/PartieInit/"
cp "images" "$MINIVILLE11" -rf
cp "PartieInit" "$MINIVILLE11" -rf

clear
javac -d "$MINIVILLE11" -encoding utf-8 @sources.list
echo "appuyer sur une touche pour continuer..."
read mot