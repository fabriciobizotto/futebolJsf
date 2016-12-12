/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifc.dao;

import br.com.ifc.model.Jogos;
import br.com.ifc.utils.ConnectionProvider;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabricio
 */
public class JogoDaoImpl implements JogoDao {

    private TimeDao timeDao;

    public JogoDaoImpl() {
        timeDao = new TimeDaoImpl();
    }

    @Override
    public boolean salvar(Jogos jogador) throws Exception {
        PreparedStatement ps = null;
        int i = 1;
        try {
            String sql = "insert into jogos (time_casa, time_fora, data_partida) values (?,?,?)";
            ps = ConnectionProvider.getInstance().getConnection().prepareStatement(sql);
            ps.setLong(i++, jogador.getTimeCasa().getId());
            ps.setLong(i++, jogador.getTimeFora().getId());
            ps.setDate(i++, new java.sql.Date(jogador.getDataPartida().getTime()));
            return ps.executeUpdate() == 1;
        } finally {
            ConnectionProvider.getInstance().closeConnection(ps);
        }
    }

    @Override
    public boolean remover(Long pk) throws Exception {
        PreparedStatement ps = null;
        try {
            String sql = "delete from jogos where id=?";
            ps = ConnectionProvider.getInstance().getConnection().prepareStatement(sql);
            ps.setLong(1, pk);
            return ps.executeUpdate() == 1;
        } finally {
            ConnectionProvider.getInstance().closeConnection(ps);
        }
    }

    @Override
    public Jogos getById(Long pk) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from jogos where id=?";
            ps = ConnectionProvider.getInstance().getConnection().prepareStatement(sql);
            ps.setLong(1, pk);
            rs = ps.executeQuery();
            if (rs.next()) {
                Long timeCasa = rs.getLong("time_casa");
                Long timeFora = rs.getLong("time_fora");
                return new Jogos(rs.getLong("id"),
                        timeDao.getById(timeCasa),
                        timeDao.getById(timeFora),
                        rs.getDate("data_partida")
                );
            }
            return null;
        } finally {
//            ConnectionProvider.getInstance().closeConnection(rs, ps);
        }
    }

    @Override
    public List<Jogos> buscarTodos() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Jogos> lista = new ArrayList<>();
        try {
            String sql = "select * from jogos";
            ps = ConnectionProvider.getInstance().getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Long timeCasa = rs.getLong("time_casa");
                Long timeFora = rs.getLong("time_fora");
                lista.add(new Jogos(rs.getLong("id"),
                        timeDao.getById(timeCasa),
                        timeDao.getById(timeFora),
                        rs.getDate("data_partida"))
                );
            }
            return lista;
        } finally {
            ConnectionProvider.getInstance().closeConnection(rs, ps);
        }
    }

    @Override
    public boolean atualizar(Jogos jogador) throws Exception {
        PreparedStatement ps = null;
        int i = 1;
        try {
            String sql = "update jogos set time_casa = ?, time_fora = ?, data_partida = ? where id = ?";
            ps = ConnectionProvider.getInstance().getConnection().prepareStatement(sql);
            ps.setLong(i++, jogador.getTimeCasa().getId());
            ps.setLong(i++, jogador.getTimeFora().getId());
            ps.setDate(i++, new java.sql.Date(jogador.getDataPartida().getTime()));

            ps.setLong(i++, jogador.getId());
            return ps.executeUpdate() == 1;
        } finally {
            ConnectionProvider.getInstance().closeConnection(ps);
        }
    }

}
