package Model.Condition;

import Implementation.Scrapper.GameOffer;
import Model.Communicator.Communicator;
import Model.Game.GameDesire;
import Model.Game.GameSale;
import Model.Sensor.Sensor;

import java.util.ArrayList;
import java.util.List;

public class ScrapperCommunicableCondition extends CommunicableCondition {

    public ScrapperCommunicableCondition(Object threshold, Sensor sensor, Communicator communicator) {
        super(threshold, sensor, communicator);
    }

    @Override
    public boolean evaluate(Object leftObject, Object rightObject) {
        List<GameDesire> gameDesireList = (List<GameDesire>) leftObject;
        List<GameOffer> gameOffers = (List<GameOffer>) rightObject;
        return anyPriceIsLower(gameDesireList, gameOffers);
    }

    private boolean anyPriceIsLower(List<GameDesire> gameDesireList, List<GameOffer> gameOffers) {
        boolean checked= false;
        List<GameSale> auxList = new ArrayList<>();
        checked = rangePrices(gameDesireList, gameOffers, checked, auxList);
        communicator.set(auxList);
        return checked;
    }

    private boolean rangePrices(List<GameDesire> gameDesireList, List<GameOffer> gameOffers, boolean checked, List<GameSale> auxList) {
        for (int i = 0; i < gameDesireList.size(); i++) {
            checked = checkPrice(gameDesireList, gameOffers, checked, auxList, i);
        }
        return checked;
    }

    private boolean checkPrice(List<GameDesire> gameDesireList, List<GameOffer> gameOffers, boolean checked, List<GameSale> auxList, int i) {
        if (scrappedPriceIsLower(gameDesireList.get(i), gameOffers.get(i))){
            checked=true;
            auxList.add(new GameSale(gameDesireList.get(i).getGameName(),gameOffers.get(i).getPrice(),gameOffers.get(i).getLink(),gameOffers.get(i).getShop()));
        }
        return checked;
    }

    private boolean scrappedPriceIsLower(GameDesire gameDesire, GameOffer gameOffer) {
        try {
            return gameDesire.getDesiredPrice()>=gameOffer.getPrice();
        } catch(Exception e){
            return false;
        }
    }
}
