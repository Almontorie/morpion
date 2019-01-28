package client.clientYellow;

import client.Affichage;
import server.model.Game;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientYellow {
    private static final int PORT_IN = 8091;
    private static final int PORT_OUT = 8081;
    private static final String ADRESS="127.0.0.1";

    private int equipe = Game.YELLOW;

    private Game game;

    public void run (){

        try {
            Socket socketOut = new Socket(ADRESS, PORT_OUT);
            ObjectOutputStream OutputStream = new ObjectOutputStream(socketOut.getOutputStream());


            ServerSocket serverSocket = new ServerSocket(PORT_IN);
            Socket socketIn = serverSocket.accept();
            ObjectInputStream InputStream = new ObjectInputStream(socketIn.getInputStream());

            Affichage affichage = new Affichage();
            game = (Game) InputStream.readObject();

            while(game.getGagnant() == Game.DEFAULT){
                affichage.affichGame(game);
                play(affichage);
                OutputStream.writeObject(game);
                game = (Game) InputStream.readObject();
            }

            affichage.affichMsg("Le gagnant est : "+game.getGagnant());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(Affichage affichage){
        int x = affichage.saisie("x");
        int y = affichage.saisie("y");

        while(!game.insererPion(x,y, equipe)){
            affichage.affichMsg("Coordonnées invalides ! ");
            x = affichage.saisie("x");
            y = affichage.saisie("y");
        }
    }
}
