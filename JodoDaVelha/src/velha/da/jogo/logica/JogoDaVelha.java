package velha.da.jogo.logica;

import java.util.Scanner;

public class JogoDaVelha {

    private static String[][] matriz;
    private static int turn;
    private Jogador[] jogadores; // apenas dois jogadores por jogo

    //bloco de inicialização
    static {
        matriz = new String[3][3];
        construirMatriz();
        JogoDaVelha.turn = 1;
    }

    Scanner k = new Scanner(System.in);

    // Métodos

    /**
     * Metodo que imprime o nome dos jogadores
     */
    public void printJogadores(){
        if (jogadores != null && jogadores.length != 0) {
            System.out.println("Os jogadores são: ");
            for (Jogador jogador : jogadores) {
                System.out.println(jogador.getNome() + " de " + jogador.getSimbolo());
            }
        } else {
            System.out.println("Não há jogadores inscritos");
        }
    }

    /**
     * metodo para contruir matriz
     */
    private static void construirMatriz() {
        int contador = 1;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = String.valueOf(contador);
                contador++;
            }
        }
    }

    /**
     * Método para mostrar o tabuleiro
     */
    public void mostrarTabuleiro() {
        System.out.println("-------------------------");
        for (String[] linha : matriz) {
            for (String value : linha) {
                System.out.printf("|\t%s\t", value);
            }
            System.out.println("|");
        }
        System.out.println("-------------------------");
    }

    /**
     * Este metodo serve para definir qual jogador é o dono da vez, atribuindo ao valor de retorno o jogador
     * detentor da vez.
     * @return deve trazer um dos objetos dentro do array de objetos  'Jogador[] jogadores' que servirá na funcao
     * Escolha posicao;
     */
    public Jogador viradaDeTurno(){
        if (JogoDaVelha.turn % 2 != 0) {
            return jogadores[0];
        }
        return jogadores[1];
    }

    /**
     * Este método busca primeiramente colher o dado da jogada escolhida e valida-lo posteriormente para então
     * definitivamente poder escrever (jogar) a posição então escolhida.
     * @param jogador é o parametro do objeto Jogador que detém a vez de jogar
     */
    public void escolhaPosicao(Jogador jogador) {
        String posicaoEscolhida = colherDadoDaJogada(jogador);
        jogando(posicaoEscolhida, jogador.getSimbolo());
        JogoDaVelha.turn++;
    }

    /**
     *  Este método será acionado após a coleta do dado de escolha de posicao do método 'escolhaPosicao' e substituir o
     *  valor númerico dentro do Array de String por um caracter 'X' ou 'O'.
     * @param posicao é o dado coletado para escrever o simbolo em seu lugar.
     * @param simbolo é o caracter 'X' ou 'O' do jogador da vez.
     */
    private void jogando(String posicao, String simbolo) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j].equals(posicao)) {
                    matriz[i][j] = simbolo;
                }
            }
        }
    }

    private String colherDadoDaJogada(Jogador jogador) {
        boolean erro = false;
        String posicaoEscolhida;
        do {
            if (erro) System.out.println("ERRO! Digite novamente: ");
            else
            System.out.printf("%s Vai jogar %s em que posição? ", jogador.getNome(), jogador.getSimbolo());
            posicaoEscolhida = k.next();
            erro = verificarErro(posicaoEscolhida);
        } while (erro);
        return posicaoEscolhida;
    }

    private boolean verificarErro(String posicaoEscolhida) {
        if (posicaoEscolhida.matches("\\d+") && (Integer.parseInt(posicaoEscolhida) <= 9 ||
                Integer.parseInt(posicaoEscolhida) >= 1)) {
            for (String[] strings : matriz) {
                for (String string : strings) {
                    if (string.equals(posicaoEscolhida)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Metodos de condicionamento para finalização

    public boolean condicoesFinalizarJogo(Jogador jogador) {
        boolean isAcabou;
        isAcabou = condicoesColunasELinhas() || condicoesDiagonais();
        //atribuir ponto ao jogador vencedor caso (isAcabou == true)
        if (isAcabou) {
            System.out.println("Acabou!");
            jogador.setPontuacao(jogador.getPontuacao()+1);
            return true;
        }
        return empate();
    }

    private boolean condicoesColunasELinhas() {
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[0][i].equals(matriz[1][i]) && matriz[0][i].equals(matriz[2][i])) {
                return true;
            }
            if (matriz[i][0].equals(matriz[i][1]) && matriz[i][0].equals(matriz[i][2])) {
                return true;
            }
        }
        return false;
    }

    private boolean condicoesDiagonais() {
        if (matriz[0][0].equals(matriz[1][1]) && matriz[0][0].equals(matriz[2][2])) {
            return true;
        }
        return matriz[0][2].equals(matriz[1][1]) && matriz[0][2].equals(matriz[2][0]);
    }

    private boolean empate() {
        int jogadasDisponiveis = 9;
        for (String[] strings : matriz) {
            for (String string : strings) {
                if (!string.matches("\\d+")) {
                    jogadasDisponiveis--;
                }
            }
        }
        return jogadasDisponiveis == 0;
    }

    // métodos após finalização do Jogo

    public void fimDeJogo(Jogador vencedor) {
        System.out.println("--FIM DE JOGO--");
        if (vencedor.getPontuacao() != 0) {
            System.out.println("JOGADOR VENCEDOR: ");
            System.out.printf("%s\n", vencedor.getNome());
        } else {
            System.out.println("JOGO EMPATADO");
        }
        System.out.println("------------------");
        System.out.println("Lista de jogadores: ");
        for (Jogador jog: getJogadores()) {
            System.out.print("Jogador: " + jog.getNome()+"     "+ jog.getPontuacao()+" pts\n");
        }
    }

    //Getters and Setters

    public Jogador[] getJogadores() {
        return jogadores;
    }

    public void setJogadores(Jogador[] jogadores) {
        this.jogadores = jogadores;
    }

    public static String[][] getMatriz() {
        return matriz;
    }

    public static void setMatriz(String[][] matriz) {
        JogoDaVelha.matriz = matriz;
    }
}
