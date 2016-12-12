/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifc.controller;

import br.com.ifc.model.Jogadores;
import br.com.ifc.model.Times;
import br.com.ifc.negocio.JogadorNegocio;
import br.com.ifc.negocio.TimeNegocio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author fabricio
 */
@ManagedBean
@ViewScoped
public class JogadorBean implements Serializable {

    private List<Jogadores> jogadores;
    private Jogadores jogador;
//    private Jogadores jogadorSelecionado;
    private final JogadorNegocio negocio;
    private final TimeNegocio timeNegocio;
    private List<SelectItem> itensTime;

    public JogadorBean() {
        jogador = new Jogadores();
        negocio = new JogadorNegocio();
        timeNegocio = new TimeNegocio();
        buscarTodos();
    }

    public void salvar() {
        try {
            negocio.salvar(jogador);
            limpar();
            this.buscarTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void prepararAlterar() {
        try {
            this.jogador = negocio.getById(jogador.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void buscarTodos() {
        try {
            jogadores = negocio.buscarTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void excluir() {
        try {
            System.out.println(jogador.getId());
            negocio.remover(jogador.getId());
            limpar();
            this.buscarTodos();
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    public void limpar() {
        jogador = new Jogadores();
    }

    public String irJogadores() {
        return "jogadores.xhtml";
    }

    public List<SelectItem> getItensTime() {
        itensTime = new ArrayList<>();
        itensTime.add(new SelectItem(null, "Selecione!"));
        try {
            for (Times c : timeNegocio.buscarTodos()) {
                itensTime.add(new SelectItem(c, c.getNome()));
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return itensTime;
    }

    public List<Jogadores> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Jogadores> jogadores) {
        this.jogadores = jogadores;
    }

    public Jogadores getJogador() {
        return jogador;
    }

    public void setJogador(Jogadores jogador) {
        this.jogador = jogador;
    }

}
