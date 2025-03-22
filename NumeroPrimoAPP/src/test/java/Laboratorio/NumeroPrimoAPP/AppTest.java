package Laboratorio.NumeroPrimoAPP;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import Laboratorio.Numero_Primo;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * Rigorous Test :-)
     */
    @Test
    void testEsPrimo() throws Exception {   

        Numero_Primo np = new Numero_Primo(0);
        Method method = Numero_Primo.class.getDeclaredMethod("esPrimo", long.class);
        method.setAccessible(true);
        assertFalse((boolean) method.invoke(np, -1));
        assertFalse((boolean) method.invoke(np, 0));
        assertFalse((boolean) method.invoke(np, 1));
        assertTrue((boolean) method.invoke(np, 2));
        assertTrue((boolean) method.invoke(np, 3));
        assertFalse((boolean) method.invoke(np, 4));
        assertTrue((boolean) method.invoke(np, 5));
        assertTrue((boolean) method.invoke(np, 13));
        assertFalse((boolean) method.invoke(np, 25));
    }
}
