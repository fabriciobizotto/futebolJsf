/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifc.dao;

import br.com.ifc.model.Times;
import java.util.List;

/**
 *
 * @author fabricio
 */
public interface TimeDao {

    public boolean salvar(Times jogador) throws Exception;

    public boolean atualizar(Times jogador) throws Exception;

    public boolean remover(Long pk) throws Exception;

    public Times getById(Long pk) throws Exception;

    public List<Times> buscarTodos() throws Exception;
}
