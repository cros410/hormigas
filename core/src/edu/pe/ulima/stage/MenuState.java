package edu.pe.ulima.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.pe.ulima.MainApplication;

public class MenuState extends State {
    private Texture texBackground;
    private Texture textPlayButton;
    private Texture textTitleButton;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        mCam.setToOrtho(false, MainApplication.WIDTH / 2, MainApplication.HEIGHT / 2);
        texBackground = new Texture("bg_game5.jpg");
        textTitleButton = new Texture("title.png");
        textPlayButton = new Texture("start1.png");
    }

    @Override
    public void handleInput() {

        if (Gdx.input.justTouched()) {
            mGSM.set(new PlayState(mGSM));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sp) {
        sp.setProjectionMatrix(mCam.combined);
        sp.begin();
        sp.draw(texBackground, 0, 0, mCam.viewportWidth, mCam.viewportHeight);
        sp.draw(textPlayButton, 0, 0, mCam.viewportWidth, mCam.viewportHeight / 4);
        /*sp.draw(texPlayButton,
                mCam.position.x - (texPlayButton.getWidth()/2) ,
                mCam.position.y
        );*/
        sp.draw(textTitleButton,0, (mCam.viewportHeight-(mCam.viewportHeight / 3))-10, mCam.viewportWidth , mCam.viewportHeight / 3);
        //sp.draw(texPlayButton,100,100);
        sp.end();
    }

    @Override
    public void dispose() {
        texBackground.dispose();
        textPlayButton.dispose();
        System.out.println("MenuState dispose");
    }
}
