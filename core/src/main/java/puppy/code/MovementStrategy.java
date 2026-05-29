package puppy.code;

/**
 * Interfaz que define la estrategia de movimiento de un DropItem.
 * Implementa el patron Strategy.
 */
public interface MovementStrategy {

    /**
     * Aplica el movimiento al DropItem.
     * @param drop el objeto que se mueve
     * @param deltaTime tiempo transcurrido desde el ultimo frame
     */
    void mover(DropItem drop, float deltaTime);
}