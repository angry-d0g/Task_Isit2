import Game.Game;
import data.GameData;
import player.Player;
import services.helper.AuxiliaryService;
import services.helper.FillService;
import services.helper.ShowSevice;
import services.option.FoxService;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        GameData gameData = FillService.getGameData();

        Game.playTest(gameData, gameData.getPlayers().get(0));

    }
}
