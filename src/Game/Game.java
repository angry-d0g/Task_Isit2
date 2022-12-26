package Game;

import board.Cell;
import board.Direction;
import data.GameData;
import fiture.Figure;
import player.Player;
import services.helper.AuxiliaryService;
import services.helper.ShowSevice;
import services.option.MoveService;
import services.reposition.RepositionService;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Game {

    public static void playTest(GameData data, Player firstPlayer){
        Player tmpPlayer = firstPlayer;
        //ShowSevice.printGameField(data);
        int count = 100000;
        while (count!=0){

            System.out.println("Moving "+tmpPlayer.getNickname()+": "+data.getPlayerGameSide().get(tmpPlayer));

            System.out.println();
            ShowSevice.printGameField(data);
            List<Cell> cells = data.getPlayerCells().get(tmpPlayer);
            if(cells.isEmpty()){
                System.out.println("Player "+MoveService.getEnemyPlayer(data, tmpPlayer).getNickname() +" lose");

                return;
            }
            List<Cell> tmpCells = getRndShape(tmpPlayer, data, cells);
            if(tmpCells.isEmpty()){
                System.out.println("Player "+tmpPlayer.getNickname() +" lose");
                return;
            }
            Cell tmpCell = tmpCells.get(new Random().nextInt(tmpCells.size()));
            Point p = AuxiliaryService.getIndexByCell(data.getBoard(), tmpCell);
            Figure f = data.getPositionShapes().get(tmpCell);
            System.out.println("From: "+p.x+" : "+p.y);

            Map<Direction, Cell> opt = data.getHowCanIMove().get(data.getPlayerGameSide().get(tmpPlayer)).
                    getOptions(data, tmpPlayer, data.getPositionShapes().get(tmpCell), tmpCell);



            Direction d = (Direction) opt.keySet().toArray()[new Random().nextInt(opt.size())];
            ShowSevice.printGameField(ShowSevice.getGameFieldWithOptions(data, castToList(opt.values()), p));
            Cell newCell = opt.get(d);


            p = AuxiliaryService.getIndexByCell(data.getBoard(), newCell);
            System.out.println("To "+p.x+" : "+p.y);

            tmpPlayer = RepositionService.reposition(data, tmpPlayer, f, tmpCell, newCell, d);

            ShowSevice.printGameField(data);
            //System.out.println(data.getPlayerCells().get(MoveService.getEnemyPlayer(data, firstPlayer)).size());
            System.out.println(data.getPlayerAnimals().get(MoveService.getEnemyPlayer(data, firstPlayer)).size());
            System.out.println("______________________________________________");
            count-=1;
        }
    }

    private static List<Cell> castToList(Collection<Cell> cells){
        List<Cell> res = new ArrayList<>();
        for (Cell c: cells) {
            res.add(c);
        }
        return res;
    }

    private static List<Cell> getRndShape(Player p, GameData d, List<Cell> cells){
        List<Cell> res = new ArrayList<>();

        for (Cell c:cells) {
            if(
                    !d.getHowCanIMove().get(d.getPlayerGameSide().get(p)).
                    getOptions(d, p, d.getPositionShapes().get(c), c).isEmpty())
                res.add(c);
        }


        return res;
    }
}
