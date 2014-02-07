/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.organizador.main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author paulo.gomes
 */
public class ManipularXml {

    private String caminho;
    private String nomeArquivo;

    public ManipularXml() {
    }

    public ManipularXml(String caminho, String nomeArquivo) {
        this.caminho = caminho;
        this.nomeArquivo = nomeArquivo;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        if (!caminho.endsWith("\\")) {
            caminho += "\\";
        }
        this.caminho = caminho;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getCaminhoArquivo() {
        return !caminho.endsWith(File.separator) ? this.caminho + File.separator + this.nomeArquivo : this.caminho + this.nomeArquivo;
    }

    public void setArquivo(File arquivo) {
        String fname = arquivo.getAbsolutePath();
//        this.caminho = fname.substring(0, fname.lastIndexOf(File.separator));
        this.caminho = arquivo.getParent();
        this.nomeArquivo = arquivo.getName();
    }

    public boolean validarCNPJ(String valor) throws JDOMException, IOException {
        return valor.trim().equals(buscarCNPJEmit());
    }

    protected String buscarCNPJEmit() throws JDOMException, IOException {
        String cnpj = buscarTag("NFe", "infNFe", "emit", "CNPJ");
        return cnpj;
    }

    protected String buscarCNPJDest() throws JDOMException, IOException {
        String cnpj = buscarTag("NFe", "infNFe", "dest", "CNPJ");
        return cnpj;
    }

    protected String buscarTag(String... caminhoTag) throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(new File(getCaminhoArquivo()));
        Element node = doc.getRootElement();
        Namespace ns = Namespace.getNamespace("http://www.portalfiscal.inf.br/nfe");
        for (String tag : caminhoTag) {
            if (node == null) {
                return null;
            }
            node = node.getChild(tag, ns);
        }
        return (node != null) ? node.getText() : null;
    }
}
