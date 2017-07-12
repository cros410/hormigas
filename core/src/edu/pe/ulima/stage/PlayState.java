package edu.pe.ulima.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import edu.pe.ulima.MainApplication;
import edu.pe.ulima.sprites.Hormiga;
import edu.pe.ulima.sprites.Hormigon;


public class PlayState extends State {

    private Texture mBackground;
    private Array<Hormiga> hormigaList;
    private Array<Hormigon> hormigonList;
    private int INTERVALO = 2;
    private float acumulador_hormiga;
    private float acum_dispose;
    private float acumulador_hormigon;


    protected PlayState(GameStateManager gsm) {
        super(gsm);
        mBackground = new Texture("bg_game1.jpg");
        hormigaList = new Array<Hormiga>();
        addHormiga();
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            Vector3 vc = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            mCam.unproject(vc);
            for (int i = 0; i < hormigaList.size; i++) {
                Hormiga hormiga = hormigaList.get(i);
                if (hormiga.getBounds() != null) {
                    if (hormiga.getBounds().contains(vc.x, vc.y)) {
                        hormiga.setLife(false);
                    }
                }
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        acumulador_hormigon += dt;
        acumulador_hormiga += dt;
        acum_dispose+= dt;

        if (acumulador_hormiga > INTERVALO) {
            addHormiga();
            acumulador_hormiga = 0.0f;
        }
        if (acumulador_hormigon > INTERVALO+4) {
            addHormigon();
            acumulador_hormigon = 0.0f;
        }
        if (acum_dispose > INTERVALO+3) {
            hide();
            acum_dispose = 0.0f;
        }
        for (int i = 0; i < hormigaList.size; i++) {
            Hormiga hormiga = hormigaList.get(i);
            hormiga.update(dt);
        }
        mCam.update();
    }

    @Override
    public void render(SpriteBatch sp) {
        sp.setProjectionMatrix(mCam.combined);
        sp.begin();
        sp.draw(mBackground,
                mCam.position.x - (mCam.viewportWidth / 2), 0,
                MainApplication.WIDTH / 2, MainApplication.HEIGHT / 2);
        for (int i = 0; i < hormigaList.size; i++) {
            Hormiga hormiga = hormigaList.get(i);
            sp.draw(hormiga.getTexture(), hormiga.getPosicion().x, hormiga.getPosicion().y);
        }

        for (int i = 0; i < hormigonList.size; i++) {
            Hormigon hormigon = hormigonList.get(i);
            sp.draw(hormigon.getTexture(), hormigon.getPosicion().x, hormigon.getPosicion().y);
        }

        sp.end();
    }

    @Override
    public void dispose() {
        for (int i = 0; i < hormigaList.size; i++) {
            Hormiga hormiga = hormigaList.get(i);
            if (!hormiga.getLife()) {
                hormiga.dipose();
            }
        }
        for (int i = 0; i < hormigonList.size; i++) {
            Hormigon hormigon = hormigonList.get(i);
            if (!hormigon.getLife()) {
                hormigon.dipose();
            }
        }
    }

    public void hide() {
        for (int i = 0; i < hormigaList.size; i++) {
            Hormiga hormiga = hormigaList.get(i);
            if (!hormiga.getLife()) {
                hormiga.hide();
            }
        }
        for (int i = 0; i < hormigonList.size; i++) {
            Hormigon hormigon = hormigonList.get(i);
            if (!hormigon.getLife()) {
                hormigon.hide();
            }
        }
    }

    public void addHormiga() {
        Hormiga hormiga;
        long rd = Math.round(Math.random() * (mCam.viewportWidth-80))+40;
        hormiga = new Hormiga((int) rd, 400);
        hormigaList.add(hormiga);
    }

    public void addHormigon() {
        Hormigon hormigon;
        long rd = Math.round(Math.random() * (mCam.viewportWidth-80))+40;
        hormigon = new Hormigon((int) rd, 400);
        hormigonList.add(hormigon);
    }


}
