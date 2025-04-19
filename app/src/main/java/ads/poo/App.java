package ads.poo;

import java.util.Scanner;
public class App {
    public static void main(String[] args) {
        //posição do jogo da velha
        char[] pos = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        Scanner sc = new Scanner(System.in);
        boolean jogoValido = true;
        int contPlayer = 1;
        exibeJogo(pos);

        while (jogoValido) {
            System.out.println("JOGADOR NÚMERO " + (contPlayer % 2 != 0 ? "1" : "2") + " - Selecione a posição no tabuleiro: ");
            String posPlayer = sc.next().toUpperCase();

            int indice = obterIndicePosicao(posPlayer);
            if (indice == -1) {
                System.out.println("Posição inválida! Tente novamente.");
                continue;
            }

            if (posicaoDisponivel(pos, indice)) {
                pos[indice] = (contPlayer % 2 != 0) ? 'X' : 'O';
                contPlayer++; // Somente incrementa se a jogada for válida
            } else {
                System.out.println("Essa posição já foi escolhida anteriormente. \nPor favor, tente novamente!");
                continue;
            }

            exibeJogo(pos);
            if (verificaVitoria(pos)) {
                int JogadorVencedor = contPlayer - 1;
                if (JogadorVencedor % 2 != 0) {
                    System.out.println("Jogador(a) número 1 venceu a partida!");
                } else {
                    System.out.println("Jogador(a) número 2 venceu a partida!");
                }
                break;
            }

            if (verificaEmpate(pos)) {
                System.out.println("O jogo terminou em empate!");
                break;

            }
        }
    }

    static void exibeJogo(char[] pos) {

        String[][] estruturaJogo = {
                {"     1   2   3 "},
                {"A |  " + pos[0] + " \u2502 " + pos[1] + " \u2502 " + pos[2] + " "},
                {"  | \u2501\u2501\u2501\u253C\u2501\u2501\u2501\u253C\u2501\u2501\u2501"},
                {"B |  " + pos[3] + " \u2502 " + pos[4] + " \u2502 " + pos[5] + " "},
                {"  | \u2501\u2501\u2501\u253C\u2501\u2501\u2501\u253C\u2501\u2501\u2501"},
                {"C |  " + pos[6] + " \u2502 " + pos[7] + " \u2502 " + pos[8] + " \n"}
        };

        for (int i = 0; i < estruturaJogo.length; i++) {
            System.out.println(estruturaJogo[i][0]);
        }
    }

    static boolean posicaoDisponivel(char[] pos, int i) {
        return pos[i] == ' ';
    }

    static void definirPosicaoJogadorUm(char[] pos, int i) {
        if (posicaoDisponivel(pos, i)) {
            pos[i] = 'X';
        } else {
            System.out.println("Essa posição já foi escolhida anteriormente. \nPor favor, tente novamente!");
        }
    }

    static void definirPosicaoJogadorDois(char[] pos, int i) {
        if (posicaoDisponivel(pos, i)) {
            pos[i] = 'O';
        } else {
            System.out.println("Essa posição já foi escolhida anteriormente. \nPor favor, tente novamente!");
        }
    }

    static int obterIndicePosicao(String posPlayer) {
        return switch (posPlayer) {
            case "A1" -> 0;
            case "A2" -> 1;
            case "A3" -> 2;
            case "B1" -> 3;
            case "B2" -> 4;
            case "B3" -> 5;
            case "C1" -> 6;
            case "C2" -> 7;
            case "C3" -> 8;
            default -> -1; // Caso inválido
        };
    }

    static boolean verificaVitoria(char[] pos) {
        // Verifica vitórias horizontais
        if ((pos[0] == pos[1] && pos[1] == pos[2] && pos[0] != ' ') ||
                (pos[3] == pos[4] && pos[4] == pos[5] && pos[3] != ' ') ||
                (pos[6] == pos[7] && pos[7] == pos[8] && pos[6] != ' ')) {
            return true;
        }

        // Verifica vitórias verticais
        if ((pos[0] == pos[3] && pos[3] == pos[6] && pos[0] != ' ') ||
                (pos[1] == pos[4] && pos[4] == pos[7] && pos[1] != ' ') ||
                (pos[2] == pos[5] && pos[5] == pos[8] && pos[2] != ' ')) {
            return true;
        }

        // Verifica vitórias diagonais
        if ((pos[0] == pos[4] && pos[4] == pos[8] && pos[0] != ' ') ||
                (pos[2] == pos[4] && pos[4] == pos[6] && pos[2] != ' ')) {
            return true;
        }

        return false;
    }

    static boolean verificaEmpate(char[] pos) {
        for (char c : pos) {
            if (c == ' ') return false;
        }
        return true;
    }
}