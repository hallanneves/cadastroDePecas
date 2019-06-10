package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**Classe para gerenciar a conexão do banco de dados
* @author Hallan Neves
*/
public class DatabaseConnection {

    private static DatabaseConnection instance = null;
    private Connection connection;
    private String url = "jdbc:postgresql://localhost:5432/Zen";
    private String username = "postgres";
    private String password = "99427489";

    /** Construtor da classe DatabaseConnection
     * @throws java.sql.SQLException*/
    private DatabaseConnection() throws SQLException{
        try {
            Class.forName("org.postgresql.Driver");	
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }
    
    /** Método para retorno da conexão do banco de dados
     * @return Connection - Conexão*/
    public Connection getConnection() {
        return connection;
    }
    
    /** Método que retorna um instancia da classe conexão
     * @return Connection - Intancia da classe de conexão
     * @throws java.sql.SQLException*/
    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }

        return instance;
    }
}