package Laboratorio;

import java.util.Scanner;

public class Numero_Primo_Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce tres números:");
        long numero1 = scanner.nextLong();
        long numero2 = scanner.nextLong();
        long numero3 = scanner.nextLong();
        
        
//Los objetos que van arrancar un hilo. 
        Numero_Primo Hilo1 = new Numero_Primo(numero1);
        Hilo1.setName("Primero");
        Numero_Primo Hilo2 = new Numero_Primo(numero2);
        Hilo2.setName("Segundo");
        Numero_Primo Hilo3= new Numero_Primo(numero3);
        Hilo3.setName("Tercero");

        Hilo1.start();
        Hilo2.start();
        Hilo3.start();

       
    }
}