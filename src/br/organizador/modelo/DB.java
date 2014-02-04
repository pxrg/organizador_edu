/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.organizador.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author prg
 */
public class DB {
    
    private Connection conn;
    private Statement stm;
    
    public DB(String arquivo) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        this.conn = DriverManager.getConnection("jdbc:sqlite:" + arquivo);
        this.stm = this.conn.createStatement();
    }
    
    public void initDB() {
        try {
            this.stm.executeUpdate("DROP TABLE IF EXISTS configuracoes");
            this.stm.executeUpdate("CREATE TABLE configuracoes( "
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
                    + "pasta_origem VARCHAR(255) NOT NULL"
                    + ");");
            this.stm.executeUpdate("DROP TABLE IF EXISTS configuracoes_pastas");
            this.stm.executeUpdate("CREATE TABLE configuracoes_pastas( "
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
                    + "cnpj VARCHAR(20) UNIQUE NOT NULL ,"
                    + "nome_empresa VARCHAR(100),"
                    + "pasta VARCHAR(255) NOT NULL,"
                    + "id_config INT NOT NULL,"
                    + "FOREIGN KEY(id_config) REFERENCES configuracoes(id)"
                    + ");");
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void salvar(Configuracao config) throws SQLException {
        if (config.getId() == 0) {
            this.stm.executeUpdate("INSERT INTO configuracoes(pasta_origem) VALUES('"
                    + config.getPastaOrigem() + "');");
        } else {
            this.stm.executeUpdate("UPDATE configuracoes SET pasta_origem = '" + config.getPastaOrigem() + "' WHERE id = " + config.getId() + ";");
        }
    }
    
    public void salvar(ConfiguracaoPasta config) throws SQLException {
        if (config.getId() == 0) {
            this.stm.executeUpdate("INSERT INTO configuracoes_pastas(cnpj, nome_empresa, pasta, id_config) "
                    + "VALUES('" + config.getCnpj() + "',"
                    + (config.getNomeEmpresa() != null ? "'" + config.getNomeEmpresa() + "'" : null)
                    + ",'" + config.getPasta() + "'," + config.getConfig().getId() + ");");
        } else {
            this.stm.executeUpdate("UPDATE configuracoes_pastas "
                    + "SET cnpj = '" + config.getCnpj() + "' ,"
                    + "nome_empresa = " + (config.getNomeEmpresa() != null ? "'" + config.getNomeEmpresa() + "'" : null) + " ,"
                    + "pasta = '" + config.getPasta() + "' ,"
                    + "id_config = " + config.getConfig().getId() + " ,"
                    + "WHERE id = " + config.getId() + ";");
        }
    }
    
    public void excluir(ConfiguracaoPasta config) throws SQLException {
        if (config.getId() > 0) {
            this.stm.executeUpdate("DELETE FROM configuracoes_pasta WHERE id = " + config.getId() + ";");
        }
    }
    
    public void excluir(Configuracao config) throws SQLException {
        if (config.getId() > 0) {
            this.stm.executeUpdate("DELETE FROM configuracoes_pasta WHERE id_config = " + config.getId() + ";");
            this.stm.executeUpdate("DELETE FROM configuracoes WHERE id = " + config.getId() + ";");
        }
    }
    
    public Configuracao selecionar() throws SQLException {
        ResultSet rs = this.stm.executeQuery("SELECT * FROM configuracoes LIMIT 1;");
        Configuracao config = null;
        if (rs.next()) {
            config = new Configuracao(rs.getString("pasta_origem"));
            config.setId(rs.getInt("id"));
        }
        return config;
    }

    public List<ConfiguracaoPasta> selecionar(Configuracao config) throws SQLException {
        ResultSet rs = this.stm.executeQuery("SELECT * FROM configuracoes_pastas WHERE id_config = " + config.getId() + ";");
        ArrayList<ConfiguracaoPasta> configs = new ArrayList();
        while (rs.next()) {
            configs.add(new ConfiguracaoPasta(
                    rs.getInt("id"),
                    rs.getString("nome_empresa"),
                    rs.getString("cnpj"),
                    rs.getString("pasta"), config));
        }
        return configs;
    }
    
    public Configuracao carregarConfiguracoes() throws SQLException{
        Configuracao config = this.selecionar();
        if (config != null) {
            config.adicionarConfig(this.selecionar(config));
        }
        return config;
    }
    
    public void close() throws SQLException{
        this.stm.close();
        this.conn.close();
    }
}
