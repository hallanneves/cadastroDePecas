package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.Peca;


/**Classe para fazer a interface entre a classe Peca e o banco da dados
* @author Hallan Neves
*/
public class PecaDAO{
    
    /** Método para retorno de todas as peças do banco de dados
    * @return ArrayList - Peças cadastradas*/
    public static ArrayList<Peca> getAllPecas() {
        String SQL = "SELECT * FROM pecas ORDER BY nome";
        ArrayList<Peca> pecas = new ArrayList<>();

        try {
            
            Connection conexao = DatabaseConnection.getInstance().getConnection();
            
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            
            while (rs.next()) {
                Peca p = new Peca(rs.getInt("id"), rs.getString("nome"), rs.getString("veiculo"), rs.getDouble("pesoLiquido"), rs.getDouble("pesoBruto"));
                pecas.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PecaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pecas;
    }

    /** Método para adicionar uma peça no banco de dados
    * @param p Peca - Peça nova que vai ser adicionada no banco de dados.
    */
    public static void addPeca(Peca p) {
        try {
            Connection conexao  = DatabaseConnection.getInstance().getConnection();
            
            String sql = "INSERT INTO pecas (nome, veiculo, pesoliquido, pesobruto)" + "VALUES(?,?,?,?)";
            PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getVeiculo());
            stmt.setDouble(3, p.getPesoLiquido());
            stmt.setDouble(4, p.getPesoBruto());
            
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Falhou em criar a peca, nenhuma linha afetada.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    p.setId(generatedKeys.getInt("id"));
                } else {
                    throw new SQLException("Falhou em criar a peca, nenhum id retornado.");
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(PecaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /** Método para remover uma peça do banco de dados
    * @param p Peca - Peça que vai ser removida do banco de dados.
    */
    public static void removePeca(Peca p) {
        try {
            Connection conexao  = DatabaseConnection.getInstance().getConnection();
            
            String sql = "DELETE FROM pecas WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, p.getId());
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Falhou em remover a peca, nenhuma linha afetada.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(PecaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
