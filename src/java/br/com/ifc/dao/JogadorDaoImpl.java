/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifc.dao;

import br.com.ifc.model.Jogadores;
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
public class JogadorDaoImpl implements JogadorDao {

    private TimeDao timeDao;

    public JogadorDaoImpl() {
        this.timeDao = new TimeDaoImpl();
    }

    @Override
    public boolean salvar(Jogadores jogador) throws Exception {
        PreparedStatement ps = null;
        int i = 1;
        try {
            String sql = "insert into jogadores (nome, posicao, idade, avatar, time_id) values (?,?,?,?,?)";
            ps = ConnectionProvider.getInstance().getConnection().prepareStatement(sql);
            ps.setString(i++, jogador.getNome());
            ps.setString(i++, jogador.getPosicao());
            ps.setInt(i++, jogador.getIdade());
            ps.setString(i++, jogador.getAvatar());
            ps.setLong(i++, jogador.getTime().getId());
            return ps.executeUpdate() == 1;
        } finally {
            ConnectionProvider.getInstance().closeConnection(ps);
        }
    }

    @Override
    public boolean remover(Long pk) throws Exception {
        PreparedStatement ps = null;
        try {
            String sql = "delete from jogadores where id=?";
            ps = ConnectionProvider.getInstance().getConnection().prepareStatement(sql);
            ps.setLong(1, pk);
            return ps.executeUpdate() == 1;
        } finally {
            ConnectionProvider.getInstance().closeConnection(ps);
        }
    }

    @Override
    public Jogadores getById(Long pk) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from jogadores where id=?";
            ps = ConnectionProvider.getInstance().getConnection().prepareStatement(sql);
            ps.setLong(1, pk);
            rs = ps.executeQuery();
            if (rs.next()) {
                Long timeId = rs.getLong("time_id");
                return new Jogadores(rs.getLong("id"), rs.getString("nome"), rs.getString("posicao"), rs.getInt("idade"), rs.getString("avatar"),
                        timeDao.getById(timeId));
            }
            return null;
        } finally {
            ConnectionProvider.getInstance().closeConnection(rs,ps);
        }
    }

    @Override
    public List<Jogadores> buscarTodos() throws Exception {
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Jogadores> lista = new ArrayList<>();
        try {
            String sql = "select * from jogadores";
            ps = ConnectionProvider.getInstance().getConnection().prepareStatement(sql);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Long timeId = resultSet.getLong("time_id");
                lista.add(new Jogadores(resultSet.getLong("id"), resultSet.getString("nome"), resultSet.getString("posicao"), resultSet.getInt("idade"), resultSet.getString("avatar"),
                        timeDao.getById(timeId)));
            }
            return lista;
        } finally {
            ConnectionProvider.getInstance().closeConnection(resultSet,ps);
        }
    }

    @Override
    public boolean atualizar(Jogadores jogador) throws Exception {
        PreparedStatement ps = null;
        int i = 1;
        try {
            String sql = "update jogadores set nome = ?, posicao = ?, idade = ?, avatar = ?, time_id = ? where id = ?";
            ps = ConnectionProvider.getInstance().getConnection().prepareStatement(sql);
            ps.setString(i++, jogador.getNome());
            ps.setString(i++, jogador.getPosicao());
            ps.setInt(i++, jogador.getIdade());
            ps.setString(i++, jogador.getAvatar());
            ps.setLong(i++, jogador.getTime().getId());

            ps.setLong(i++, jogador.getId());
            return ps.executeUpdate() == 1;
        } finally {
            ConnectionProvider.getInstance().closeConnection(ps);
        }
    }

}
