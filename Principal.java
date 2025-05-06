import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Principal {
    public static void main(String[] args) {
        ConsultaMoneda consulta = new ConsultaMoneda();
        Scanner lectura = new Scanner(System.in);
        boolean continuar = true;
        List<RegistroConversion> historial = new ArrayList<>();
        HistorialConv historialArchivo = new HistorialConv();


        while (continuar) {
            System.out.println("Bienvenid@ al conversor de Monedas.");
            System.out.println("Seleccione el cambio que desea realizar:");
            System.out.println("***********************************************************");
            System.out.println("***********************************************************");
            System.out.println("1.- Dólar → Peso argentino");
            System.out.println("2.- Peso argentino → Dólar");
            System.out.println("3.- Dólar → Real brasileño");
            System.out.println("4.- Real brasileño → Dólar");
            System.out.println("5.- Dólar → Peso colombiano");
            System.out.println("6.- Peso colombiano → Dólar");
            System.out.println("7.- Ingresar monedas manualmente");
            System.out.println("8.- Ver historial de conversiones");
            System.out.println("9.- Salir");
            System.out.println("***********************************************************");
            System.out.println("***********************************************************");
            System.out.print("Opción: ");

            int opcion = -1;
            try {
                opcion = Integer.parseInt(lectura.nextLine());
            } catch (NumberFormatException e) { //para evitar que ingresen letras o simbolos
                System.out.println("Por favor, ingrese un número válido.");
                continue; // vuelve a mostrar el menú sin salir del programa
            }

            String base = "";
            String cambio = "";

            switch (opcion) {
                case  1 ->{
                    base = "USD";
                    cambio = "ARS";
                }
                case 2 ->{
                    base = "ARS";
                    cambio = "USD";
                }
                case 3 -> {
                    base = "USD";
                    cambio = "BRL";
                }
                case 4 -> {
                    base = "BRL";
                    cambio = "USD";
                }
                case 5 -> {
                    base = "USD";
                    cambio = "COP";
                }
                case 6 -> {
                    base = "COP";
                    cambio = "USD";
                }
                case 7 -> {
                    System.out.print("Ingrese el código de la moneda base (por ejemplo, USD): ");
                    base = lectura.nextLine().trim().toUpperCase();
                    System.out.print("Ingrese el código de la moneda destino (por ejemplo, EUR): ");
                    cambio = lectura.nextLine().trim().toUpperCase();
                }
                case 8 -> {
                    if (historial.isEmpty()) {
                        System.out.println("No hay conversiones registradas.");
                    } else {
                        historialArchivo.mostrarHistorial();

                    }
                    continue;
                }
                case 9 -> {
                    continuar = false;
                    System.out.println("Gracias por utilizar el conversor de monedas");
                    continue;
                }
                default -> {
                    System.out.println("Opción inválida. Intente nuevamente");
                    continue;
                }
            }

            System.out.print("Ingrese la cantidad a convertir: ");
            String entrada = lectura.nextLine().replace(",", ".");
            // para evitar que de error si colocan , en lugar de .
            double cantidad = Double.parseDouble(entrada);

            try {
                Moneda moneda = consulta.buscaConversion(base);
                Double tasa = moneda.conversion_rates().get(cambio);
                if (tasa != null) {
                    double resultado = cantidad * tasa;
                    System.out.println(); //espacio en blanco
                    System.out.println("************************************************");
                    System.out.printf("%.2f %s son %.2f %s%n", cantidad, base, resultado, cambio);
                    System.out.println("************************************************");
                    //imprime cantidad base son resultado cambio (ej: 1$ son 1172 ARS)
                    System.out.println();
                    RegistroConversion registro = new RegistroConversion(base, cambio, cantidad, resultado);
                    historial.add(registro);
                    historialArchivo.guardar(registro);


                } else {
                    System.out.println("Moneda no encontrada.");
                }
            } catch (RuntimeException e) {
                System.out.println("Error al obtener tasas de cambio: " + e.getMessage());
            }
        }
    }
}