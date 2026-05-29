package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

/**
 * Clase abstracta que representa un objeto que cae desde el cielo.
 * Extiende GameObject (GM-4) y aplica encapsulamiento (GM-5).
 * Utiliza el patron Strategy (GM-7) para definir su movimiento.
 * Es padre de ResourceDrop y DangerDrop.
 */
public abstract class DropItem extends GameObject {

    private float velocidadY;
    private MovementStrategy movementStrategy;

    /**
     * Constructor de DropItem.
     * @param texture textura del objeto que cae
     * @param velocidadY velocidad de caida
     */
    public DropItem(Texture texture, float velocidadY) {
        super(texture, MathUtils.random(0, 800 - 64), 480, 64, 64);
        this.velocidadY = velocidadY;
        this.movementStrategy = new NormalFall(); // estrategia por defecto
    }

    /**
     * Define que ocurre cuando este objeto colisiona con el Survivor.
     * Cada tipo de drop implementa su propio efecto.
     * @param survivor el superviviente con el que colisiona
     */
    public abstract void onPlayerCollision(Survivor survivor);

    /**
     * Verifica si el objeto cayo fuera de la pantalla.
     * @return true si ya salio por debajo de la pantalla
     */
    public boolean fueraDePantalla() {
        return getY() + getHeight() < 0;
    }

    // ==================== Metodos de GameObject ====================

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(getTexture(), getX(), getY());
    }

    @Override
    public void update() {
        // delega el movimiento a la estrategia asignada (GM-7)
        movementStrategy.mover(this, Gdx.graphics.getDeltaTime());
    }

    @Override
    public void dispose() {
        getTexture().dispose();
    }

    // ==================== Getters y Setters (GM-5) ====================

    public float getVelocidadY() {
        return velocidadY;
    }

    public void setVelocidadY(float velocidadY) {
        this.velocidadY = velocidadY;
    }

    public MovementStrategy getMovementStrategy() {
        return movementStrategy;
    }

    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }
}