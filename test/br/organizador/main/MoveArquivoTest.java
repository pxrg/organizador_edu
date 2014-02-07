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
        System.out.println("testCopiar_String_String");
        String origem = "C:\\temp\\a.txt";
        String destino = "C:\\temp\\dest";
        File test = new File(origem);
        if (!test.exists()) {
            test.createNewFile();
        }
        MoveArquivo.copiar(origem, destino);
        // TODO review the generated test code and remove the default call to fail.
        assertTrue(new File(destino).exists());
    }

    @Test
    public void testeRenomearArquivo() throws Exception {
        System.out.println("testeRenomearArquivo");
        String origem = "C:\\temp\\aa.txt";
        String destino = "C:\\temp\\abc.txt";
        File test = new File(origem);
        if (!test.exists()) {
            test.createNewFile();
        }
        test.renameTo(new File(destino));
        // TODO review the generated test code and remove the default call to fail.
        assertTrue(new File(destino).exists());
    }

    @Test
    public void testCopiarERenomearArquivo() throws Exception {
        System.out.println("testCopiarERenomearArquivo");
        String origem = "C:\\temp\\a.txt";
        String destino = "C:\\temp\\dest";
        File test = new File(origem);
        if (!test.exists()) {
            test.createNewFile();
        }
        MoveArquivo.copiarERenomearArquivo(origem, destino);
        // TODO review the generated test code and remove the default call to fail.
        assertTrue(new File(MoveArquivo.chave + test.getName()).exists());
    }
}