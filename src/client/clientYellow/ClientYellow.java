package client.clientYellow;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import server.model.Game;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientYellow extends BorderPane {
    private static final int PORT_IN = 8091;
    private static final int PORT_OUT = 8081;
    private static final String ADRESS="127.0.0.1";

    private int equipe = Game.YELLOW;

    private Game game;

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;
    @FXML
    private Label gagnant;
    @FXML
    private Label team;

    private Button[][] buttons = new Button[Game.LARGEUR][Game.HAUTEUR];

    private boolean initGame = false;

    private AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            try {
                if(!initGame){
                    game = (Game) inputStream.readObject();
                    initButtons();
                    updateButtons();
                    initGame = true;
                }
                if (game.getGagnant() != Game.DEFAULT) {
                    if (game.getGagnant() == Game.EXAEQUO) {
                        gagnant.setText("Exeaquo !");
                    } else if (game.getGagnant() == Game.YELLOW) {
                        gagnant.setText("Le gagnant est X !");
                        gagnant.setTextFill(Color.RED);
                    } else if (game.getGagnant() == Game.RED) {
                        gagnant.setText("Le gagnant est O !");
                        gagnant.setTextFill(Color.BLUE);
                    }
                    disableAll();
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    };

    public ClientYellow() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/ressource/fxml/gameView.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        loader.load();
    }

    public void initialize (){

        try {
            Socket socketOut = new Socket(ADRESS, PORT_OUT);
            outputStream = new ObjectOutputStream(socketOut.getOutputStream());

            ServerSocket serverSocket = new ServerSocket(PORT_IN);
            Socket socketIn = serverSocket.accept();
            inputStream = new ObjectInputStream(socketIn.getInputStream());

            team.setText("Vous êtes X");

            animationTimer.start();

            System.out.println("bal");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initButtons(){
        buttons[0][0]=button1;
        buttons[0][1]=button2;
        buttons[0][2]=button3;
        buttons[1][0]=button4;
        buttons[1][1]=button5;
        buttons[1][2]=button6;
        buttons[2][0]=button7;
        buttons[2][1]=button8;
        buttons[2][2]=button9;
    }
    public void updateButtons(){
        for(int i=0;i<Game.LARGEUR;i++){
            for(int j=0;j<Game.HAUTEUR;j++){
                if(game.getTab()[i][j]==Game.YELLOW) {
                    buttons[i][j].setText("X");
                    buttons[i][j].setTextFill(Color.RED);
                    buttons[i][j].setDisable(true);
                }
                else if(game.getTab()[i][j]==Game.RED){
                    buttons[i][j].setText("O");
                    buttons[i][j].setTextFill(Color.BLUE);
                    buttons[i][j].setDisable(true);
                }
            }
        }
    }

    private void disableAll(){
        for(int i=0;i<Game.LARGEUR;i++){
            for(int j=0;j<Game.HAUTEUR;j++){
                buttons[i][j].setDisable(true);
            }
        }
    }

    @FXML
    public void onClick1(){
        play(0,0);
    }
    public void onClick2(){
        play(1,0);
    }
    public void onClick3(){
        play(2,0);
    }
    public void onClick4(){
        play(0,1);
    }
    public void onClick5(){
        play(1,1);
    }
    public void onClick6(){
        play(2,1);
    }
    public void onClick7(){
        play(0,2);
    }
    public void onClick8(){
        play(1,2);
    }
    public void onClick9(){
        play(2,2);
    }

    public void play(int x,int y){
        try {
            game.insererPion(x, y, equipe);
            outputStream.writeObject(game);
            updateButtons();
            game = (Game)inputStream.readObject();
            updateButtons();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
