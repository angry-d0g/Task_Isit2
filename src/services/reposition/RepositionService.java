package services.reposition;

import board.Cell;
import board.Direction;
import data.GameData;
import fiture.Figure;
import fiture.TypeFigure;
import player.Player;
import services.option.MoveService;

import java.util.List;
import java.util.Map;


public class RepositionService {

    public static Player reposition(GameData data, Player p, Figure f, Cell lastCell, Cell newCell, Direction d){
        if(newCell == null){
            System.out.println("U cant go to this position");
            return p;
        }
        /*System.out.println("�����: "+p);
        System.out.println("����� �������: "+lastCell);
        System.out.println("����� �������: "+newCell);
*/
        Player enemy = MoveService.getEnemyPlayer(data, p);
        if(data.getPlayerGameSide().get(p) == TypeFigure.GOOSE){
            reposWithoutKill(data, p, f, lastCell, newCell);
            return enemy;
        }else{
            Figure ef1 = data.getPositionShapes().getOrDefault(newCell, null);


            if(ef1 == null) {
                reposWithoutKill(data, p, f, lastCell, newCell);
                return enemy;
            }else {
                Cell nextCell = newCell.getCell(d);
                if(nextCell == null)
                    return p;
                Figure ef2 = data.getPositionShapes().getOrDefault(nextCell, null);;
                if(ef2 == null){
                    /*System.out.println("����");
                    System.out.println("������� ����� �������� "+nextCell);
                    System.out.println("������ ������� ���� "+ef1);*/
                    /*Map<Player, List<Cell>> pc = data.getPlayerCells();

                    List<Cell> cls = pc.get(enemy);
                    System.out.println(pc);
                    System.out.println();
                    cls.remove(newCell);
                    pc.replace(enemy, cls);
                    data.setPlayerCells(pc);*/


                    /*data.getPlayerCells().get(enemy).remove(newCell);

                    Map<Player, List<Figure>> pf = data.getPlayerAnimals();
                    System.out.println(pf);
                    System.out.println();
                    List<Figure> fgrs = pf.get(enemy);
                    System.out.println("�������� �� ����� ������: "+fgrs.contains(ef1));
                    fgrs.remove(ef1);
                    pf.replace(enemy, fgrs);
                    data.setPlayerAnimals(pf);
                    System.out.println(pf);
*/

                    data.getPlayerAnimals().get(enemy).remove(ef1);
                    data.getPositionShapes().remove(newCell);
                    data.getShapesPosition().remove(ef1);

                    reposWithoutKill(data, p, f, lastCell, nextCell);
                    return p;
                }


            }
            /*if(ef != null && nextCell != null && data.getPositionShapes().getOrDefault(newCell, null) == null){
                data.getPlayerCells().get(enemy).remove(newCell);
                data.getPlayerAnimals().get(enemy).remove(ef);
                reposWithoutKill(data, p, f, lastCell, newCell.getCell(d));
                return p;
            }
            reposWithoutKill(data, p, f, lastCell, newCell);*/
            return enemy;
        }
    }
    private static void reposWithoutKill(GameData data, Player p, Figure f, Cell lastCell, Cell newCell){

        /*Map<Player, List<Cell>> pC = data.getPlayerCells();
        List<Cell> l = pC.get(p);
        l.remove(lastCell);
        l.add(newCell);

        pC.replace(p, l);

        data.setPlayerCells(pC);*/
        data.getPlayerCells().get(p).remove(lastCell);
        data.getPlayerCells().get(p).add(newCell);

/*
        Map<Cell, Figure> cF = data.getPositionShapes();

        cF.remove(lastCell);

        cF.put(newCell, f);
        data.setPositionShapes(cF);*/
        data.getPositionShapes().remove(lastCell);
        data.getPositionShapes().put(newCell, f);

        /*Map<Figure, Cell> fC = data.getShapesPosition();

        fC.remove(f);
        fC.put(f, newCell);
        data.setShapesPosition(fC);*/
        data.getShapesPosition().remove(f);
        data.getShapesPosition().put(f, newCell);
    }

}
