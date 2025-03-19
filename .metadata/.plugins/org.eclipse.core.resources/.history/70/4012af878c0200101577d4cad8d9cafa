package Laboratorio;

import java.util.Scanner;

public class Numero_Primo extends Thread {
    private long numero;
   
    public Numero_Primo(long numero) {
        this.numero = numero;
      
    }

    @Override
    public void run() {
     
        boolean esPrimo = esPrimo(numero);
      
        
      
        
        System.out.println("Número introducido: " + numero);
        System.out.println("Hilo : " + Thread.currentThread().getName());
        System.out.println("Es primo: " + esPrimo);
    }

    private boolean esPrimo(long numero) {
        if (numero <= 1) {
            return false;
        }
        if (numero <= 3) {
            return true;
        }
        if (numero % 2 == 0 || numero % 3 == 0) {
            return false;
        }
        for (long i = 5; i * i <= numero; i += 6) {
            if (numero % i == 0 || numero % (i + 2) == 0) {
                return false;
            }
        }
        return true;
        
     
    }
  
   
}

    