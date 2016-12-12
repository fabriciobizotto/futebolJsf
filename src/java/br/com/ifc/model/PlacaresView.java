/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifc.model;

import java.util.Date;

/**
 *
 * @author fabricio
 */
public class PlacaresView {
//    select a.time_casa, a.time_fora, a.data_partida, b.id AS placar_id, b.jogo_id, b.placar_casa, b.placar_fora from jogos a

    private Long placarId;
    private Times timeCasa;
    private Times timeFora;
    private Date dataPartida;
    private Long jogoId;
    private Integer placarCasa;
    private Integer placarFora;

    public PlacaresView() {
    }

    public PlacaresView(Long placarId, Times timeCasa, Times timeFora, Date dataPartida, Long jogoId, Integer placarCasa, Integer placarFora) {
        this.placarId = placarId;
        this.timeCasa = timeCasa;
        this.timeFora = timeFora;
        this.dataPartida = dataPartida;
        this.jogoId = jogoId;
        this.placarCasa = placarCasa;
        this.placarFora = placarFora;
    }

    public Long getPlacarId() {
        return placarId;
    }

    public void setPlacarId(Long placarId) {
        this.placarId = placarId;
    }

    public Times getTimeCasa() {
        return timeCasa;
    }

    public void setTimeCasa(Times timeCasa) {
        this.timeCasa = timeCasa;
    }

    public Times getTimeFora() {
        return timeFora;
    }

    public void setTimeFora(Times timeFora) {
        this.timeFora = timeFora;
    }

    public Date getDataPartida() {
        return dataPartida;
    }

    public void setDataPartida(Date dataPartida) {
        this.dataPartida = dataPartida;
    }

    public Long getJogoId() {
        return jogoId;
    }

    public void setJogoId(Long jogoId) {
        this.jogoId = jogoId;
    }

    public Integer getPlacarCasa() {
        return placarCasa;
    }

    public void setPlacarCasa(Integer placarCasa) {
        this.placarCasa = placarCasa;
    }

    public Integer getPlacarFora() {
        return placarFora;
    }

    public void setPlacarFora(Integer placarFora) {
        this.placarFora = placarFora;
    }

}
