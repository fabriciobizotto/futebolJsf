/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifc.dao;

import br.com.ifc.model.PlacaresView;
import br.com.ifc.model.Pontuacao;
import br.com.ifc.utils.ConnectionProvider;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabricio
 */
public class PlacarDaoImpl implements PlacarDao {

    private JogoDao jogoDao;
    private TimeDao timeDao;

    public PlacarDaoImpl() {
        jogoDao = new JogoDaoImpl();
        timeDao = new TimeDaoImpl();
    }

    @Override
    public boolean salvar(PlacaresView placar) throws Exception {
        PreparedStatement ps = null;
        int i = 1;
        try {
            String sql = "insert into placar (jogo_id, placar_casa, placar_fora) values (?,?,?)";
            ps = ConnectionProvider.getInstance().getConnection().prepareStatement(sql);
            ps.setLong(i++, placar.getJogoId());
            if (placar.getPlacarCasa() == null) {
                ps.setNull(i++, Types.NULL);
            } else {
                ps.setInt(i++, placar.getPlacarCasa());
            }
            if (placar.getPlacarFora() == null) {
                ps.setNull(i++, Types.NULL);
            } else {
                ps.setInt(i++, placar.getPlacarFora());
            }

            return ps.executeUpdate() == 1;
        } finally {
            ConnectionProvider.getInstance().closeConnection(ps);
        }
    }

    @Override
    public boolean remover(Long pk) throws Exception {
        PreparedStatement ps = null;
        try {
            String sql = "delete from placar where id=?";
            ps = ConnectionProvider.getInstance().getConnection().prepareStatement(sql);
            ps.setLong(1, pk);
            return ps.executeUpdate() == 1;
        } finally {
            ConnectionProvider.getInstance().closeConnection(ps);
        }
    }

    @Override
    public PlacaresView getById(Long pk) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select a.time_casa, a.time_fora, a.data_partida, b.id AS placar_id, a.id AS jogo_id, b.placar_casa, b.placar_fora from jogos a "
                    + "LEFT JOIN placar b ON a.id = b.jogo_id "
                    + "where b.id = ?";
            ps = ConnectionProvider.getInstance().getConnection().prepareStatement(sql);
            ps.setLong(1, pk);
            rs = ps.executeQuery();
            if (rs.next()) {
                Long jogoId = rs.getLong("jogo_id");
                Long timeCasa = rs.getLong("time_casa");
                Long timeFora = rs.getLong("time_fora");
                return new PlacaresView(rs.getLong("placar_id"),
                        timeDao.getById(timeCasa), timeDao.getById(timeFora),
                        rs.getDate("data_partida"),
                        //                        jogoDao.getById(jogoId),
                        jogoId,
                        rs.getInt("placar_casa"),
                        rs.getInt("placar_fora")
                );
            }
            return null;
        } finally {
            ConnectionProvider.getInstance().closeConnection(rs, ps);
        }
    }

    @Override
    public List<PlacaresView> buscarTodos() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<PlacaresView> lista = new ArrayList<>();
        try {
            String sql = "select a.time_casa, a.time_fora, a.data_partida, b.id AS placar_id, a.id AS jogo_id, b.placar_casa, b.placar_fora from jogos a "
                    + "LEFT JOIN placar b ON a.id = b.jogo_id";
            ps = ConnectionProvider.getInstance().getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Long jogoId = rs.getLong("jogo_id");
                Long timeCasa = rs.getLong("time_casa");
                Long timeFora = rs.getLong("time_fora");
                lista.add(new PlacaresView(rs.getLong("placar_id"),
                        timeDao.getById(timeCasa), timeDao.getById(timeFora),
                        rs.getDate("data_partida"),
                        //                        jogoDao.getById(jogoId),
                        jogoId,
                        rs.getObject("placar_casa") == null ? null : rs.getInt("placar_casa"),
                        rs.getObject("placar_fora") == null ? null : rs.getInt("placar_fora")
                )
                );
            }
            return lista;
        } finally {
            ConnectionProvider.getInstance().closeConnection(rs, ps);
        }
    }

    @Override
    public boolean atualizar(PlacaresView placar) throws Exception {
        PreparedStatement ps = null;
        int i = 1;
        try {
            String sql = "update placar set jogo_id = ?, placar_casa = ?, placar_fora = ? where id = ?";
            ps = ConnectionProvider.getInstance().getConnection().prepareStatement(sql);
            ps.setLong(i++, placar.getJogoId());
            if (placar.getPlacarCasa() == null) {
                ps.setNull(i++, Types.NULL);
            } else {
                ps.setInt(i++, placar.getPlacarCasa());
            }
            if (placar.getPlacarFora() == null) {
                ps.setNull(i++, Types.NULL);
            } else {
                ps.setInt(i++, placar.getPlacarFora());
            }

            ps.setLong(i++, placar.getPlacarId());
            return ps.executeUpdate() == 1;
        } finally {
            ConnectionProvider.getInstance().closeConnection(ps);
        }
    }

    @Override
    public List<Pontuacao> buscarPontuacaoCampeonato() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Pontuacao> lista = new ArrayList<>();
        try {
            String sql = "select sub.times, sum(sub.pontos) AS pontos from ( "
                    + "select a.time_casa AS times, 3 AS pontos "
                    + "from jogos a "
                    + "LEFT JOIN placar b ON a.id = b.jogo_id "
                    + "where b.placar_casa is not null and b.placar_casa is not null "
                    + "and b.placar_casa > b.placar_fora "
                    + "UNION ALL "
                    + "select a.time_fora AS times, 0 AS pontos "
                    + "from jogos a "
                    + "LEFT JOIN placar b ON a.id = b.jogo_id "
                    + "where b.placar_casa is not null and b.placar_casa is not null "
                    + "and b.placar_casa > b.placar_fora "
                    + "UNION ALL "
                    + "select a.time_casa AS times, 1 AS pontos "
                    + "from jogos a "
                    + "LEFT JOIN placar b ON a.id = b.jogo_id "
                    + "where b.placar_casa is not null and b.placar_casa is not null "
                    + "and b.placar_casa = b.placar_fora "
                    + "UNION ALL "
                    + "select a.time_fora AS times, 3 AS pontos "
                    + "from jogos a "
                    + "LEFT JOIN placar b ON a.id = b.jogo_id "
                    + "where b.placar_casa is not null and b.placar_casa is not null "
                    + "and b.placar_casa < b.placar_fora "
                    + "UNION ALL "
                    + "select a.time_casa AS times, 0 AS pontos "
                    + "from jogos a "
                    + "LEFT JOIN placar b ON a.id = b.jogo_id "
                    + "where b.placar_casa is not null and b.placar_casa is not null "
                    + "and b.placar_casa < b.placar_fora "
                    + "UNION ALL "
                    + "select a.time_fora AS times, 1 AS pontos "
                    + "from jogos a "
                    + "LEFT JOIN placar b ON a.id = b.jogo_id "
                    + "where b.placar_casa is not null and b.placar_casa is not null "
                    + "and b.placar_casa = b.placar_fora "
                    + "UNION ALL "
                    + "select a.time_casa AS times, 0 AS pontos "
                    + "from jogos a "
                    + "LEFT JOIN placar b ON a.id = b.jogo_id "
                    + "where b.placar_casa is null OR b.placar_fora is null "
                    + "UNION ALL "
                    + "select a.time_fora AS times, 0 AS pontos "
                    + "from jogos a "
                    + "LEFT JOIN placar b ON a.id = b.jogo_id "
                    + "where b.placar_fora is null OR b.placar_casa is null "
                    + ") sub "
                    + "group by sub.times "
                    + "order by SUM(sub.pontos) desc, sub.times";
            ps = ConnectionProvider.getInstance().getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Long time = rs.getLong("times");
                lista.add(new Pontuacao(timeDao.getById(time), rs.getInt("pontos")));
            }
            return lista;
        } finally {
            ConnectionProvider.getInstance().closeConnection(rs, ps);
        }
    }

}
