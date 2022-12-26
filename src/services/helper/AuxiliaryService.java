package services.helper;

import board.Cell;
import board.Direction;
import data.GameData;

import java.awt.*;
import java.util.*;

public class AuxiliaryService {
    public static Cell getCellByIndex(GameData data, Point p){
        return getCellByIndex(data, p.x, p.y);
    }
    public static Cell getCellByIndex(GameData data, int row, int col){
        return getCellByIndex(data.getBoard(), row, col);
    }

    public static Cell getCellByIndex(Cell cell, int row, int col){
        class Inner{
            Cell result = null;
            Set<Cell> visited = new HashSet<>();

            void search(Cell tmpCell, int tmpRow, int tmpCol){
                if(tmpCell == null || visited.contains(tmpCell))
                    return;
                if (tmpRow == row && tmpCol == col){
                    result = tmpCell;
                    return;
                }
                visited.add(tmpCell);
                search(tmpCell.getCell(Direction.UP), tmpRow-1, tmpCol);
                search(tmpCell.getCell(Direction.DOWN), tmpRow+1, tmpCol);
                search(tmpCell.getCell(Direction.RIGHT), tmpRow, tmpCol+1);
                search(tmpCell.getCell(Direction.LEFT), tmpRow, tmpCol-1);

                search(tmpCell.getCell(Direction.UP_RIGHT), tmpRow-1, tmpCol+1);
                search(tmpCell.getCell(Direction.UP_LEFT), tmpRow-1, tmpCol-1);

                search(tmpCell.getCell(Direction.DOWN_RIGHT), tmpRow+1, tmpCol+1);
                search(tmpCell.getCell(Direction.DOWN_LEFT), tmpRow+1, tmpCol-1);
            }
            void search() {
                if (!checkBorders(row, col))
                    return;
                search(cell, 1, 3);
            }
        }

        Inner i = new Inner();
        i.search();
        return i.result;
    }


    public static Point getIndexByCell(GameData data, Cell c){
        return getIndexByCell(data.getBoard(), c);
    }
    public static Point getIndexByCell(Cell start, Cell cell){
        class Inner{
            Point p = null;
            Set<Cell> visited = new HashSet<>();
            void search(Cell tmpCell, int tmpRow, int tmpCol){
                if(tmpCell == null || visited.contains(tmpCell))
                    return;

                if (cell.hashCode() == tmpCell.hashCode()){
                    p = new Point(tmpRow, tmpCol);
                    return;
                }
                visited.add(tmpCell);
                search(tmpCell.getCell(Direction.UP), tmpRow-1, tmpCol);
                search(tmpCell.getCell(Direction.DOWN), tmpRow+1, tmpCol);
                search(tmpCell.getCell(Direction.RIGHT), tmpRow, tmpCol+1);
                search(tmpCell.getCell(Direction.LEFT), tmpRow, tmpCol-1);

                search(tmpCell.getCell(Direction.UP_RIGHT), tmpRow-1, tmpCol+1);
                search(tmpCell.getCell(Direction.UP_LEFT), tmpRow-1, tmpCol-1);

                search(tmpCell.getCell(Direction.DOWN_RIGHT), tmpRow+1, tmpCol+1);
                search(tmpCell.getCell(Direction.DOWN_LEFT), tmpRow+1, tmpCol-1);
            }
            void search() {
                search(start, 1, 3);
            }
        }

        Inner i = new Inner();
        i.search();

        return i.p;
    }



    public static boolean checkBorders(int p1, int p2){
        if(p1 >= 8 || p1<=0 || p2 >= 8 || p2 <= 0) {
            return false;
        }
        if((p1 == 1 || p1 == 2 || p1 == 6 || p1 == 7) && (p2 <= 2 || p2 >= 6)) {
            return false;
        }
        return true;

    }




}
