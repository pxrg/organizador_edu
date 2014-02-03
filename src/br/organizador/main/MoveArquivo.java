/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.organizador.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 *
 * @author prg
 */
public class MoveArquivo {
    
    public static void copiar(String origem, String destino) throws FileNotFoundException, IOException{
        String nomeArq = origem.substring(origem.lastIndexOf(File.separator), origem.length());
        copiar(new File(origem), new File(destino.concat(File.separator+nomeArq)));
    }
    
    public static void copiar(File origem, File destino) throws FileNotFoundException, IOException{
        if (destino.exists()) {
            destino.delete();
        }
        
        FileChannel arqOrig = null;
        FileChannel arqDest = null;
        try {
            arqOrig = new FileInputStream(origem).getChannel();
            arqDest = new FileOutputStream(destino).getChannel();
            arqOrig.transferTo(0, arqOrig.size(), arqDest);
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException();
        } catch (IOException ex) {
            throw new IOException();
        }finally{
            if (arqOrig != null && arqOrig.isOpen()) {
                arqOrig.close();
            }
            if (arqDest != null && arqDest.isOpen()) {
                arqDest.close();
            }
        }
    }
    
}
