package Model.Game;

import Implementation.Scrapper.GameOffer;

public class GameDesire {
    private String gameName;
    private int desiredPrice;
    private GameOffer gameOffer;

    public GameDesire(String gameName, int desiredPrice) {
        this.gameName = gameName;
        this.desiredPrice = desiredPrice;
    }

    public String getGameName() {
        return gameName;
    }

    public int getDesiredPrice() {
        return desiredPrice;
    }

    public void setGameOffer(GameOffer gameOffer) {
        this.gameOffer = gameOffer;
    }
}
