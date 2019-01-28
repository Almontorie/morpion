package server;

import server.model.Game;

public class Affichage{
    public void affichGame(Game game){
        int tab[][] = game.getTab();
        for(int i=0; i<Game.HAUTEUR; i++){
            for(int j=0; j<Game.LARGEUR; j++){
                System.out.print(tab[i][j]+" ");
            }
            System.out.println();
        }

        System.out.println(game.getGagnant()+" est Z boss");
    }
}
