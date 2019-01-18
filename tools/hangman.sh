#
# unix shell script to run hangman
#

# Usage: hangman.sh [-rt]
# -rt: use richtext

dir=`dirname $0`
java -classpath $dir/hangman.jar org.games.hangman.Hangman $*
