for f in $*
do
  if [ ! -f $f ]; then
    f=org/games/hangman/$f.java
  fi
  javac -classpath ../bin -d ../bin $f
done
