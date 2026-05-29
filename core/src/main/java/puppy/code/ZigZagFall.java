package puppy.code;

import com.badlogic.gdx.math.MathUtils;

/**
 * Estrategia de caida en zigzag, como un pteranodon planeando.
 * Implementa MovementStrategy.
 */
public class ZigZagFall implements MovementStrategy {

    private float tiempo;
    private float amplitud;
    private float frecuencia;

    public ZigZagFall() {
        this.tiempo = 0;
        this.amplitud = 100f;
        this.frecuencia = 3f;
    }

    @Override
    public void mover(DropItem drop, float deltaTime) {
        tiempo += deltaTime;
        // movimiento vertical hacia abajo
        drop.setY(drop.getY() - drop.getVelocidadY() * deltaTime);
        // movimiento horizontal en zigzag usando funcion seno
        float desplazamiento = MathUtils.sin(tiempo * frecuencia) * amplitud * deltaTime;
        float nuevaX = drop.getX() + desplazamiento;
        // mantener dentro de los bordes de la pantalla
        if (nuevaX < 0) nuevaX = 0;
        if (nuevaX > 800 - drop.getWidth()) nuevaX = 800 - drop.getWidth();
        drop.setX(nuevaX);
    }
}