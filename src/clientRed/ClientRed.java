package clientRed;

import server.Affichage;
import server.model.Game;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientRed {
    private static final int PORT_IN = 8090;
    private static final int PORT_OUT = 8080;
    private static final String ADRESS="127.0.0.1";

    private int equipe = Game.RED;

    public void run (){

        try {
            Game game;

            Socket socketOut = new Socket(ADRESS, PORT_OUT);
            ObjectOutputStream OutputStream = new ObjectOutputStream(socketOut.getOutputStream());


            ServerSocket serverSocket = new ServerSocket(PORT_IN);
            Socket socketIn = serverSocket.accept();
            ObjectInputStream InputStream = new ObjectInputStream(socketIn.getInputStream());

            game = (Game)InputStream.readObject();

            Affichage affichage = new Affichage();
            affichage.affichGame(game);

//            Game game = new Game();
//            Socket socket = new Socket(ADRESS,PORT);
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
//            objectOutputStream.writeObject(game);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
