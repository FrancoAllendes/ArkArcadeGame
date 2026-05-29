package puppy.code;

/**
 * Clase que gestiona el estado global del juego.
 * Implementa el patron Singleton.
 */
public class GameManager {

    // instancia unica del Singleton
    private static GameManager instance;

    private int puntaje;
    private int vidas;
    private int nivel;
    private float velocidadBase;
    private float intervaloSpawn;
    private boolean juegoActivo;

    /**
     * Constructor privado para evitar instanciacion externa.
     */
    private GameManager() {
        resetear();
    }

    /**
     * Obtiene la instancia unica de GameManager.
     * Si no existe, la crea.
     * @return la instancia unica de GameManager
     */
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    /**
     * Reinicia el estado del juego a valores iniciales.
     */
    public void resetear() {
        this.puntaje = 0;
        this.vidas = 3;
        this.nivel = 1;
        this.velocidadBase = 300f;
        this.intervaloSpawn = 100000000f;
        this.juegoActivo = true;
    }

    /**
     * Suma puntos al puntaje global.
     * @param puntos cantidad de puntos a sumar
     */
    public void sumarPuntaje(int puntos) {
        this.puntaje += puntos;
        verificarNivel();
    }

    /**
     * Reduce una vida. Si las vidas llegan a 0, termina el juego.
     */
    public void perderVida() {
        if (vidas > 0) {
            vidas--;
        }
        if (vidas <= 0) {
            juegoActivo = false;
        }
    }

    /**
     * Verifica si el jugador sube de nivel segun el puntaje.
     * Cada 50 puntos sube un nivel y aumenta la dificultad.
     */
    private void verificarNivel() {
        int nuevoNivel = (puntaje / 50) + 1;
        if (nuevoNivel > nivel) {
            nivel = nuevoNivel;
            aumentarDificultad();
        }
    }

    /**
     * Aumenta la dificultad del juego al subir de nivel.
     * Incrementa la velocidad de caida y reduce el tiempo entre spawns.
     */
    private void aumentarDificultad() {
        velocidadBase += 30f;
        intervaloSpawn = Math.max(intervaloSpawn * 0.9f, 30000000f);
    }

    // ==================== Getters y Setters (GM-5) ====================

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public float getVelocidadBase() {
        return velocidadBase;
    }

    public void setVelocidadBase(float velocidadBase) {
        this.velocidadBase = velocidadBase;
    }

    public float getIntervaloSpawn() {
        return intervaloSpawn;
    }

    public void setIntervaloSpawn(float intervaloSpawn) {
        this.intervaloSpawn = intervaloSpawn;
    }

    public boolean isJuegoActivo() {
        return juegoActivo;
    }

    public void setJuegoActivo(boolean juegoActivo) {
        this.juegoActivo = juegoActivo;
    }
}