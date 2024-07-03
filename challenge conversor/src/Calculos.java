import java.util.Scanner;

public class Calculos {
    private String monedaBase;
    private String monedaObjetivo;
    private double cantidad;

    Scanner lectura = new Scanner(System.in);
    ConsultaConversion conversion;

    public Calculos(ConsultaConversion conversion) {
        this.conversion = conversion;
    }

    public String getMonedaBase() {
        return monedaBase;
    }

    public String getMonedaObjetivo() {
        return monedaObjetivo;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void almacenarValores(String monedaBase, String monedaObjetivo) {
        if (esMonedaValida(monedaBase) && esMonedaValida(monedaObjetivo)) {
            this.monedaBase = monedaBase;
            this.monedaObjetivo = monedaObjetivo;

            System.out.println("Ingrese el valor que deseas convertir");
            this.cantidad = Double.parseDouble(lectura.nextLine());
        } else {
            System.out.println("Error: Monedas no válidas. Solo se permite USD, ARS, BRL, y COP.");
        }
    }

    public void almacenarValoresPersonalizados() {
        String menuOtrasOpciones = """
                Currency Code       Currency Name
                USD                 United States Dollar
                ARS                 Argentine Peso
                BRL                 Brazilian Real
                COP                 Colombian Peso
                """;

        System.out.println(menuOtrasOpciones);
        System.out.println("Ingrese la moneda base con 3 letras");
        String monedaBase = lectura.next().toUpperCase();
        System.out.println("Ingrese la moneda objetivo con 3 letras");
        String monedaObjetivo = lectura.next().toUpperCase();

        if (esMonedaValida(monedaBase) && esMonedaValida(monedaObjetivo)) {
            this.monedaBase = monedaBase;
            this.monedaObjetivo = monedaObjetivo;

            boolean entradaValida = false;
            do {
                System.out.println("Ingrese el valor que deseas convertir");
                if (lectura.hasNextDouble()) {
                    this.cantidad = lectura.nextDouble();
                    entradaValida = true;
                } else {
                    System.out.println("Error. Ingrese un valor numérico.");
                    lectura.next(); // Limpiar el buffer de entrada
                }
            } while (!entradaValida);
        } else {
            System.out.println("Error: Monedas no válidas. Solo se permite USD, ARS, BRL, y COP.");
        }
    }

    private boolean esMonedaValida(String moneda) {
        return moneda.equals("USD") || moneda.equals("ARS") || moneda.equals("BRL") || moneda.equals("COP");
    }

    public String obtenerMensajeRespuesta() {
        String mensaje = getMonedaBase().toUpperCase() + " " + getCantidad() + " equivale a: " + getMonedaObjetivo().toUpperCase() + " " + conversion.buscaConversion(getMonedaBase(), getMonedaObjetivo(), getCantidad());
        System.out.println(mensaje);
        return mensaje;
    }
}
