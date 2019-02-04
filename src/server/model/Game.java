package server.model;


import java.io.Serializable;

public class Game implements Serializable {
    public final static int LARGEUR = 3;
    public final static int HAUTEUR= 3;
    public final static int DEFAULT = 0;
    public final static int YELLOW = 1;
    public final static int RED = 2;
    public final static int EXAEQUO = 3;



    private int[][] tab = new int[LARGEUR][HAUTEUR];
    private int cptPion = 0;
    private int gagnant = DEFAULT;

    public int[][] getTab() {
        return tab;
    }

    public int getGagnant() {
        return gagnant;
    }

    public boolean insererPion(int x, int y, int color) {
        //nous avions inversé x et y
        int tmp = x;
        x = y;
        y = tmp;

        if(x > LARGEUR-1 || y > HAUTEUR-1 || x < 0 || y < 0) return false;


        if(!isSetCell(x,y)) {
            tab[x][y] = color;
            cptPion++;
            if (cptPion >= 5)
                aGagner(x, y);
            return true;
        }
        return false;
    }


    private boolean aGagner(int x, int y){
        int color = tab[x][y];

        if(testColonne(x, y, color)) return true;
        if(testLigne(x, y, color)) return true;
        if(testDiagonale(x, y, color)) return true;

        if(cptPion >= 9) {
            gagnant = EXAEQUO;
            return true;
        }

        return false;
    }

    private boolean testColonne(int x, int y, int color){
        if(y == 0){
            if(tab[x][y+1] == color){
                if(tab[x][y+2] == color) {
                    this.gagnant = color;
                    return true;
                }
            }
        }
        else if(y == 1){
            if(tab[x][y-1] == color){
                if(tab[x][y+1] == color) {
                    this.gagnant = color;
                    return true;
                }
            }
        }
        else if(y == 2) {
            if (tab[x][y - 1] == color) {
                if (tab[x][y - 2] == color) {
                    this.gagnant = color;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean testLigne(int x, int y, int color){
        if(x == 0){
            if(tab[x+1][y] == color){
                if(tab[x+2][y] == color) {
                    this.gagnant = color;
                    return true;
                }
            }
        }
        else if(x == 1){
            if(tab[x-1][y] == color){
                if(tab[x+1][y] == color) {
                    this.gagnant = color;
                    return true;
                }
            }
        }
        else if(x == 2){
            if(tab[x-1][y] == color){
                if(tab[x-2][y] == color) {
                    this.gagnant = color;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean testDiagonale(int x, int y, int color){
        if(x == 0){
            if(y == 0){
                if(tab[x+1][y+1] == color){
                    if(tab[x+2][y+2] == color){
                        gagnant = color;
                        return true;
                    }
                }
            }
            else if (y == 2){
                if(tab[x+1][y-1] == color){
                    if(tab[x+2][y-2] == color){
                        gagnant = color;
                        return true;
                    }
                }
            }
        }
        else if (x == 1 && y == 1){
            if(tab[x-1][y+1] == color){
                if(tab[x+1][y-1] == color){
                    gagnant = color;
                    return true;
                }
            }
            if(tab[x-1][y-1] == color){
                if(tab[x+1][y+1] == color){
                    gagnant = color;
                    return true;
                }
            }
        }
        else if( x == 2){
            if(y == 0){
                if(tab[x-1][y+1] == color){
                    if(tab[x-2][y+2] == color){
                        gagnant = color;
                        return true;
                    }
                }
            }
            else if (y == 2){
                if(tab[x-1][y-1] == color){
                    if(tab[x-2][y-2] == color){
                        gagnant = color;
                        return true;
                    }
                }
            }
        }

        return false;
    }



    private boolean isSetCell(int x, int y){
        if(tab[x][y] == DEFAULT){
            return false;
        }
        return true;
    }
}
