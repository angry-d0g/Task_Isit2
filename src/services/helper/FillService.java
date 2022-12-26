package services.helper;

import board.Cell;
import board.Direction;
import data.GameData;
import fiture.Figure;
import fiture.TypeFigure;
import player.Player;
import services.option.FoxService;
import services.option.GooseService;
import services.option.MoveService;

import java.lang.reflect.Type;
import java.util.*;

public class FillService {

    public static Cell createBoard() {
        class Inner {
            Cell getBoard() {
                List<List<Cell>> matrix = new ArrayList<>();

                for (int i = 0; i < 2; i++) {
                    matrix.add(createLine(3));
                }
                for (int i = 0; i < 3; i++) {
                    matrix.add(createLine(7));
                }
                for (int i = 0; i < 2; i++) {
                    matrix.add(createLine(3));
                }

                bind(matrix.get(0), matrix.get(1));
                bind(matrix.get(1), getList(matrix.get(2), 2, 5));

                bind(matrix.get(2), matrix.get(3));
                bind(matrix.get(3), matrix.get(4));

                bind(getList(matrix.get(4), 2, 5), matrix.get(5));
                bind(matrix.get(5), matrix.get(6));

                return matrix.get(0).get(0);
            }

            private static List<Cell> createLine(int count) {
                Cell c = new Cell();
                List<Cell> cells = new ArrayList<>();
                for (int i = 0; i < count - 1; i++) {
                    c.setCells(Direction.RIGHT);
                    cells.add(c);
                    c = c.getCell(Direction.RIGHT);
                }
                cells.add(c);
                for (int i = 1; i < cells.size(); i++) {
                    cells.get(i).setCells(Direction.LEFT, cells.get(i - 1));
                }
                return cells;
            }

            private static void bind(List<Cell> up, List<Cell> down) {
                for (int i = 0; i < up.size(); i++) {
                    up.get(i).setCells(Direction.DOWN, down.get(i));
                    down.get(i).setCells(Direction.UP, up.get(i));
                }
                for (int i = 0; i < down.size() - 1; i++) {
                    down.get(i).setCells(Direction.UP_RIGHT, up.get(i + 1));
                    up.get(i + 1).setCells(Direction.DOWN_LEFT, down.get(i));

                    up.get(i).setCells(Direction.DOWN_RIGHT, down.get(i + 1));
                }
                for (int i = 1; i < down.size(); i++) {
                    down.get(i).setCells(Direction.UP_LEFT, up.get(i - 1));
                }


            }

            private static List<Cell> getList(List<Cell> cells, int from, int to) {
                List<Cell> res = new ArrayList<>();

                for (int i = from; i < to; i++) {
                    res.add(cells.get(i));
                }
                return res;
            }
        }
        return new Inner().getBoard();
    }

    public static GameData getGameData() {
        GameData gameData = new GameData();
        gameData.setBoard(createBoard());
        gameData.setPlayers(List.of(new Player("p1"), new Player("p2")));

        fillShapes(gameData);
        setMoves(gameData);

        return gameData;
    }

    private static void fillShapes(GameData gameData) {
        Map<Figure, Cell> fC = new HashMap<>();
        Map<Cell, Figure> cF = new HashMap<>();

        Map<Player, List<Figure>> pF = new HashMap<>();
        List<Figure> lG = new ArrayList<>();

        Map<Player, List<Cell>> pC = new HashMap<>();
        List<Cell> lC = new ArrayList<>();

        Map<Player, TypeFigure> pS = new HashMap<>();
        pS.put(gameData.getPlayers().get(0), TypeFigure.FOX);
        pS.put(gameData.getPlayers().get(1), TypeFigure.GOOSE);
        gameData.setPlayerGameSide(pS);

        for (int i = 1; i <= 2; i++) {
            for (int j = 3; j <= 5; j++) {
                Figure f = new Figure(TypeFigure.GOOSE);
                Cell c = AuxiliaryService.getCellByIndex(gameData, i, j);
                fC.put(f, c);
                cF.put(c, f);
                lG.add(f);

                lC.add(c);
            }
        }

        for (int j = 1; j <= 7; j++) {
            Figure f = new Figure(TypeFigure.GOOSE);
            Cell c = AuxiliaryService.getCellByIndex(gameData, 3, j);
            fC.put(f, c);
            cF.put(c, f);

            lG.add(f);

            lC.add(c);
        }

        Figure f = new Figure(TypeFigure.GOOSE);
        Cell c = AuxiliaryService.getCellByIndex(gameData, 4, 1);
        fC.put(f, c);
        cF.put(c, f);
        lG.add(f);

        lC.add(c);

        f = new Figure(TypeFigure.GOOSE);
        c = AuxiliaryService.getCellByIndex(gameData, 4, 7);
        fC.put(f, c);
        cF.put(c, f);

        pF.put(gameData.getPlayers().get(1), lG);
        pC.put(gameData.getPlayers().get(1), lC);


        f = new Figure(TypeFigure.FOX);
        c = AuxiliaryService.getCellByIndex(gameData, 4, 4);
        fC.put(f, c);
        cF.put(c, f);
        List<Figure> lf = new ArrayList<>();
        lf.add(f);
        pF.put(gameData.getPlayers().get(0), lf);
        List<Cell> lc = new ArrayList<>();
        lc.add(c);
        pC.put(gameData.getPlayers().get(0), lc);

        gameData.setShapesPosition(fC);
        gameData.setPositionShapes(cF);
        gameData.setPlayerAnimals(pF);
        gameData.setPlayerCells(pC);
        gameData.setPlayerGameSide(pS);
    }

    private static void setMoves(GameData gameData) {
        Map<TypeFigure, MoveService> hcim = new TreeMap<>();
        hcim.put(TypeFigure.GOOSE, new GooseService());
        hcim.put(TypeFigure.FOX, new FoxService());

        Map<TypeFigure, List<Direction>> df = new TreeMap<>();
        df.put(TypeFigure.GOOSE, List.of(Direction.DOWN, Direction.LEFT, Direction.RIGHT, Direction.DOWN_LEFT, Direction.DOWN_RIGHT));
        df.put(TypeFigure.FOX, List.of(Direction.UP, Direction.UP_LEFT, Direction.UP_RIGHT, Direction.DOWN, Direction.LEFT, Direction.RIGHT, Direction.DOWN_LEFT, Direction.DOWN_RIGHT));
        gameData.setHowCanIMove(hcim);
        gameData.setDirectionsFigure(df);
    }


}
