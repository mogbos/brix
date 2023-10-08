package mogbos.brix.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import mogbos.brix.utils.ColorCreator;

public class GameScreen implements Screen, InputProcessor {
    ShapeRenderer shapeRenderer;

    Rectangle boardRect;

    enum BoardControl {
        IDL, GO_LEFT, GO_RIGHT
    }

    BoardControl boardControl;
    float boardSpeed;

    /* Screen part */

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);

        // Creating shapeRenderer
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setColor(1, 1, 1, 1);

        // Creating board Rectangle
        boardRect = new Rectangle();
        boardRect.x = 10;
        boardRect.y = 40;
        boardRect.width = 300;
        boardRect.height = 30;

        // Controllers
        boardControl = BoardControl.IDL;
        boardSpeed = 2;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(ColorCreator.backgroundColor);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        moveBoard();

        shapeRenderer.rect(boardRect.x, boardRect.y, boardRect.width, boardRect.height);

        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    /* InputProcessor part */

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT: boardControl = BoardControl.GO_LEFT; break;
            case Input.Keys.RIGHT: boardControl = BoardControl.GO_RIGHT; break;
            default: return false;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (boardControl == BoardControl.GO_LEFT && keycode == Input.Keys.LEFT) {
            boardControl = BoardControl.IDL;
        } else if (boardControl == BoardControl.GO_RIGHT && keycode == Input.Keys.RIGHT) {
            boardControl = BoardControl.IDL;
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    /* Action */
    private void moveBoard(){
        System.out.println((Gdx.graphics.getWidth() - 10));
        switch (boardControl) {
            case GO_LEFT: if (boardRect.x > 10) boardRect.x -= boardSpeed; break;
            case GO_RIGHT: if (boardRect.x < (Gdx.graphics.getWidth() - boardRect.getWidth() - 10))
                boardRect.x += boardSpeed; break;
            case IDL: break;
        }
    }
}
