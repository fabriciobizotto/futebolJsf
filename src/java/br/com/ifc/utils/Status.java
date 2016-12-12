/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifc.utils;

/**
 *
 * @author fabricio
 */
public enum Status {

    VITORIA(3), EMPATE(1), DERROTA(0);

    private int valor;

    private Status(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

}
