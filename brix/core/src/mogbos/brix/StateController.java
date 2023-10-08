package mogbos.brix;

public class StateController {
    public enum GameState {
        MAIN_PAGE, GAME_PAGE
    }

    static boolean stateChanged = false;

    static GameState gameState = GameState.MAIN_PAGE;

    static public void setGameState(GameState gameState) {
        stateChanged = true;
        StateController.gameState = gameState;
    }
}
