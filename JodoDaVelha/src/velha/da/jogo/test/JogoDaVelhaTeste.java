package velha.da.jogo.test;

import velha.da.jogo.logica.Jogador;
import velha.da.jogo.logica.JogoDaVelha;

public class JogoDaVelhaTeste {
    public static void main(String[] args) {
        JogoDaVelha func = new JogoDaVelha();

        // criei dois objetos jogador e fiz o relacionamento dos objetos criados serem adicionado no array de jogadores
        func.setJogadores(new Jogador[] {new Jogador("Felipe", 0, "X")
                , new Jogador("Wilker", 0, "O")});

        func.printJogadores();

        int turno = 0;
        Jogador jogador;
        do {
            turno++;
            func.mostrarTabuleiro();
            jogador = func.viradaDeTurno(turno);
            func.escolhaPosicao(jogador);
        } while (!func.condicoesFinalizarJogo(jogador));
        func.mostrarTabuleiro();
        func.fimDeJogo(jogador);
    }
}
