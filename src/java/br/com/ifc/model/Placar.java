/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifc.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author fabricio
 */
public class Placar implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private Jogos jogo;
    private int placarCasa;
    private int placarFora;

    public Placar() {
    }

    public Placar(Long id, Jogos jogo, int placarCasa, int placarFora) {
        this.id = id;
        this.jogo = jogo;
        this.placarCasa = placarCasa;
        this.placarFora = placarFora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Jogos getJogo() {
        return jogo;
    }

    public void setJogo(Jogos jogo) {
        this.jogo = jogo;
    }

    public int getPlacarCasa() {
        return placarCasa;
    }

    public void setPlacarCasa(int placarCasa) {
        this.placarCasa = placarCasa;
    }

    public int getPlacarFora() {
        return placarFora;
    }

    public void setPlacarFora(int placarFora) {
        this.placarFora = placarFora;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Placar other = (Placar) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Placar{" + "id=" + id + ", jogo=" + jogo + ", placarCasa=" + placarCasa + ", placarFora=" + placarFora + '}';
    }

}
