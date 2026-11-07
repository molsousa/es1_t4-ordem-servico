/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.apoio.bd;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.Context;

/**
 *
 * @author molsousa
 */
public class ConnectionFactory {

    private static DataSource dataSource;

    private static final String JNDI_NAME = "jdbc/OrdemServico";

    static {
        try {
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
            props.put(Context.PROVIDER_URL, "iiop://localhost:3700");

            InitialContext ctx = new InitialContext(props);
            dataSource = (DataSource) ctx.lookup(JNDI_NAME);

        } catch (NamingException e) {
            throw new RuntimeException("Erro ao buscar o DataSource no JNDI remoto: " + JNDI_NAME, e);
        }
    }

    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("DataSource não foi inicializado corretamente.");
        }

        Connection conn = dataSource.getConnection();
        conn.setAutoCommit(false);

        return conn;
    }
}
