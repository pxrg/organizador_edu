/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.organizador.main;

import br.organizador.modelo.Configuracao;
import br.organizador.modelo.ConfiguracaoPasta;
import br.organizador.modelo.DB;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.JDOMException;

/**
 *
 * @author paulo.gomes
 */
public class Organizador {

    private Configuracao config;
    private DB sqlite;
    List<File> arquivos;
    ManipularXml manipularXml;

    public Organizador() {
        config = new Configuracao();
        this.arquivos = new ArrayList();
        manipularXml = new ManipularXml();
        try {
            sqlite = new DB("organizador.db");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Organizador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Organizador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void getFiles() {
        File origem = new File(config.getPastaOrigem());
        arquivos.addAll(Arrays.asList(origem.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getAbsolutePath().endsWith(".xml");
            }
        })));
    }

    public void executar() {
        if (config == null) {
            return;
        }
        this.getFiles();
        String cnpj = null;
        ConfiguracaoPasta configDest = null;
        for (File file : arquivos) {
            try {
                if (file.isDirectory()) {
                    continue;
                }
                manipularXml.setArquivo(file);
                cnpj = manipularXml.buscarCNPJDest();
                configDest = config.getConfiguracao(cnpj);
                if (configDest != null) {
                    MoveArquivo.copiar(file.getAbsolutePath(), configDest.getPasta());
                }
            } catch (JDOMException ex) {
                Logger.getLogger(Organizador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Organizador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Configuracao getConfig() {
        return config;
    }
}
