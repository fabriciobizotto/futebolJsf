/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifc.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author fabricio
 */
public class Jogos implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private Times timeCasa;
    private Times timeFora;
    private Date dataPartida;

    public Jogos() {
    }

    public Jogos(Long id, Times timeCasa, Times timeFora, Date dataPartida) {
        this.id = id;
        this.timeCasa = timeCasa;
        this.timeFora = timeFora;
        this.dataPartida = dataPartida;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jogos)) {
            return false;
        }
        Jogos other = (Jogos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.ifc.entidades.Jogos[ id=" + id + " ]";
    }

}
