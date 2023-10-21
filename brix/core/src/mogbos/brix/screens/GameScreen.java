package mogbos.brix.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import mogbos.brix.utils.ColorCreator;
import mogbos.brix.utils.LevelParser;
import mogbos.brix.utils.level.Block;
import mogbos.brix.utils.level.Level;

public class GameScreen implements Screen, InputProcessor {
    ShapeRenderer shapeRenderer;

    Rectangle boardRect;

    Level level;

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

        // Creating board Rectangle
        boardRect = new Rectangle();
        boardRect.x = 10;
        boardRect.y = 40;
        boardRect.width = 300;
        boardRect.height = 30;

        // Controllers
        boardControl = BoardControl.IDL;
        boardSpeed = 2;

        // Making Level
        LevelParser levelParser = new LevelParser();
        level = levelParser.getLevel();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(ColorCreator.backgroundColor);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        /* Board */
        moveBoard();
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.rect(boardRect.x, boardRect.y, boardRect.width, boardRect.height);

        /* Blocks */
        // 495 starting height
        // 10 starting width
        for(int i = 0; i < level.getBlocks().size(); i++) {
            Block b = level.getBlocks().get(i);
            if(b.getStamina() == 0)
                break;
            else if(b.getStamina() == 1)
                shapeRenderer.setColor(236f/255, 238f/255, 129f/255, 1); // rgb(236, 238, 129)
            else if(b.getStamina() == 2)
                shapeRenderer.setColor(141f/255, 223f/255, 203f/255, 1); // rgb(141, 223, 203)
            else if(b.getStamina() == 3)
                shapeRenderer.setColor(130f/255, 160f/255, 216f/255, 1); // rgb(130, 160, 216)
            else if(b.getStamina() == 4)
                shapeRenderer.setColor(237f/255, 183f/255, 237f/255, 1); // rgb(237, 183, 237)

            float block_y = 720 - 495 + ((10-b.getRow()) * 45);
            float block_x = 10 + ((b.getColumn()-1) * 75);
            shapeRenderer.rect(block_x, block_y, 60f, 30f);
        }

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
        switch (boardControl) {
            case GO_LEFT: if (boardRect.x > 10) boardRect.x -= boardSpeed; break;
            case GO_RIGHT: if (boardRect.x < (Gdx.graphics.getWidth() - boardRect.getWidth() - 10))
                boardRect.x += boardSpeed; break;
            case IDL: break;
        }
    }
}
