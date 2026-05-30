package puppy.code;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Clase principal del juego. Extiende Game para poder usar multiples pantallas.
 * Ahora delega la logica a las distintas Screens.
 */
public class GameLluvia extends Game {

    private SpriteBatch batch;
    private BitmapFont font;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        // iniciar en la pantalla de menu
        this.setScreen(new MenuScreen(this));
    }

    @Override
    public void render() {
        super.render(); // delega el render a la pantalla activa
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    // ==================== Getters ====================

    public SpriteBatch getBatch() {
        return batch;
    }

    public BitmapFont getFont() {
        return font;
    }
}