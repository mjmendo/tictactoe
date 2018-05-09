package game;


import game.config.AppContext;
import game.config.Config;
import game.internal.TicTacToeGame;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class App {

    public static final String GAME_PROPERTIES = "game.properties";

    public static void main(String[] args ){
        try {
            TicTacToeGame game = new AppContext().createTicTacToeApplication(readProperties());
            print(game.getPrintablePlayersList());
            game.getNextPlayer().forEach(message -> print(message));
            print(game.getPrintableBoard());

            while (true) {
                print("Enter your move (x,y): ");
                Scanner scanner = new Scanner(System.in);
                String move = scanner.next();
                print("Aplying move " + move + "\n");
                try {
                    List<String> messages = game.play(move);
                    messages.forEach(message -> print(message));
                    print(game.getPrintableBoard());
                    if (game.isDone()){
                        System.exit(0);
                    }
                    game.getNextPlayer().forEach(message -> print(message));
                } catch (IllegalArgumentException e) {
                    print(e.getMessage());
                    print(game.getPrintableBoard());
                }
            }
        } catch (Throwable e){
            System.out.println("Error has occurred. Reason: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void print(String line){
        System.out.println(line + "\r");
    }

    private static Config readProperties() {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            String filename = GAME_PROPERTIES;
            input = App.class.getClassLoader().getResourceAsStream(filename);
            if(input==null){
                System.out.println("Sorry, unable to find " + filename);
                return null;
            }
            prop.load(input);
            Config config = new Config();
            config.setBoardSize(Integer.valueOf(prop.getProperty("board.size")));
            config.setComputerSymbol(prop.getProperty("computer.symbol").charAt(0));
            config.setPlayer1Symbol(prop.getProperty("player1.symbol").charAt(0));
            config.setPlayer2Symbol(prop.getProperty("player2.symbol").charAt(0));
            config.setComputerPlayerOn(Boolean.valueOf(prop.getProperty("computer.enabled")));

            return config;
        } catch (IOException ex) {
            throw new IllegalStateException("Application's config file could not be loaded");
        } finally{
            if(input!=null){
                try {
                    input.close();
                } catch (IOException e) {
                    //shhh.....
                }
            }
        }
    }
}

