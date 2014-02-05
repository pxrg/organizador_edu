/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.organizador.modelo;

/**
 *
 * @author paulo.gomes
 */
public class ConfiguracaoPasta {

    private int id;
    private String nomeEmpresa;
    private String cnpj;
    private String pasta;
    private Configuracao config;

    public ConfiguracaoPasta() {
    }

    public ConfiguracaoPasta(int id) {
        this.id = id;
    }

    public ConfiguracaoPasta(int id, String nomeEmpresa, String cnpj, String pasta, Configuracao config) {
        this.id = id;
        this.nomeEmpresa = nomeEmpresa;
        this.cnpj = cnpj;
        this.pasta = pasta;
        this.config = config;
    }
    
    public ConfiguracaoPasta(String nomeEmpresa, String cnpj, String pasta) {
        this.nomeEmpresa = nomeEmpresa;
        this.cnpj = cnpj;
        this.pasta = pasta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getPasta() {
        return pasta;
    }

    public void setPasta(String pasta) {
        this.pasta = pasta;
    }

    public Configuracao getConfig() {
        return config;
    }

    public void setConfig(Configuracao config) {
        this.config = config;
    }
}
