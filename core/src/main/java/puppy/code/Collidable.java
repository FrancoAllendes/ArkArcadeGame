package puppy.code;

import com.badlogic.gdx.math.Rectangle;

/**
 * Interfaz que define el comportamiento de objetos que pueden colisionar.
 * Implementada por Survivor y DropItem (GM-4).
 */
public interface Collidable {

    /**
     * Retorna el area de colision del objeto.
     * @return Rectangle que representa el hitbox del objeto
     */
    Rectangle getHitbox();

    /**
     * Verifica si este objeto colisiona con otro objeto Collidable.
     * @param otro el otro objeto con el que se verifica la colision
     * @return true si hay colision, false en caso contrario
     */
    default boolean checkCollision(Collidable otro) {
        return getHitbox().overlaps(otro.getHitbox());
    }
}