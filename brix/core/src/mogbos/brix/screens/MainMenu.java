package mogbos.brix.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import mogbos.brix.StateController;
import mogbos.brix.utils.ColorCreator;
import mogbos.brix.utils.FontCreator;
import mogbos.brix.utils.TextResources;

import static mogbos.brix.StateController.setGameState;

public class MainMenu implements Screen {

    SpriteBatch batch;
    BitmapFont titleFont;
    BitmapFont buttonFont;

    Label titleLabel;
    Stage buttonStage;
    TextButton startButton;
    TextButton quitButton;

    @Override
    public void show() {
        // Get fonts
        titleFont = FontCreator.getTitilliumWebRegular(180, ColorCreator.titleColor);
        buttonFont = FontCreator.getTitilliumWebRegular(70, ColorCreator.buttonTextColor);

        // Title
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = titleFont;
        titleLabel = new Label(TextResources.gameName, labelStyle);
        titleLabel.setPosition((Gdx.graphics.getWidth() - titleLabel.getWidth()) / 2f, 370);

        // Style for buttons
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = buttonFont;
        buttonStage = new Stage();
        Gdx.input.setInputProcessor(buttonStage);

        // Start button
        startButton = new TextButton(TextResources.start, buttonStyle);
        startButton.setPosition((Gdx.graphics.getWidth() - startButton.getWidth()) / 2f, 250);
        buttonStage.addActor(startButton);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setGameState(StateController.GameState.GAME_PAGE);
            }
        });
        startButton.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer,
                              com.badlogic.gdx.scenes.scene2d.Actor fromActor) {
                startButton.getLabel().setColor(ColorCreator.buttonTextOnHoverColor);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer,
                             com.badlogic.gdx.scenes.scene2d.Actor toActor) {
                startButton.getLabel().setColor(Color.WHITE);
            }
        });

        // Quit button
        quitButton = new TextButton(TextResources.quit, buttonStyle);
        quitButton.setPosition((Gdx.graphics.getWidth() - quitButton.getWidth()) / 2f, 150);
        buttonStage.addActor(quitButton);
        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.exit(0);
            }
        });
        quitButton.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer,
                              com.badlogic.gdx.scenes.scene2d.Actor fromActor) {
                quitButton.getLabel().setColor(ColorCreator.buttonTextOnHoverColor);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer,
                             com.badlogic.gdx.scenes.scene2d.Actor toActor) {
                quitButton.getLabel().setColor(Color.WHITE);
            }
        });

        // Create new batch
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(ColorCreator.backgroundColor);
        batch.begin();

        // Title
        titleLabel.draw(batch, 1);

        // Start and quit button
        buttonStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));
        buttonStage.draw();

        batch.end();
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
        batch.dispose();
        titleFont.dispose();
        buttonFont.dispose();
        buttonStage.dispose();
    }
}
