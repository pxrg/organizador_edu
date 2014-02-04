/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.organizador.main;

import br.organizador.modelo.Configuracao;
import br.organizador.modelo.ConfiguracaoPasta;
import br.organizador.modelo.DB;
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author prg
 */
public class DBTest {
    
    String db_name = "sqlite.db";
    DB sqlite;
    
    public DBTest() {
        try {
            sqlite = new DB(db_name);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void criarBanco(){
        sqlite.initDB();
        assertTrue(new File(db_name).exists());
    }
    
    @Test
    public void salvarConfiguracaoEBuscarNaBaseDeDados() throws SQLException{
        Configuracao config = new Configuracao("C:\\tmp");
        sqlite.salvar(config);
        Configuracao novaConfig =sqlite.selecionar();
        assertNotNull(novaConfig);
        sqlite.close();
    }
    
    @Test
    public void salvarConfiguracaoEConfiguracaoPastaEBuscarNaBaseDeDados() throws SQLException{
        sqlite.salvar(new Configuracao("C:\\tmp"));
        Configuracao novaConfig =sqlite.selecionar();
        assertNotNull(novaConfig);
        ConfiguracaoPasta pasta = new ConfiguracaoPasta(0, "abc", "123456789", "C:\\tmp\\teste", novaConfig);
        sqlite.salvar(pasta);
        assertTrue(sqlite.selecionar(novaConfig).size() > 0);
        sqlite.close();
    }
}