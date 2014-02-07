/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.organizador.modelo;

import java.sql.Date;

/**
 *
 * @author paulo.gomes
 */
public class Log {
    
    private int id;
    private String descricao;
    private Date data;
    private ConfiguracaoPasta pasta;

    public Log(String descricao, ConfiguracaoPasta pasta) {
        this.descricao = descricao;
        this.pasta = pasta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public ConfiguracaoPasta getPasta() {
        return pasta;
    }

    public void setPasta(ConfiguracaoPasta pasta) {
        this.pasta = pasta;
    }
}
