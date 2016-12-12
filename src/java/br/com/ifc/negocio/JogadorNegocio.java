/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifc.negocio;

import br.com.ifc.dao.JogadorDao;
import br.com.ifc.dao.JogadorDaoImpl;
import br.com.ifc.model.Jogadores;
import java.util.List;

/**
 *
 * @author fabricio
 */
public class JogadorNegocio {

    private final JogadorDao dao;

    public JogadorNegocio() {
        dao = new JogadorDaoImpl();
    }

    public boolean salvar(Jogadores a) throws Exception {
        if (a.getId() == null) {
            return dao.salvar(a);
        } else {
            return dao.atualizar(a);
        }
    }

    public void remover(Long pk) throws Exception {
        dao.remover(pk);
    }

    public List<Jogadores> buscarTodos() throws Exception {
        return dao.buscarTodos();
    }

    public Jogadores getById(Long id) throws Exception {
        return dao.getById(id);
    }

}
