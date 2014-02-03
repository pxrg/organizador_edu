/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.organizador.modelo;

import java.util.HashMap;

/**
 *
 * @author paulo.gomes
 */
public class Configuracao {

    // Nome da pasta com os arquivos
    private String pastaOrigem;
    // Map com 'cnpj' e configuracoes
    HashMap<String, ConfiguracaoPasta> configuracoes;

    public Configuracao() {
        configuracoes = new HashMap();
    }

    public Configuracao(String pastaOrigem) {
        this.pastaOrigem = pastaOrigem;
        configuracoes = new HashMap();
    }

    public String getPastaOrigem() {
        return pastaOrigem;
    }

    public void setPastaOrigem(String pastaOrigem) {
        this.pastaOrigem = pastaOrigem;
    }

    public HashMap<String, ConfiguracaoPasta> getConfiguracoes() {
        return configuracoes;
    }

    public void setConfiguracoes(HashMap<String, ConfiguracaoPasta> configuracoes) {
        this.configuracoes = configuracoes;
    }

    public ConfiguracaoPasta getConfiguracao(String cnpj) {
        if (configuracoes == null || configuracoes.isEmpty()) {
            return null;
        }
        return configuracoes.get(cnpj);
    }

    public void adicionarConfig(ConfiguracaoPasta configPasta) {
        if (configuracoes == null || configPasta == null) {
            return;
        }
        configuracoes.put(configPasta.getCnpj(), configPasta);
    }
}
