/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifc.dao;

import br.com.ifc.model.Jogos;
import java.util.List;

/**
 *
 * @author fabricio
 */
public interface JogoDao {

    public boolean salvar(Jogos jogador) throws Exception;

    public boolean atualizar(Jogos jogador) throws Exception;

    public boolean remover(Long pk) throws Exception;

    public Jogos getById(Long pk) throws Exception;

    public List<Jogos> buscarTodos() throws Exception;
}
