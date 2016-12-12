/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifc.utils;

/**
 *
 * @author fabricio
 */
public class FormatField {
    
    public static String formataCpf(String numero){
        return formataNumero(11, numero);
    }

    public static String formataNumero(Integer casas, String numero) {
        StringBuilder sb = new StringBuilder(numero);
        for (int i = sb.length(); i < casas; i++) {
            sb.insert(0, "0");
        }
        return sb.toString();
    }
}
