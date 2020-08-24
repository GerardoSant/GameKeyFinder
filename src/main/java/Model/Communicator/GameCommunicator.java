package Model.Communicator;

import Model.Game.GameSale;

import java.util.ArrayList;
import java.util.List;

public class GameCommunicator implements Communicator {

    private List<GameSale> gamesLowered;

    public GameCommunicator() {
        gamesLowered = new ArrayList<>();
    }

    @Override
    public Object get() {
        return gamesLowered;
    }

    @Override
    public void set(Object communicatingObject) {
        gamesLowered = (List<GameSale>) communicatingObject;
    }
}
