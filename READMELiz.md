# gravehunters
Multiplayer game
*4/5/2017: Added GameBoard.java
*4/6/2017: Added ClientListener.java, Edited GameClient.java
*4/5/2017: Implemented a basic 6x6 board with '+' as a border and '-' as the field, finished getBoard()
            *Still required: Player movement.
*4/12/2017: Edited the board generation to generate 'x' obstacles and '*' points
*4/20/2017: Added README.md, added three new methods for game logic.
*5/4/2017: Client program now terminates if user types "quit" and presses enter. Added a new object named PlayerPiece
*5/10/2017: Cleaned up GameBoard. It is now named Board2.
            *Updated PlayerPiece so that it keeps track of its own score. PlayerPiece2 is the new name.
            *Implemented a static and public game board in GameServer. All game logic is handled by the Board2 object and the ClientHandler.
            *Implemented a better scoreboard using static variables. The game will tell you the winner, as well as if there is a tie.
