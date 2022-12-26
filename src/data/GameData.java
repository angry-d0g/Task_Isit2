package data;

import board.Cell;
import board.Direction;
import fiture.Figure;
import fiture.TypeFigure;
import player.Player;
import services.helper.AuxiliaryService;
import services.option.MoveService;


import java.util.List;
import java.util.Map;

public class GameData {

    private Cell board;

    private List<Player> players;

    private Map<Figure, Cell> shapesPosition;

    private Map<Cell, Figure> positionShapes;

    private Map<Player, List<Figure>> playerAnimals;

    private Map<Player, List<Cell>> playerCells;

    private Map<TypeFigure, MoveService> howCanIMove;

    private Map<TypeFigure, List<Direction>> directionsFigure;

    private Map<Player, TypeFigure> playerGameSide;

    public Cell getBoard() {
        return board;
    }

    public void setBoard(Cell board) {


        this.board = board;
    }

    public Map<Figure, Cell> getShapesPosition() {


        return shapesPosition;
    }

    public void setShapesPosition(Map<Figure, Cell> shapesPosition) {//+

        this.shapesPosition = shapesPosition;
    }

    public Map<Cell, Figure> getPositionShapes() {
        return positionShapes;
    }

    public void setPositionShapes(Map<Cell, Figure> positionShapes) {
        this.positionShapes = positionShapes;
    }//+

    public Map<Player, List<Figure>> getPlayerAnimals() {
        return playerAnimals;
    }

    public void setPlayerAnimals(Map<Player, List<Figure>> playerAnimals) {
        this.playerAnimals = playerAnimals;
    }//+

    public Map<TypeFigure, MoveService> getHowCanIMove() {
        return howCanIMove;
    }

    public void setHowCanIMove(Map<TypeFigure, MoveService> howCanIMove) {
        this.howCanIMove = howCanIMove;
    }//+

    public Map<TypeFigure, List<Direction>> getDirectionsFigure() {
        return directionsFigure;
    }

    public void setDirectionsFigure(Map<TypeFigure, List<Direction>> directionsFigure) {//+
        this.directionsFigure = directionsFigure;
    }

    public Map<Player, List<Cell>> getPlayerCells() {
        return playerCells;
    }

    public void setPlayerCells(Map<Player, List<Cell>> playerCells) {
        this.playerCells = playerCells;
    }//+

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {//+

        this.players = players;
    }

    public Map<Player, TypeFigure> getPlayerGameSide() {
        return playerGameSide;
    }

    public void setPlayerGameSide(Map<Player, TypeFigure> playerGameSide) {
        this.playerGameSide = playerGameSide;
    }//+
}
