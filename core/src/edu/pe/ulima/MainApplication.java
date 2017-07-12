package edu.pe.ulima;


import com.badlogic.gdx.ApplicationAdapter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.pe.ulima.stage.GameStateManager;
import edu.pe.ulima.stage.MenuState;


public class MainApplication extends ApplicationAdapter {

    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;

    public static final String TITLE = "Flappy Bird";

    private GameStateManager mGSM;
    private SpriteBatch batch;

    @Override
    public void create () {
        batch = new SpriteBatch();
        mGSM = new GameStateManager();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        mGSM.push(new MenuState(mGSM));
    }

    @Override
    public void render () {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mGSM.update(Gdx.graphics.getDeltaTime());
        mGSM.render(batch);
    }

    @Override
    public void dispose () {
        batch.dispose();
    }
}
