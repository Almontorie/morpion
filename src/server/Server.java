package server;

import javafx.beans.property.ObjectProperty;
import server.model.Game;

import server.launch.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT_IN_RED = 8080;
    public static final int PORT_IN_YELLOW = 8081;
    public static final int PORT_OUT_RED = 8090;
    public static final int PORT_OUT_YELLOW = 8091;
    private static final String ADRESS="127.0.0.1";

    public void run (){
        try{
            ServerSocket serverSocketYellow = new ServerSocket(PORT_IN_YELLOW);
            ServerSocket serverSocketRed = new ServerSocket(PORT_IN_RED);

            Socket socketInYellow = serverSocketYellow.accept();
            Socket socketInRed = serverSocketRed.accept();

            ObjectInputStream yellowInputStream = new ObjectInputStream(socketInYellow.getInputStream());
            ObjectInputStream redInputStream = new ObjectInputStream(socketInRed.getInputStream());

            Socket socketOutYellow = new Socket(ADRESS, PORT_OUT_YELLOW);
            Socket socketOutRed = new Socket(ADRESS, PORT_OUT_RED);

            ObjectOutputStream yellowOutputStream = new ObjectOutputStream(socketOutYellow.getOutputStream());
            ObjectOutputStream redOutputStream = new ObjectOutputStream(socketOutRed.getOutputStream());

            Game game = new Game();

            yellowOutputStream.writeObject(game);
            redOutputStream.writeObject(game);


//            while(game.getGagnant() == Game.DEFAULT){
//
//            }

//            ObjectInputStream objectInputStream = new ObjectInputStream(socketAccepte.getInputStream());
//            Game jeu = (Game)objectInputStream.readObject();
//            Affichage affichage = new Affichage();
//            affichage.affichGame(jeu);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

}
