package puppy.code;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Clase abstracta que representa cualquier objeto del juego.
 * Es padre de Survivor y DropItem (GM-4).
 * Aplica encapsulamiento con atributos privados y getters/setters (GM-5).
 */
public abstract class GameObject implements Collidable {

    private float x;
    private float y;
    private int width;
    private int height;
    private Texture texture;
    private Rectangle hitbox;

    /**
     * Constructor de GameObject.
     * @param texture la textura del objeto
     * @param x posicion horizontal inicial
     * @param y posicion vertical inicial
     * @param width ancho del objeto
     * @param height alto del objeto
     */
    public GameObject(Texture texture, float x, float y, int width, int height) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hitbox = new Rectangle(x, y, width, height);
    }

    // ==================== Metodos abstractos ====================

    /**
     * Dibuja el objeto en pantalla.
     * @param batch el SpriteBatch para dibujar
     */
    public abstract void draw(SpriteBatch batch);

    /**
     * Actualiza la logica del objeto (movimiento, estado, etc).
     */
    public abstract void update();

    /**
     * Libera los recursos del objeto (texturas, sonidos).
     */
    public abstract void dispose();

    // ==================== Metodos concretos ====================

    /**
     * Actualiza la posicion del hitbox para que coincida con x e y.
     */
    protected void updateHitbox() {
        hitbox.setPosition(x, y);
    }

    // ==================== Implementacion de Collidable ====================

    @Override
    public Rectangle getHitbox() {
        return hitbox;
    }

    // ==================== Getters y Setters (GM-5) ====================

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
        hitbox.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
        hitbox.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        hitbox.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        hitbox.height = height;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}