package mogbos.brix;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import mogbos.brix.screens.GameScreen;
import mogbos.brix.screens.MainMenu;

import static mogbos.brix.StateController.gameState;

public class Brix extends Game {
	@Override
	public void create () {
		Gdx.graphics.setUndecorated(true);
		setScreen(new MainMenu());
	}

	@Override
	public void render () {
		if (StateController.stateChanged) {
			StateController.stateChanged = false;
			changeScreen();
		}
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}

	public void changeScreen() {
		switch (gameState){
			case MAIN_PAGE: setScreen(new MainMenu()); break;
			case GAME_PAGE: setScreen(new GameScreen()); break;
			default: break;
		}
	}
}
