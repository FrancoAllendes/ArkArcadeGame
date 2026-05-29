package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

/**
 * Clase que representa al superviviente (jugador) en el juego.
 * Extiende GameObject (GM-4) y aplica encapsulamiento (GM-5).
 * Reemplaza a la clase Tarro del juego base.
 */
public class Survivor extends GameObject {

    private Sound sonidoHerido;
    private int vidas;
    private int puntos;
    private int velocidadX;
    private boolean herido;
    private int tiempoHeridoMax;
    private int tiempoHerido;

    /**
     * Constructor del Survivor.
     * @param texture textura del superviviente
     * @param sonidoHerido sonido al recibir daño
     */
    public Survivor(Texture texture, Sound sonidoHerido) {
        super(texture, 800 / 2 - 64 / 2, 20, 64, 64);
        this.sonidoHerido = sonidoHerido;
        this.vidas = 3;
        this.puntos = 0;
        this.velocidadX = 400;
        this.herido = false;
        this.tiempoHeridoMax = 50;
        this.tiempoHerido = 0;
    }

    /**
     * Aplica daño al superviviente. No permite vidas negativas.
     * Corrige el bug del juego base donde las vidas podian ser negativas.
     */
    public void dañar() {
        if (vidas > 0) {
            vidas--;
            herido = true;
            tiempoHerido = tiempoHeridoMax;
            sonidoHerido.play();
        }
    }

    /**
     * Suma puntos al puntaje del superviviente.
     * @param cantidad cantidad de puntos a sumar
     */
    public void sumarPuntos(int cantidad) {
        puntos += cantidad;
    }

    /**
     * Verifica si el superviviente sigue con vida.
     * @return true si tiene vidas restantes
     */
    public boolean estaVivo() {
        return vidas > 0;
    }

    /**
     * Verifica si el superviviente esta en estado herido.
     * @return true si esta herido
     */
    public boolean estaHerido() {
        return herido;
    }

    // ==================== Metodos de GameObject ====================

    @Override
    public void draw(SpriteBatch batch) {
        if (!herido) {
            batch.draw(getTexture(), getX(), getY());
        } else {
            // efecto de temblor cuando esta herido
            batch.draw(getTexture(), getX(), getY() + MathUtils.random(-5, 5));
            tiempoHerido--;
            if (tiempoHerido <= 0) {
                herido = false;
            }
        }
    }

    @Override
    public void update() {
        // movimiento desde teclado
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            setX(getX() - velocidadX * Gdx.graphics.getDeltaTime());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            setX(getX() + velocidadX * Gdx.graphics.getDeltaTime());
        }

        // que no se salga de los bordes izquierdo y derecho
        if (getX() < 0) {
            setX(0);
        }
        if (getX() > 800 - getWidth()) {
            setX(800 - getWidth());
        }
    }

    @Override
    public void dispose() {
        getTexture().dispose();
    }

    // ==================== Getters y Setters (GM-5) ====================

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getVelocidadX() {
        return velocidadX;
    }

    public void setVelocidadX(int velocidadX) {
        this.velocidadX = velocidadX;
    }
}