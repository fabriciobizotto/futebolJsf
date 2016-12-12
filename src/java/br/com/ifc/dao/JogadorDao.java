/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifc.dao;

import br.com.ifc.model.Jogadores;
import java.util.List;

/**
 *
 * @author fabricio
 */
public interface JogadorDao {

    public boolean salvar(Jogadores jogador) throws Exception;

    public boolean atualizar(Jogadores jogador) throws Exception;

    public boolean remover(Long pk) throws Exception;

    public Jogadores getById(Long pk) throws Exception;

    public List<Jogadores> buscarTodos() throws Exception;
}
