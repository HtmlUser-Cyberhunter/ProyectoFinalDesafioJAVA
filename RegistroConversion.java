import java.time.LocalDateTime;

public class RegistroConversion {
    private final String monedaOrigen;
    private final String monedaDestino;
    private final double cantidadOriginal;
    private final double resultado;
    private final LocalDateTime fechaHora;

    public RegistroConversion(String monedaOrigen, String monedaDestino, double cantidadOriginal, double resultado) {
        this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
        this.cantidadOriginal = cantidadOriginal;
        this.resultado = resultado;
        this.fechaHora = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return String.format("[%s] %.2f %s → %.2f %s",
                fechaHora,
                cantidadOriginal,
                monedaOrigen,
                resultado,
                monedaDestino);
    }
}
