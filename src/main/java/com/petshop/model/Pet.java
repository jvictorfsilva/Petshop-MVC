package model;

public class Pet {
    private int id;
    private String nome;
    private int idade;
    private String tipo;
    private String raca;
    private int idCliente;

    public Pet(int id, String nome, int idade, String tipo, String raca, int idCliente) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.tipo = tipo;
        this.raca = raca;
        this.idCliente = idCliente;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public String getTipo() { return tipo; }
    public String getRaca() { return raca; }
    public int getIdCliente() { return idCliente; }

    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setIdade(int idade) { this.idade = idade; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setRaca(String raca) { this.raca = raca; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
}
