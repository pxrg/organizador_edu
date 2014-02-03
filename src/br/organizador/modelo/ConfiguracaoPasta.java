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

    private String nomeEmpresa;
    private String cnpj;
    private String pasta;

    public ConfiguracaoPasta() {
    }
    
    public ConfiguracaoPasta(String nomeEmpresa, String cnpj, String pasta) {
        this.nomeEmpresa = nomeEmpresa;
        this.cnpj = cnpj;
        this.pasta = pasta;
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
}
