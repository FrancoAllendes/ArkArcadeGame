package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Pantalla principal donde ocurre el juego.
 * Extiende BaseScreen aplicando Template Method (GM-8).
 * Reemplaza la logica de la clase Lluvia original.
 */
public class GameScreen extends BaseScreen {

    private Survivor survivor;
    private Array<DropItem> drops;
    private long lastDropTime;

    // texturas
    private Texture survivorTexture;
    private Texture recursoTexture;
    private Texture peligroTexture;

    // sonidos
    private Sound sonidoRecolectar;
    private Sound sonidoDaño;
    private Music musicaFondo;

    /**
     * Constructor de GameScreen.
     * @param game referencia al juego principal
     */
    public GameScreen(GameLluvia game) {
        super(game);
    }

    @Override
    public void show() {
        // cargar texturas
        survivorTexture = new Texture(Gdx.files.internal("survivor.png"));
        recursoTexture = new Texture(Gdx.files.internal("recurso.png"));
        peligroTexture = new Texture(Gdx.files.internal("peligro.png"));

        // cargar sonidos
        sonidoRecolectar = Gdx.audio.newSound(Gdx.files.internal("recolectar.mp3"));
        sonidoDaño = Gdx.audio.newSound(Gdx.files.internal("danio.mp3"));
        musicaFondo = Gdx.audio.newMusic(Gdx.files.internal("musica_fondo.mp3"));

        // iniciar musica de fondo
        musicaFondo.setLooping(true);
        musicaFondo.play();

        // crear survivor
        survivor = new Survivor(survivorTexture, sonidoDaño);

        // crear lista de drops
        drops = new Array<>();
        spawnDrop();
    }

    /**
     * Genera un nuevo drop aleatorio usando el Builder (GM-9).
     */
    private void spawnDrop() {
        GameManager gm = GameManager.getInstance();
        DropItem drop;

        if (MathUtils.random(1, 10) < 3) {
            // 20% probabilidad de peligro
            drop = DropBuilder.crearPeligro(peligroTexture, gm.getVelocidadBase());
        } else {
            // 80% probabilidad de recurso
            drop = DropBuilder.crearRecurso(recursoTexture, gm.getVelocidadBase(), sonidoRecolectar);
        }

        drops.add(drop);
        lastDropTime = TimeUtils.nanoTime();
    }

    @Override
    protected void handleInput() {
        // el input del survivor se maneja en su metodo update()
    }

    @Override
    protected void update(float delta) {
        GameManager gm = GameManager.getInstance();

        // actualizar survivor
        survivor.update();

        // generar nuevos drops segun el intervalo del GameManager
        if (TimeUtils.nanoTime() - lastDropTime > gm.getIntervaloSpawn()) {
            spawnDrop();
        }

        // actualizar drops y verificar colisiones
        for (int i = 0; i < drops.size; i++) {
            DropItem drop = drops.get(i);
            drop.update();

            // verificar si cayo fuera de pantalla
            if (drop.fueraDePantalla()) {
                drops.removeIndex(i);
                i--;
                continue;
            }

            // verificar colision con survivor usando Collidable (GM-4)
            if (drop.checkCollision(survivor)) {
                drop.onPlayerCollision(survivor);
                // sincronizar puntaje y vidas con GameManager
                gm.setPuntaje(survivor.getPuntos());
                gm.setVidas(survivor.getVidas());
                drops.removeIndex(i);
                i--;
            }
        }

        // verificar si el jugador murio
        if (!survivor.estaVivo()) {
            musicaFondo.stop();
            game.setScreen(new GameOverScreen(game));
            dispose();
        }
    }

    @Override
    protected void draw(SpriteBatch batch) {
        // dibujar survivor
        survivor.draw(batch);

        // dibujar drops
        for (DropItem drop : drops) {
            drop.draw(batch);
        }
    }

    @Override
    protected void drawUI(SpriteBatch batch) {
        GameManager gm = GameManager.getInstance();
        font.draw(batch, "Puntos: " + gm.getPuntaje(), 10, 470);
        font.draw(batch, "Vidas: " + gm.getVidas(), 710, 470);
        font.draw(batch, "Nivel: " + gm.getNivel(), 370, 470);
    }

    @Override
    public void dispose() {
        musicaFondo.dispose();
        sonidoRecolectar.dispose();
        sonidoDaño.dispose();
        survivorTexture.dispose();
        recursoTexture.dispose();
        peligroTexture.dispose();
    }
}