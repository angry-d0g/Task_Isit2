package services.option;

import board.Cell;
import board.Direction;
import data.GameData;
import fiture.Figure;
import player.Player;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public interface MoveService {

    Map<Direction, Cell> getOptions(GameData d, Player p, Figure f, Cell c);

    default List<Cell> getEnemyCells(GameData data, Player p){
        return data.getPlayerCells().get(getEnemyPlayer(data, p));
    }

    static Player getEnemyPlayer(GameData data, Player p){
        return data.getPlayers().get(0).equals(p) ?
                data.getPlayers().get(data.getPlayers().size()-1) :
                data.getPlayers().get(0);
    }

}
