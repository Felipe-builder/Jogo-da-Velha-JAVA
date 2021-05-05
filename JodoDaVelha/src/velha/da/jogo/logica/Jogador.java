package velha.da.jogo.logica;

public class Jogador {
    private String nome;
    private int pontuacao;
    private String simbolo;

    //Construtor

    public Jogador() {
    }

    public Jogador(String nome, int pontuacao) {
        this.nome = nome;
        this.pontuacao = pontuacao;
    }

    public Jogador(String nome, int pontuacao, String simbolo) {
        this(nome, pontuacao);
        this.simbolo = simbolo;
    }

    //Setters and Getters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }
}
