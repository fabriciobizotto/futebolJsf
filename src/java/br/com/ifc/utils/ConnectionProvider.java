/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author fabricio
 */
public class ConnectionProvider {

    private static ConnectionProvider instance;
    private static Connection connection = null;

    private final String CLASSE = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/";
    private final String database = "futebol";
    private String usuario = "root";
    private String senha = "root";

    private ConnectionProvider() throws Exception {
        try {
            Class.forName(CLASSE);
            connection = DriverManager.getConnection(this.url + this.database, this.usuario, this.senha);
        } catch (Exception classNotFoundException) {
            throw new Exception(classNotFoundException);
        }
    }

    public static ConnectionProvider getInstance() throws Exception {
        if (instance == null || connection == null || connection.isClosed()) {
            instance = new ConnectionProvider();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection(ResultSet rs, PreparedStatement ps) throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception ex) {
                throw ex;
            }
        }
    }

    public void closeConnection(PreparedStatement ps) throws Exception {
        closeConnection(null, ps);
    }
    
    public void closeConnection() throws Exception {
        closeConnection(null, null);
    }

    public void rollback() throws Exception {
        if (connection != null) {
            try {
//                connection.rollback();
            } catch (Exception sqlException) {
                throw sqlException;
            }
        }
    }
}
