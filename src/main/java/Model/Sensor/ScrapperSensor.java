package Model.Sensor;

import Implementation.Scrapper.AllkeyshopScrapper;
import Implementation.Scrapper.GameOffer;
import Model.Game.GameDesire;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScrapperSensor implements Sensor {

    private List<GameDesire> gameDesires;

    public ScrapperSensor(List<GameDesire> gameDesires) {
        this.gameDesires= gameDesires;
    }



    @Override
    public Object getValue() {
        try {
            return getGameOfferList();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Object getGameOfferList() throws IOException {
        List<GameOffer> bestGameOffers = new ArrayList<>();
        for (GameDesire game : gameDesires){
            bestGameOffers.add(new AllkeyshopScrapper(game.getGameName()).getBestGameOffer());
        }
        return bestGameOffers;
    }

    public void setGameDesires(List<GameDesire> gameDesires) {
        this.gameDesires = gameDesires;
    }
}
