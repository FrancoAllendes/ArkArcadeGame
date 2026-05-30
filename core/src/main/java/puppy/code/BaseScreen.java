package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Clase abstracta que define la estructura base de todas las pantallas.
 * Implementa el patron Template Method.
 */
public abstract class BaseScreen implements Screen {

    protected SpriteBatch batch;
    protected BitmapFont font;
    protected OrthographicCamera camera;
    protected GameLluvia game;

    /**
     * Constructor de BaseScreen.
     * @param game referencia al juego principal
     */
    public BaseScreen(GameLluvia game) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 800, 480);
    }

    /**
     * METODO TEMPLATE: define la estructura fija del render.
     * Los pasos se ejecutan siempre en este orden.
     * Las subclases implementan cada paso segun su necesidad.
     */
    protected final void renderScreen(float delta) {
        // Paso 1: limpiar la pantalla
        clearScreen();
        // Paso 2: actualizar la camara
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        // Paso 3: procesar entrada del usuario
        handleInput();
        // Paso 4: actualizar la logica
        update(delta);
        // Paso 5: dibujar elementos
        batch.begin();
        draw(batch);
        drawUI(batch);
        batch.end();
    }

    /**
     * Limpia la pantalla con un color base.
     * Las subclases pueden sobreescribir para cambiar el color de fondo.
     */
    protected void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    /**
     * Procesa la entrada del usuario (teclado, mouse, touch).
     * Cada pantalla define su propia logica de input.
     */
    protected abstract void handleInput();

    /**
     * Actualiza la logica del juego.
     * @param delta tiempo desde el ultimo frame
     */
    protected abstract void update(float delta);

    /**
     * Dibuja los elementos graficos de la pantalla.
     * @param batch el SpriteBatch para dibujar
     */
    protected abstract void draw(SpriteBatch batch);

    /**
     * Dibuja la interfaz de usuario (texto, puntaje, vidas).
     * @param batch el SpriteBatch para dibujar
     */
    protected abstract void drawUI(SpriteBatch batch);

    // ==================== Metodos de Screen ====================

    @Override
    public void render(float delta) {
        // llama al metodo template
        renderScreen(delta);
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
}