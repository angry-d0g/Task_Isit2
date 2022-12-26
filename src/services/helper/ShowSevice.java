package services.helper;

import board.Cell;
import data.GameData;
import fiture.Figure;
import fiture.TypeFigure;

import java.awt.*;
import java.util.List;

public class ShowSevice {
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String BLUE = "\033[0;34m";
    public static final String CYAN = "\033[0;36m";
    public static final String YELLOW = "\033[0;33m";
    public static String[][] getGameFieldWithoutFigures(){

        String[][] arr = new String[7][7];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                arr[i][j] = "   ";
            }
        }
        for (int i = 0; i < arr.length; i++) {
            int left = 7;
            int drop = 0;
            if(i < 2 || i > 4) {
                drop = 2;
                left = 3;
            }
            for (int j = 0; j < left; j++) {
                arr[i][j + drop]=" # ";
            }
        }



        return arr;
    }

    public static String[][] getGameFieldWithFigure(GameData d){
        String[][] arr = getGameFieldWithoutFigures();
        for (Figure f:d.getShapesPosition().keySet()) {
            String s = GREEN+" g "+ANSI_RESET;
            if(f.getTypeFigure() == TypeFigure.FOX)
                s = RED+" f "+ANSI_RESET;
            Point p = AuxiliaryService.getIndexByCell(d.getBoard(), d.getShapesPosition().get(f));
            arr[p.x-1][p.y-1] = s;
        }
        return arr;
    }

    public static String[][] getGameFieldWithOptions(GameData d, List<Cell> cells, Point p){
        String[][] s = getGameFieldWithFigure(d);
        System.out.println();
        if(s[p.x-1][p.y-1].equals(GREEN+" g "+ANSI_RESET)){
            s[p.x-1][p.y-1] = YELLOW+" g " +ANSI_RESET;
        }else if (s[p.x-1][p.y-1].equals(RED+" f "+ANSI_RESET) ){
            s[p.x-1][p.y-1] = YELLOW+" f " +ANSI_RESET;
        }

        System.out.println(s[p.x-1][p.y-1]);
        System.out.println();
        for (Cell c:cells) {
            p = AuxiliaryService.getIndexByCell(d, c);
            if(s[p.x-1][p.y-1].equals("#"))
                s[p.x-1][p.y-1] = CYAN+" * "+ANSI_RESET;
            else
                s[p.x-1][p.y-1] = BLUE+" * "+ANSI_RESET;
        }

        return s;
    }


    public static void printGameField(String[][] arr){
        System.out.print("  ");
        for (int i = 1; i <= 7; i++) {
            System.out.print(" "+i+" ");
        }
        System.out.println();

        for (int i = 0; i < arr.length; i++) {
            System.out.print((i+1)+" ");
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }

        System.out.println();
    }

    public static void printGameField(GameData d){
        String[][] arr = getGameFieldWithFigure(d);
        printGameField(arr);
    }
}
