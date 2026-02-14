//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import javax.swing.JOptionPane;

class arregloDeNotas {

    public static void main(String[] args) {

        // 1 y 2. Crear e inicializar arreglo con 5 notas
        double[] notas = ingresarNotas(5);

        // 3. Calcular nota definitiva (promedio)
        double definitiva = calcularPromedio(notas);

        // 4. Obtener nota mayor
        double mayor = obtenerMayor(notas);

        // 5. Obtener nota menor
        double menor = obtenerMenor(notas);

        // 6. Ordenar con burbuja
        ordenarBurbuja(notas);

        // 7. Funcionalidad extra: contar cuántas pasan (>=3.0)
        int aprobadas = contarAprobadas(notas);

        // Mostrar resumen
        mostrarResumen(notas, definitiva, mayor, menor, aprobadas);
    }

    // INGRESAR NOTAS
    public static double[] ingresarNotas(int cantidad) {
        double[] notas = new double[cantidad];

        for (int i = 0; i < cantidad; i++) {
            notas[i] = Double.parseDouble(
                    JOptionPane.showInputDialog("Ingrese la nota #" + (i + 1))
            );
        }

        return notas;
    }

    // CALCULAR PROMEDIO
    public static double calcularPromedio(double[] notas) {
        double suma = 0;

        for (int i = 0; i < notas.length; i++) {
            suma += notas[i];
        }

        return suma / notas.length;
    }

    // OBTENER MAYOR
    public static double obtenerMayor(double[] notas) {
        double mayor = notas[0];

        for (int i = 1; i < notas.length; i++) {
            if (notas[i] > mayor) {
                mayor = notas[i];
            }
        }

        return mayor;
    }

    // OBTENER MENOR
    public static double obtenerMenor(double[] notas) {
        double menor = notas[0];

        for (int i = 1; i < notas.length; i++) {
            if (notas[i] < menor) {
                menor = notas[i];
            }
        }

        return menor;
    }

    // ORDENAMIENTO BURBUJA
    public static void ordenarBurbuja(double[] notas) {
        for (int i = 0; i < notas.length - 1; i++) {
            for (int j = 0; j < notas.length - 1 - i; j++) {

                if (notas[j] > notas[j + 1]) {
                    double aux = notas[j];
                    notas[j] = notas[j + 1];
                    notas[j + 1] = aux;
                }
            }
        }
    }

    // FUNCIONALIDAD EXTRA: CONTAR APROBADAS
    public static int contarAprobadas(double[] notas) {
        int contador = 0;

        for (int i = 0; i < notas.length; i++) {
            if (notas[i] >= 3.0) {
                contador++;
            }
        }

        return contador;
    }

    // MOSTRAR RESUMEN
    public static void mostrarResumen(double[] notas, double promedio, double mayor, double menor, int aprobadas) {

        String mensaje = "RESUMEN DE NOTAS\n\n";

        mensaje += "Notas ordenadas:\n";
        for (int i = 0; i < notas.length; i++) {
            mensaje += notas[i] + "\n";
        }

        mensaje += "\nPromedio: " + promedio;
        mensaje += "\nNota mayor: " + mayor;
        mensaje += "\nNota menor: " + menor;
        mensaje += "\nCantidad de aprobadas: " + aprobadas;

        JOptionPane.showMessageDialog(null, mensaje);
    }
}