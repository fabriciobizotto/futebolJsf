/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifc.negocio;

import br.com.ifc.dao.TimeDao;
import br.com.ifc.dao.TimeDaoImpl;
import br.com.ifc.model.Times;
import java.util.List;

/**
 *
 * @author fabricio
 */
public class TimeNegocio {

    private final TimeDao dao;

    public TimeNegocio() {
        dao = new TimeDaoImpl();
    }

    public boolean salvar(Times a) throws Exception {
        if (a.getId() == null) {
            return dao.salvar(a);
        } else {
            return dao.atualizar(a);
        }
    }

    public void remover(Long pk) throws Exception {
        dao.remover(pk);
    }

    public List<Times> buscarTodos() throws Exception {
        return dao.buscarTodos();
    }

    public Times getById(Long id) throws Exception {
        return dao.getById(id);
    }

}
