package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Pantalla de Game Over que muestra el puntaje final.
 * Extiende BaseScreen aplicando Template Method.
 */
public class GameOverScreen extends BaseScreen {

    /**
     * Constructor de GameOverScreen.
     * @param game referencia al juego principal
     */
    public GameOverScreen(GameLluvia game) {
        super(game);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            game.setScreen(new MenuScreen(game));
            dispose();
        }
    }

    @Override
    protected void update(float delta) {
    }

    @Override
    protected void draw(SpriteBatch batch) {
    }

    @Override
    protected void drawUI(SpriteBatch batch) {
        GameManager gm = GameManager.getInstance();
        font.draw(batch, "GAME OVER", 330, 320);
        font.draw(batch, "Has sido devorado!", 300, 280);
        font.draw(batch, "Puntaje final: " + gm.getPuntaje(), 300, 240);
        font.draw(batch, "Nivel alcanzado: " + gm.getNivel(), 300, 200);
        font.draw(batch, "Presiona ENTER para volver al menu", 220, 130);
    }

    @Override
    public void show() {
    }
}