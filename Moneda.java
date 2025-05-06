import java.util.Map;

public record Moneda(String base_code, Map<String, Double> conversion_rates) {
    //Se utiliza Map para mapear los pares de monedas