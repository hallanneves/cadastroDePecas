package apresentacao;

import negocio.Peca;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import persistencia.PecaDAO;

/**Classe que controla a aplicação, telas e validação.
* @author Hallan Neves
*/

@SessionScoped
@Named
public class Manager implements Serializable {

    private ArrayList<Peca> pecas = null;
    private Peca novaPeca = new Peca(0, "", "", 0, 0);
    private String erro = "";
    private boolean temErro = false;

    /**Método para iniciar a classe DAO da peca e carregar todas as pecaso para a pagina inicial
    */
    private void inicializa() {
        if (pecas == null) {
            
            pecas = PecaDAO.getAllPecas();
        }
    }
    /** Método para retorno da lista de peça
     * @return ArrayList - Lista com todas as peças*/
    public ArrayList<Peca> getPecas() throws ClassNotFoundException {
        inicializa();
        return pecas;
    }

    /** Método para retorno de uma nova peça
     * @return Peca - Nova Peça*/
    public Peca getNovaPeca() {
        return novaPeca;
    }

    /** Método para retorno da menssagem de erro, uma vez vista também apaga a menssagem
    * @return String - Menssagem de erro*/
    public String getErro() {
        String erroTemp = erro;
        erro = "";
        temErro = false;
        return erroTemp;
    }

    /** Método para retorno caso um ou mais erros tenham ocorrido
    * @return Boolean - Houve um erro*/
    public boolean isTemErro() {
        return temErro;
    }

    /** Método para cadastro e para validação do cadastro de uma nova peça
     * este métoso tamém é responsável por salvar a peça no banco de dados
    * @return String - Página de redirecionamento*/
    public String cadastraPeca() {
        erro = "";

        System.out.println("Nome = " + novaPeca.getNome());
        System.out.println("Veiculo = " + novaPeca.getVeiculo());
        System.out.println("PesoLíquido = " + novaPeca.getPesoLiquido());
        System.out.println("Peso Bruto = " + novaPeca.getPesoBruto());

        if (novaPeca.getNome().length() < 1) {
            temErro = true;
            erro += "Nome deve ser preenchido <br>";
        }
        if (novaPeca.getPesoLiquido() <= 0) {
            temErro = true;
            erro += "Peso Líquido deve ser preenchido <br>";
        }
        if (novaPeca.getPesoBruto() <= 0) {
            temErro = true;
            erro += "Peso Bruto deve ser preenchido <br>";
        }
        if (novaPeca.getPesoBruto() < novaPeca.getPesoLiquido()) {
            temErro = true;
            erro += "O pesso bruto deve ser maior que o peso líquido <br>";
        }

        if (!temErro) {
            inicializa();
            DecimalFormat dc = new DecimalFormat("#.00");
            novaPeca.setPesoLiquido(Math.floor(novaPeca.getPesoLiquido() * 100.0) / 100.0);
            novaPeca.setPesoBruto(Math.floor(novaPeca.getPesoBruto() * 100.0) / 100.0);
            PecaDAO.addPeca(novaPeca);
            pecas.add(novaPeca);
            novaPeca = new Peca(0, "", "", 0, 0);
            return "index";
        } else {
            return "cadastro";
        }
    }

    /** Método que remove a peça da lista e do banco de dados 
    * @param p Peca - Objeto da peça que deve ser removida.
    * @return Boolean - Houve um erro*/
    public String removePeca(Peca p) {
        pecas.remove(p);
        PecaDAO.removePeca(p);
        return "index";

    }

}
