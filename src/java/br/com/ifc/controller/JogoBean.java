/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifc.controller;

import br.com.ifc.model.Jogadores;
import br.com.ifc.model.Jogos;
import br.com.ifc.model.Times;
import br.com.ifc.negocio.JogadorNegocio;
import br.com.ifc.negocio.JogoNegocio;
import br.com.ifc.negocio.TimeNegocio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author fabricio
 */
@ManagedBean
@ViewScoped
public class JogoBean implements Serializable {

    private List<Jogos> jogos;
    private Jogos jogo;
//    private Jogadores jogoSelecionado;
    private final JogoNegocio negocio;
    private final TimeNegocio timeNegocio;
    private List<SelectItem> itensTime;

    public JogoBean() {
        jogo = new Jogos();
        negocio = new JogoNegocio();
        timeNegocio = new TimeNegocio();
        buscarTodos();
    }

    public void salvar() {
        try {
            negocio.salvar(jogo);
            limpar();
            this.buscarTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void prepararAlterar() {
        try {
            this.jogo = negocio.getById(jogo.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void buscarTodos() {
        try {
            jogos = negocio.buscarTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void excluir() {
        try {
            System.out.println(jogo.getId());
            negocio.remover(jogo.getId());
            limpar();
            this.buscarTodos();
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    public void limpar() {
        jogo = new Jogos();
    }

    public String irJogos() {
        return "jogoes.xhtml";
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

    public List<Jogos> getJogos() {
        return jogos;
    }

    public void setJogos(List<Jogos> jogos) {
        this.jogos = jogos;
    }

    public Jogos getJogo() {
        return jogo;
    }

    public void setJogo(Jogos jogo) {
        this.jogo = jogo;
    }

}
