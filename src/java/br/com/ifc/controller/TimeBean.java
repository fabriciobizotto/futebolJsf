/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifc.controller;

import br.com.ifc.model.Times;
import br.com.ifc.negocio.TimeNegocio;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author fabricio
 */
@ManagedBean
@ViewScoped
public class TimeBean implements Serializable {

    private List<Times> times;
    private Times time;
//    private Times jogadorSelecionado;
    private final TimeNegocio negocio;

    public TimeBean() {
        time = new Times();
        negocio = new TimeNegocio();
        buscarTodos();
    }

    public void salvar() {
        try {
            negocio.salvar(time);
            limpar();
            this.buscarTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void prepararAlterar() {
        try {
            this.time = negocio.getById(time.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void buscarTodos() {
        try {
            times = negocio.buscarTodos();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void excluir() {
        try {
            System.out.println(time.getId());
            negocio.remover(time.getId());
            limpar();
            this.buscarTodos();
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    public void limpar() {
        time = new Times();
    }

    public String irTimes() {
        return "times.xhtml";
    }

    public List<Times> getTimes() {
        return times;
    }

    public void setTimes(List<Times> times) {
        this.times = times;
    }

    public Times getTime() {
        return time;
    }

    public void setTime(Times time) {
        this.time = time;
    }

}
