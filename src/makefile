all:
	-mkdir -p ../bin ../tools
	javac -d ../bin org/games/hangman/*.java
	cp *.txt ../bin
	cd ../bin; jar cf ../tools/hangman.jar .
