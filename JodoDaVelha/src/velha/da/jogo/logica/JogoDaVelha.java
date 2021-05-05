package velha.da.jogo.logica;

import java.util.Scanner;

public class JogoDaVelha {

    private static String[][] matriz;
    private Jogador[] jogadores; // apenas dois jogadores por jogo

    //bloco de inicialização
    static {
        matriz = new String[3][3];
        construirMatriz();
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
     * @param turno valor int que serve como base para definir qual dos jogadores será retornado. impar retorna
     *              o jogador[0], par jogador[1]
     * @return deve trazer um dos objetos dentro do array de objetos  'Jogador[] jogadores' que servirá na funcao
     * Escolha posicao;
     */
    public Jogador viradaDeTurno(int turno){
        if (turno % 2 != 0) {
            return jogadores[0];
        } else {
            return jogadores[1];
        }

    }

    /**
     *
     * @param jogador é o parametro do objeto Jogador
     */
    public void escolhaPosicao(Jogador jogador) {
        boolean prossegue;
        do {
            System.out.printf("%s Vai jogar %s em que posição? ", jogador.getNome(), jogador.getSimbolo());
            int posicao = k.nextInt();
            prossegue = jogando(posicao, jogador.getSimbolo());
            if (!prossegue) System.out.println("Jogada inválida! ");
        } while (!prossegue);
    }

    public boolean jogando(int posicao, String simbolo) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j].equals(String.valueOf(posicao))) {
                    matriz[i][j] = simbolo;
                    return true;
                }
            }
        }
        return false;
    }

    // Metodos de finalização

    public boolean condicoesFinalizarJogo(Jogador jogador) {
        boolean isAcabou;
        isAcabou = condicoesColunas() || condicoesLinhas() || condicoesDiagonais();
        //atribuir ponto ao jogador vencedor caso (isAcabou == true)
        if (isAcabou) {
            System.out.println("Acabou!");
            jogador.setPontuacao(jogador.getPontuacao()+1);
            return true;
        }
        return empate();
    }

    private boolean condicoesColunas() {
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[0][i].equals(matriz[1][i]) && matriz[0][i].equals(matriz[2][i])) {
                return true;
            }
        }
        return false;
    }

    private boolean condicoesLinhas() {
        for (String[] strings : matriz) {
            if (strings[0].equals(strings[1]) && strings[0].equals(strings[2])) {
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
