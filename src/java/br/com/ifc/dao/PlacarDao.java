/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifc.dao;

import br.com.ifc.model.Placar;
import br.com.ifc.model.PlacaresView;
import br.com.ifc.model.Pontuacao;
import java.util.List;

/**
 *
 * @author fabricio
 */
public interface PlacarDao {

    public boolean salvar(PlacaresView jogador) throws Exception;

    public boolean atualizar(PlacaresView jogador) throws Exception;

    public boolean remover(Long pk) throws Exception;

    public PlacaresView getById(Long pk) throws Exception;

    public List<PlacaresView> buscarTodos() throws Exception;
    
    public List<Pontuacao> buscarPontuacaoCampeonato() throws Exception;
}
