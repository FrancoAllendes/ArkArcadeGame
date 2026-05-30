package puppy.code;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

/**
 * Clase que construye objetos DropItem paso a paso.
 * Implementa el patron Builder.
 */
public class DropBuilder {

    private Texture texture;
    private float velocidadY;
    private MovementStrategy strategy;
    private Sound sonido;
    private int puntos;
    private int daño;
    private boolean esPeligro;

    /**
     * Constructor del Builder con valores por defecto.
     */
    public DropBuilder() {
        this.velocidadY = 300f;
        this.strategy = new NormalFall();
        this.puntos = 10;
        this.daño = 1;
        this.esPeligro = false;
    }

    /**
     * Establece la textura del drop.
     * @param texture la textura a usar
     * @return este Builder para encadenar llamadas
     */
    public DropBuilder conTextura(Texture texture) {
        this.texture = texture;
        return this;
    }

    /**
     * Establece la velocidad de caida.
     * @param velocidadY la velocidad de caida
     * @return este Builder para encadenar llamadas
     */
    public DropBuilder conVelocidad(float velocidadY) {
        this.velocidadY = velocidadY;
        return this;
    }

    /**
     * Establece la estrategia de movimiento.
     * @param strategy la estrategia de movimiento
     * @return este Builder para encadenar llamadas
     */
    public DropBuilder conEstrategia(MovementStrategy strategy) {
        this.strategy = strategy;
        return this;
    }

    /**
     * Establece el sonido de recoleccion (solo para recursos).
     * @param sonido el sonido al recolectar
     * @return este Builder para encadenar llamadas
     */
    public DropBuilder conSonido(Sound sonido) {
        this.sonido = sonido;
        return this;
    }

    /**
     * Establece los puntos que otorga (solo para recursos).
     * @param puntos los puntos a otorgar
     * @return este Builder para encadenar llamadas
     */
    public DropBuilder conPuntos(int puntos) {
        this.puntos = puntos;
        return this;
    }

    /**
     * Establece el daño que inflige (solo para peligros).
     * @param daño el daño a infligir
     * @return este Builder para encadenar llamadas
     */
    public DropBuilder conDaño(int daño) {
        this.daño = daño;
        return this;
    }

    /**
     * Define que el drop sera un peligro.
     * @return este Builder para encadenar llamadas
     */
    public DropBuilder comoPeligro() {
        this.esPeligro = true;
        return this;
    }

    /**
     * Define que el drop sera un recurso.
     * @return este Builder para encadenar llamadas
     */
    public DropBuilder comoRecurso() {
        this.esPeligro = false;
        return this;
    }

    /**
     * Construye el DropItem final con la configuracion establecida.
     * @return un ResourceDrop o DangerDrop segun la configuracion
     */
    public DropItem build() {
        DropItem drop;
        if (esPeligro) {
            drop = new DangerDrop(texture, velocidadY, daño);
        } else {
            drop = new ResourceDrop(texture, velocidadY, sonido, puntos);
        }
        drop.setMovementStrategy(strategy);
        return drop;
    }

    // ==================== Metodos de fabrica rapidos ====================

    /**
     * Crea un recurso con configuracion predeterminada.
     * @param texture textura del recurso
     * @param velocidad velocidad de caida
     * @param sonido sonido al recolectar
     * @return un ResourceDrop configurado
     */
    public static DropItem crearRecurso(Texture texture, float velocidad, Sound sonido) {
        return new DropBuilder()
                .conTextura(texture)
                .conVelocidad(velocidad)
                .conSonido(sonido)
                .conPuntos(10)
                .comoRecurso()
                .conEstrategia(new NormalFall())
                .build();
    }

    /**
     * Crea un peligro con configuracion predeterminada y estrategia aleatoria.
     * @param texture textura del peligro
     * @param velocidad velocidad de caida
     * @return un DangerDrop configurado
     */
    public static DropItem crearPeligro(Texture texture, float velocidad) {
        // asignar estrategia aleatoria para variedad
        MovementStrategy strategy;
        int random = MathUtils.random(1, 3);
        if (random == 1) {
            strategy = new ZigZagFall();
        } else if (random == 2) {
            strategy = new FastFall();
        } else {
            strategy = new NormalFall();
        }

        return new DropBuilder()
                .conTextura(texture)
                .conVelocidad(velocidad)
                .comoPeligro()
                .conDaño(1)
                .conEstrategia(strategy)
                .build();
    }
}