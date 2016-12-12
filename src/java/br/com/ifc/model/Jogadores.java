/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifc.model;

import java.io.Serializable;

/**
 *
 * @author fabricio
 */
public class Jogadores implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String nome;
    private String posicao;
    private Integer idade;
    private String avatar;
    private Times time;

    public Jogadores() {
    }

    public Jogadores(Long id, String nome, String posicao, Integer idade, String avatar, Times time) {
        this.id = id;
        this.nome = nome;
        this.posicao = posicao;
        this.idade = idade;
        this.avatar = avatar;
        this.time = time;
    }

    public Times getTime() {
        return time;
    }

    public void setTime(Times time) {
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
        if (!(object instanceof Jogadores)) {
            return false;
        }
        Jogadores other = (Jogadores) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.ifc.entidades.Jogadores[ id=" + id + " ]";
    }

}
