/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifc.negocio;

import br.com.ifc.dao.PlacarDao;
import br.com.ifc.dao.PlacarDaoImpl;
import br.com.ifc.model.PlacaresView;
import br.com.ifc.model.Times;
import br.com.ifc.utils.Status;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author fabricio
 */
public class PlacarNegocio {

    private final PlacarDao dao;

    public PlacarNegocio() {
        dao = new PlacarDaoImpl();
    }

    public boolean salvar(PlacaresView a) throws Exception {
        if (a.getPlacarId() == null || a.getPlacarId() == 0) {
            return dao.salvar(a);
        } else {
            return dao.atualizar(a);
        }
    }

    public void remover(Long pk) throws Exception {
        dao.remover(pk);
    }

    public List<PlacaresView> buscarTodos() throws Exception {
        return dao.buscarTodos();
    }

    public PlacaresView getById(Long id) throws Exception {
        return dao.getById(id);
    }

    public Map<Times, Integer> calcularPosicaoTimes(List<PlacaresView> placares) {
        Map<Times, Integer> mapa = new HashMap<>();
        for (PlacaresView p : placares) {
            if (p.getPlacarCasa() != null && p.getPlacarFora() != null) {
                if (p.getPlacarCasa().compareTo(p.getPlacarFora()) > 0) {
                    Integer pontos = mapa.get(p.getTimeCasa());
                    pontos = pontos == null ? Status.VITORIA.getValor() : (Status.VITORIA.getValor() + pontos);
                    mapa.put(p.getTimeCasa(), pontos);
                    
                    pontos = mapa.get(p.getTimeFora());
                    pontos = pontos == null ? Status.DERROTA.getValor() : (Status.DERROTA.getValor() + pontos);
                    mapa.put(p.getTimeFora(), pontos);
                } else if (p.getPlacarCasa().compareTo(p.getPlacarFora()) == 0) {
                    Integer pontos = mapa.get(p.getTimeCasa());
                    pontos = pontos == null ? Status.EMPATE.getValor() : (Status.EMPATE.getValor() + pontos);
                    mapa.put(p.getTimeCasa(), pontos);
                    pontos = mapa.get(p.getTimeFora());
                    pontos = pontos == null ? Status.EMPATE.getValor() : (Status.EMPATE.getValor() + pontos);
                    mapa.put(p.getTimeFora(), pontos);
                } else if (p.getPlacarCasa().compareTo(p.getPlacarFora()) < 0) {
                    Integer pontos = mapa.get(p.getTimeFora());
                    pontos = pontos == null ? Status.VITORIA.getValor() : (Status.VITORIA.getValor() + pontos);
                    mapa.put(p.getTimeFora(), pontos);
                    
                    pontos = mapa.get(p.getTimeCasa());
                    pontos = pontos == null ? Status.DERROTA.getValor() : (Status.DERROTA.getValor() + pontos);
                    mapa.put(p.getTimeCasa(), pontos);
                }
            }
        }

        return sortByValue(mapa);
    }

    private Map<Times, Integer> sortByValue(Map<Times, Integer> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<Times, Integer>> list
                = new LinkedList<Map.Entry<Times, Integer>>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<Times, Integer>>() {
            public int compare(Map.Entry<Times, Integer> o1,
                    Map.Entry<Times, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<Times, Integer> sortedMap = new LinkedHashMap<Times, Integer>();
        for (Map.Entry<Times, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        /*
        //classic iterator example
        for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext(); ) {
            Map.Entry<String, Integer> entry = it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }*/
        return sortedMap;
    }

}
