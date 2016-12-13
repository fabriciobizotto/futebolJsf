/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifc.model;

/**
 *
 * @author fabricio
 */
public class Pontuacao {

    private Times time;
    private Integer pontos;

    public Pontuacao() {
    }

    public Pontuacao(Times time, Integer pontos) {
        this.time = time;
        this.pontos = pontos;
    }

    public Times getTime() {
        return time;
    }

    public void setTime(Times time) {
        this.time = time;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }

}
