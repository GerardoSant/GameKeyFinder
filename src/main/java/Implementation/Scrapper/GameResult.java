package Implementation.Scrapper;

import java.util.List;

public class GameResult {
    private String gameName;
    private String gameLink;
    private List<GameOffer> gameOffers;

    public GameResult(String gameName, String gameLink, List<GameOffer> gameOffers) {
        this.gameName = gameName;
        this.gameLink = gameLink;
        this.gameOffers = gameOffers;
    }

    public String getGameName() {
        return gameName;
    }

    public String getGameLink() {
        return gameLink;
    }

    public List<GameOffer> getGameOffers() {
        return gameOffers;
    }

    public void setGameOffers(List<GameOffer> gameOffers) {
        this.gameOffers = gameOffers;
    }

    @Override
    public String toString() {
        return "gameName='" + gameName + '\'' +
                ", gameLink='" + gameLink + '\'' +
                ", gameOffers=" + gameOffers +
                '}';
    }
}
