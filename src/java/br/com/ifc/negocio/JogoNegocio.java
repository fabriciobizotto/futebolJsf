/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifc.negocio;

import br.com.ifc.dao.JogoDao;
import br.com.ifc.dao.JogoDaoImpl;
import br.com.ifc.model.Jogos;
import java.util.List;

/**
 *
 * @author fabricio
 */
public class JogoNegocio {

    private final JogoDao dao;

    public JogoNegocio() {
        dao = new JogoDaoImpl();
    }

    public boolean salvar(Jogos a) throws Exception {
        if (a.getId() == null) {
            return dao.salvar(a);
        } else {
            return dao.atualizar(a);
        }
    }

    public void remover(Long pk) throws Exception {
        dao.remover(pk);
    }

    public List<Jogos> buscarTodos() throws Exception {
        return dao.buscarTodos();
    }

    public Jogos getById(Long id) throws Exception {
        return dao.getById(id);
    }

}
