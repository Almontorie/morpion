package client;

import server.model.Game;

import java.util.Scanner;

public class Affichage{
    public void affichGame(Game game){
        int tab[][] = game.getTab();
        for(int i=0; i<Game.HAUTEUR; i++){
            for(int j=0; j<Game.LARGEUR; j++){
                System.out.print(tab[i][j]+" ");
            }
            System.out.println();
        }
    }

    public int saisie(String axe){
        System.out.println("Coordonnée "+axe+" : ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public void affichMsg(String message){
        System.out.println(message);
        System.out.println();
    }
}
