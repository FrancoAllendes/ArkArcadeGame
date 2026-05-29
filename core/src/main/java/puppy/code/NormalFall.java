package puppy.code;

/**
 * Estrategia de caida normal en linea recta.
 * Implementa MovementStrategy.
 */
public class NormalFall implements MovementStrategy {

    @Override
    public void mover(DropItem drop, float deltaTime) {
        drop.setY(drop.getY() - drop.getVelocidadY() * deltaTime);
    }
}