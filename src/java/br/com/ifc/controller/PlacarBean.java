/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifc.controller;

import br.com.ifc.model.PlacaresView;
import br.com.ifc.model.Pontuacao;
import br.com.ifc.model.Times;
import br.com.ifc.negocio.PlacarNegocio;
import br.com.ifc.negocio.TimeNegocio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

/**
 *
 * @author fabricio
 */
@ManagedBean
@ViewScoped
public class PlacarBean implements Serializable {

    private List<PlacaresView> placares;
    private PlacaresView placar;
    private final PlacarNegocio negocio;
    private final TimeNegocio timeNegocio;
    private List<SelectItem> itensTime;
    private DataModel<Pontuacao> pontuacaoDataModel = null;

    public PlacarBean() {
        placar = new PlacaresView();
        negocio = new PlacarNegocio();
        timeNegocio = new TimeNegocio();
    }

    @PostConstruct
    public void init() {
        buscarTodos();
    }

    public void salvar(PlacaresView p) {
        try {
            System.out.println(p);
            negocio.salvar(p);
            limpar();
            this.buscarTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void prepararAlterar() {
        try {
            this.placar = negocio.getById(placar.getPlacarId());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void buscarTodos() {
        try {
            placares = negocio.buscarTodos();
            pontuacaoDataModel = new ListDataModel<>(negocio.buscarPontuacaoCampeonato());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void excluir() {
        try {
            System.out.println(placar.getPlacarId());
            negocio.remover(placar.getPlacarId());
            limpar();
            this.buscarTodos();
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    public void limpar() {
        placar = new PlacaresView();
    }

    public String irJogos() {
        return "placar.xhtml";
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

    public List<PlacaresView> getPlacares() {
        return placares;
    }

    public void setPlacares(List<PlacaresView> placares) {
        this.placares = placares;
    }

    public PlacaresView getPlacar() {
        return placar;
    }

    public void setPlacar(PlacaresView placar) {
        this.placar = placar;
    }

    public DataModel<Pontuacao> getPontuacaoDataModel() {
        return pontuacaoDataModel;
    }

}
