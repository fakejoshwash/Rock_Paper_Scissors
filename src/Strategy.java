public interface Strategy {
    int determineMove(int playerMove);
    int cheat(int playerMove);
    int leastUsed(int rockUsed, int paperUsed, int scissorsUsed);
    int mostUsed(int rockUsed, int paperUsed, int scissorsUsed);
    int mystery();
    int usedLast(int usedLast);
    int didPlayerWin(int playerMove, int computerMove);
    String whatMove(int move);
}
