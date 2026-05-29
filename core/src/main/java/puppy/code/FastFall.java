package puppy.code;

/**
 * Estrategia de caida rapida, como un meteoro o roca volcánica.
 * Implementa MovementStrategy.
 * Cae al doble de la velocidad normal.
 */
public class FastFall implements MovementStrategy {

    private float multiplicador;

    public FastFall() {
        this.multiplicador = 2.0f;
    }

    @Override
    public void mover(DropItem drop, float deltaTime) {
        drop.setY(drop.getY() - drop.getVelocidadY() * multiplicador * deltaTime);
    }
}