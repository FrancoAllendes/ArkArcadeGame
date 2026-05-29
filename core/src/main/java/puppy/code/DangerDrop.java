package puppy.code;

import com.badlogic.gdx.graphics.Texture;

/**
 * Clase que representa un peligro que cae (garras de raptor, rocas, fuego).
 * Extiende DropItem. Al colisionar con el Survivor, le hace daño.
 * Aplica encapsulamiento (GM-5).
 */
public class DangerDrop extends DropItem {

    private int dañoInfligido;

    /**
     * Constructor de DangerDrop.
     * @param texture textura del peligro
     * @param velocidadY velocidad de caida
     * @param dañoInfligido cantidad de daño que inflige (vidas que quita)
     */
    public DangerDrop(Texture texture, float velocidadY, int dañoInfligido) {
        super(texture, velocidadY);
        this.dañoInfligido = dañoInfligido;
    }

    @Override
    public void onPlayerCollision(Survivor survivor) {
        // aplica daño tantas veces como dañoInfligido indique
        for (int i = 0; i < dañoInfligido; i++) {
            survivor.dañar();
        }
    }

    // ==================== Getters y Setters (GM-5) ====================

    public int getDañoInfligido() {
        return dañoInfligido;
    }

    public void setDañoInfligido(int dañoInfligido) {
        this.dañoInfligido = dañoInfligido;
    }
}