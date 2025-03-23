package Laboratorio;

public class Numero_Primo_Main {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println();
            return;
        }

        long numero1 = Long.parseLong(args[0]);
        long numero2 = Long.parseLong(args[1]);
        long numero3 = Long.parseLong(args[2]);

        // Los objetos que van a arrancar un hilo.
        Numero_Primo Hilo1 = new Numero_Primo(numero1);
        Hilo1.setName("Primero");

        Numero_Primo Hilo2 = new Numero_Primo(numero2);
        Hilo2.setName("Segundo");

        Numero_Primo Hilo3 = new Numero_Primo(numero3);
        Hilo3.setName("Tercero");

        Hilo1.start();
        Hilo2.start();
        Hilo3.start();
    }
}