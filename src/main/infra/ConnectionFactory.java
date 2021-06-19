package main.infra;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private ConnectionFactory(){}

    //metodo para gerar conexao
    public static Connection getConnection(){
        try {
            Properties prop = getProperties();
            final String url = prop.getProperty("banco.url");
            final String user = prop.getProperty("banco.user");
            final String password = prop.getProperty("banco.password");

            return DriverManager.getConnection(url,user, password);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    //utilizando properties as informações para conexão com banco de dados não
    //ficam expostas no codigo
    private static Properties getProperties() throws IOException {
        Properties prop = new Properties();
        String caminho = "/conexao.properties";
        prop.load(ConnectionFactory.class.getResourceAsStream(caminho));
        return prop;
    }

}
