import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class HistorialConv {
    private final String nombreArchivo = "historial_conversiones.txt";

    public void guardar(RegistroConversion registro) {
        try (FileWriter writer = new FileWriter(nombreArchivo, true)) {
            writer.write(registro + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error al guardar en el historial: " + e.getMessage());
        }
    }

    public void mostrarHistorial() {
        try {
            List<String> lineas = Files.readAllLines(Path.of(nombreArchivo));
            if (lineas.isEmpty()) {
                System.out.println("No hay conversiones registradas.");
            } else {
                System.out.println("Historial de conversiones:");
                lineas.forEach(System.out::println);
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer el historial: " + e.getMessage());
        }
    }
}
