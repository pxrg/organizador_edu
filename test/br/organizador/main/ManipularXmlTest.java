/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.organizador.main;

import java.io.IOException;
import org.jdom2.JDOMException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author prg
 */
public class ManipularXmlTest {
    
    ManipularXml xml;
    
    public ManipularXmlTest() {
    }
    
    @Before
    public void setUp() {
        xml = new ManipularXml("", "11806723000794_0907144900011520140108060210257.xml");
    }

    @Test
    public void testBuscarCNPJRetornarNaoNulo() throws JDOMException, IOException {
        // TODO review the generated test code and remove the default call to fail.
        assertNotNull(xml.buscarCNPJEmit());
    }
    
    @Test
    public void testBuscarCNPJDiferenteDoValor() throws JDOMException, IOException{
        assertTrue(xml.buscarCNPJEmit()!= "12345678901234");
    }
    
    @Test
    public void testValidarCNPJIgualAoValorPassado() throws JDOMException, IOException{
        assertTrue(xml.validarCNPJ("11806723000794"));
    }
}