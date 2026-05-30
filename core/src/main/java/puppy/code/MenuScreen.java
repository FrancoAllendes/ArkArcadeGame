package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Pantalla del menu principal del juego.
 * Extiende BaseScreen aplicando Template Method.
 */
public class MenuScreen extends BaseScreen {

    /**
     * Constructor de MenuScreen.
     * @param game referencia al juego principal
     */
    public MenuScreen(GameLluvia game) {
        super(game);
    }

    @Override
    protected void handleInput() {
        // al presionar ENTER o hacer click, inicia el juego
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) || Gdx.input.justTouched()) {
            GameManager.getInstance().resetear();
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    protected void update(float delta) {
        // el menu no tiene logica de actualizacion
    }

    @Override
    protected void draw(SpriteBatch batch) {
        // el menu solo muestra texto en drawUI
    }

    @Override
    protected void drawUI(SpriteBatch batch) {
        font.draw(batch, "ARK ARCADE", 320, 300);
        font.draw(batch, "Sobrevive recolectando recursos", 230, 250);
        font.draw(batch, "y esquivando dinosaurios!", 260, 220);
        font.draw(batch, "Presiona ENTER para jugar", 260, 150);
    }

    @Override
    public void show() {
    }
}