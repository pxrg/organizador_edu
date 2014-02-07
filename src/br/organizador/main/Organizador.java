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
import javax.swing.JOptionPane;
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
        String arquivoDB = "organizador.db";
        try {
            if (!new File(arquivoDB).exists()) {
                sqlite = new DB(arquivoDB);
                sqlite.initDB();
            } else {
                sqlite = new DB(arquivoDB);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Organizador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Organizador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void getFiles() {
        File origem = new File(config.getPastaOrigem());
        this.arquivos = new ArrayList();
        arquivos.addAll(Arrays.asList(origem.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getAbsolutePath().endsWith(".xml") && !pathname.getName().startsWith(MoveArquivo.chave);
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
                if (cnpj != null) {
                    configDest = config.getConfiguracao(cnpj);
                    if (configDest != null) {
                        MoveArquivo.copiarERenomearArquivo(file.getAbsolutePath(), configDest.getPasta());
                    }
                }
            } catch (JDOMException ex) {
                Logger.getLogger(Organizador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Organizador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public DB getSqlite() {
        return sqlite;
    }

    public void setSqlite(DB sqlite) {
        this.sqlite = sqlite;
    }

    public List<File> getArquivos() {
        return arquivos;
    }

    public void setArquivos(List<File> arquivos) {
        this.arquivos = arquivos;
    }

    public ManipularXml getManipularXml() {
        return manipularXml;
    }

    public void setManipularXml(ManipularXml manipularXml) {
        this.manipularXml = manipularXml;
    }

    public Configuracao getConfig() {
        return config;
    }

    public void setConfig(Configuracao config) {
        this.config = config;
    }

    public DB getDb() {
        return sqlite;
    }

    public void adicionarConfig(ConfiguracaoPasta configuracaoPasta) throws SQLException {
        this.sqlite.salvar(configuracaoPasta);
        this.config.adicionarConfig(configuracaoPasta);
    }

    public void carregarConfiguracoes() throws SQLException {
        this.config = this.sqlite.carregarConfiguracoes();
        if (config == null) {
            this.config = new Configuracao();
        }
    }
}
