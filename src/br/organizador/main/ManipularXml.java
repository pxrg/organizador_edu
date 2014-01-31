/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.organizador.main;

import java.io.File;
import java.io.IOException;
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
    
    
    private boolean validarCNPJ(String valor) throws JDOMException, IOException{
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(new File(caminho+nomeArquivo));
        Element nfe = doc.getRootElement();
        
        Namespace ns = Namespace.getNamespace("http://www.portalfiscal.inf.br/nfe");
        Element infNFe = nfe.getChild("infNFe", ns);
        infNFe = infNFe.getChild("emit");
        String cnpj = infNFe.getAttributeValue("CNPJ");
        return false;
    }
    
    private String buscarCNPJ() throws JDOMException, IOException{
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(new File(caminho+nomeArquivo));
        Element nfe = doc.getRootElement();
        
        Namespace ns = Namespace.getNamespace("http://www.portalfiscal.inf.br/nfe");
        Element infNFe = nfe.getChild("infNFe", ns);
        infNFe = infNFe.getChild("emit");
        String cnpj = infNFe.getAttributeValue("CNPJ");
        return cnpj;
    }
        
}
