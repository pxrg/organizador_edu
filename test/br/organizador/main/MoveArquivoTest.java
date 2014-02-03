/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.organizador.main;

import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author prg
 */
public class MoveArquivoTest {
    
    public MoveArquivoTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of copiar method, of class MoveArquivo.
     */
    @Test
    public void testCopiar_String_String() throws Exception {
        System.out.println("copiar");
        String origem = "C:\\temp\\a.txt";
        String destino = "C:\\temp\\dest\\a.txt";
        File test = new File(origem);
        if (!test.exists()) {
            test.createNewFile();
        }
        MoveArquivo.copiar(origem, destino);
        // TODO review the generated test code and remove the default call to fail.
        assertTrue(new File(destino).exists());
    }

}