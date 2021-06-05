package it.polimi.ingsw.controller;

import it.polimi.ingsw.Game;
import it.polimi.ingsw.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LocalSinglePlayer {

    private static String nickname = "";

    private final static Game game = new Game();


    public static void singlePlayer() {
        System.out.println("Local mode: single player only, you will play against Lorenzo the Magnificent himself");
        try {
            askForNickname();
        } catch (EndingGameException e) {
            return;
        }
        try {
            gameCreator();
        } catch (EndingGameException e) {
            return;
        }

    }

    private static void gameCreator() throws EndingGameException {
        game.setNumberOfPlayers(1);
        game.addPlayer(new Player(nickname));
        try {
            game.initialSet();
        } catch (IOException e) {
            throw new EndingGameException();
        }
    }

    private static void askForNickname() throws EndingGameException {
        System.out.println("Select a nickname");
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        do {
            try {
                nickname = stdIn.readLine().toLowerCase();
            } catch (IOException e) {
                System.err.println("Unable to read input");
                throw new EndingGameException();
            }
        } while (nickname.isBlank() || nickname.isEmpty() || nickname.equals("lorenzothemagnificent"));
    }

    private static void gameProtocol() {

    }
}
