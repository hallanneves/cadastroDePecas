package negocio;

import java.io.Serializable;

/**Classe que contem os atributos de uma peça
* @author Hallan Neves
*/
public class Peca implements Serializable{
    private int id;
    private String nome;
    private String veiculo;
    private double pesoLiquido;
    private double pesoBruto;

    /**Construtor da classe peça
    
    * @param id int - Identificador da classe no sistema e na base de dados.
    * @param nome String - Nome da peça.
    * @param veiculo String - Nome do veículo de aplicação da peça.
    * @param pesoLiquido Double - Peso líquido da peça.
    * @param pesoBruto Double - Peso bruto da peça.
    */
    public Peca(int id, String nome, String veiculo, double pesoLiquido, double pesoBruto) {
        this.id = id;
        this.nome = nome;
        this.veiculo = veiculo;
        this.pesoLiquido = pesoLiquido;
        this.pesoBruto = pesoBruto;
    }
    
    /** Método para retorno do id da peça
     * @return int - Id da peça*/
    public int getId() {
        return id;
    }

    /** Método para atribuir o id da peça
     * @param id int - Id da peça.
    */
    public void setId(int id) {
        this.id = id;
    }

    /** Método para retorno do nome da peça
     * @return String - Nome da peça*/
    public String getNome() {
        return nome;
    }

    /** Método para atribuir o nome da peça
     * @param nome String - Nome da peça.
    */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /** Método para retorno do veículo de aplicação da peça
     * @return int - Veículo de aplicação da peça*/
    public String getVeiculo() {
        return veiculo;
    }

    /** Método para atribuir o veículo de aplicação da peça
     * @param veiculo String - Veículo de aplicação da peça.
    */
    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    /** Método para retorno do peso líquido da peça
     * @return double - Peso líquido da peça*/
    public double getPesoLiquido() {
        return pesoLiquido;
    }

    /** Método para atribuir o peso líquido da peça
     * @param pesoLiquido double - Peso líquidoo da peça.
     */
    public void setPesoLiquido(double pesoLiquido) {
        this.pesoLiquido = pesoLiquido;
    }

    /** Método para retorno do peso bruto da peça
     * @return double - Peso bruto da peça*/
    public double getPesoBruto() {
        return pesoBruto;
    }

    /** Método para atribuir o peso bruto da peça
     * @param pesoBruto double - Peso bruto da peça.
     */
    public void setPesoBruto(double pesoBruto) {
        this.pesoBruto = pesoBruto;
    }
    
    
}
