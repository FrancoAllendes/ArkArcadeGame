package puppy.code;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 * Clase que representa un recurso recolectable (carne, berries, madera).
 * Extiende DropItem. Al colisionar con el Survivor, suma puntos.
 * Aplica encapsulamiento (GM-5).
 */
public class ResourceDrop extends DropItem {

    private Sound sonidoRecoleccion;
    private int puntosOtorgados;

    /**
     * Constructor de ResourceDrop.
     * @param texture textura del recurso
     * @param velocidadY velocidad de caida
     * @param sonidoRecoleccion sonido al ser recolectado
     * @param puntosOtorgados puntos que otorga al recolectar
     */
    public ResourceDrop(Texture texture, float velocidadY, Sound sonidoRecoleccion, int puntosOtorgados) {
        super(texture, velocidadY);
        this.sonidoRecoleccion = sonidoRecoleccion;
        this.puntosOtorgados = puntosOtorgados;
    }

    @Override
    public void onPlayerCollision(Survivor survivor) {
        survivor.sumarPuntos(puntosOtorgados);
        sonidoRecoleccion.play();
    }

    // ==================== Getters y Setters (GM-5) ====================

    public Sound getSonidoRecoleccion() {
        return sonidoRecoleccion;
    }

    public void setSonidoRecoleccion(Sound sonidoRecoleccion) {
        this.sonidoRecoleccion = sonidoRecoleccion;
    }

    public int getPuntosOtorgados() {
        return puntosOtorgados;
    }

    public void setPuntosOtorgados(int puntosOtorgados) {
        this.puntosOtorgados = puntosOtorgados;
    }
}