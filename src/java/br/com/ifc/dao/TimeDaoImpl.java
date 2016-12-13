/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifc.dao;

import br.com.ifc.model.Times;
import br.com.ifc.utils.ConnectionProvider;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabricio
 */
public class TimeDaoImpl implements TimeDao {

    @Override
    public boolean salvar(Times jogador) throws Exception {
        PreparedStatement ps = null;
        int i = 1;
        try {
            String sql = "insert into times (nome, tecnico, estadio, escudo) values (?,?,?,?)";
            ps = ConnectionProvider.getInstance().getConnection().prepareStatement(sql);
            ps.setString(i++, jogador.getNome());
            ps.setString(i++, jogador.getTecnico());
            ps.setString(i++, jogador.getEstadio());
            ps.setString(i++, jogador.getEscudo());
            return ps.executeUpdate() == 1;
        } finally {
            ConnectionProvider.getInstance().closeConnection(ps);
        }
    }

    @Override
    public boolean remover(Long pk) throws Exception {
        PreparedStatement ps = null;
        try {
            String sql = "delete from times where id=?";
            ps = ConnectionProvider.getInstance().getConnection().prepareStatement(sql);
            ps.setLong(1, pk);
            return ps.executeUpdate() == 1;
        } finally {
            ConnectionProvider.getInstance().closeConnection(ps);
        }
    }

    @Override
    public Times getById(Long pk) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from times where id=?";
            ps = ConnectionProvider.getInstance().getConnection().prepareStatement(sql);
            ps.setLong(1, pk);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Times(rs.getLong("id"), rs.getString("nome"), rs.getString("tecnico"), rs.getString("estadio"), rs.getString("escudo"));
            }
            return null;
        } finally {
//            ConnectionProvider.getInstance().closeConnection(rs, ps);
        }
    }

    @Override
    public List<Times> buscarTodos() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Times> lista = new ArrayList<>();
        try {
            String sql = "select * from times order by nome";
            ps = ConnectionProvider.getInstance().getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Times(rs.getLong("id"), rs.getString("nome"), rs.getString("tecnico"), rs.getString("estadio"), rs.getString("escudo")));
            }
            return lista;
        } finally {
            ConnectionProvider.getInstance().closeConnection(rs, ps);
        }
    }

    @Override
    public boolean atualizar(Times jogador) throws Exception {
        PreparedStatement ps = null;
        int i = 1;
        try {
            String sql = "update times set nome = ?, tecnico = ?, estadio = ?, escudo = ? where id = ?";
            ps = ConnectionProvider.getInstance().getConnection().prepareStatement(sql);
            ps.setString(i++, jogador.getNome());
            ps.setString(i++, jogador.getTecnico());
            ps.setString(i++, jogador.getEstadio());
            ps.setString(i++, jogador.getEscudo());

            ps.setLong(i++, jogador.getId());
            return ps.executeUpdate() == 1;
        } finally {
            ConnectionProvider.getInstance().closeConnection(ps);
        }
    }

}
