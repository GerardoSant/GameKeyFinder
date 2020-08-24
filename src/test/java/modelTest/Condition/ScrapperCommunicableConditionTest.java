package modelTest.Condition;

import Implementation.Scrapper.GameOffer;
import Model.Communicator.Communicator;
import Model.Communicator.GameCommunicator;
import Model.Condition.ScrapperCommunicableCondition;
import Model.Game.GameDesire;
import Model.Sensor.ScrapperSensor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ScrapperCommunicableConditionTest {

    @Test
    public void check_isTrue_when_scrappedGame_price_is_less_than_desiredGames(){
        /*Configure DOC*/
        //Mock
        ScrapperSensor scrapperSensorMock = mock(ScrapperSensor.class);
        Communicator gameCommunicator = mock(GameCommunicator.class);
        //Stub
        List<GameDesire> gameDesiresList = new ArrayList<>();
        gameDesiresList.add(new GameDesire("FIFA 20",20));
        List<GameOffer> scrappedGameOffers = new ArrayList<>();
        scrappedGameOffers.add(new GameOffer("",19, ""));
        when(scrapperSensorMock.getValue()).thenReturn(scrappedGameOffers);
        /*Configure SUT*/
        ScrapperCommunicableCondition scrapperCommunicableCondition = new ScrapperCommunicableCondition(gameDesiresList,scrapperSensorMock,gameCommunicator);
        /*Exec*/
        boolean check = scrapperCommunicableCondition.check();
        /*Verify*/
        assertThat(check).isTrue();
        /*Finalize*/
    }
    @Test
    public void check_isFalse_when_scrappedGame_price_is_higher_than_desiredGames(){
        /*Configure DOC*/
        //Mock
        ScrapperSensor scrapperSensorMock = mock(ScrapperSensor.class);
        Communicator gameCommunicator = mock(GameCommunicator.class);
        //Stub
        List<GameDesire> gameDesiresList = new ArrayList<>();
        gameDesiresList.add(new GameDesire("FIFA 20",20));
        List<GameOffer> scrappedGameOffers = new ArrayList<>();
        scrappedGameOffers.add(new GameOffer("",21, ""));
        when(scrapperSensorMock.getValue()).thenReturn(scrappedGameOffers);
        /*Configure SUT*/
        ScrapperCommunicableCondition scrapperCommunicableCondition = new ScrapperCommunicableCondition(gameDesiresList,scrapperSensorMock,gameCommunicator);
        /*Exec*/
        boolean check = scrapperCommunicableCondition.check();
        /*Verify*/
        assertThat(check).isFalse();
        /*Finalize*/
    }

    @Test
    public void check_isTrue_when_scrappedGame_of_many_price_is_less_than_desiredGames(){
        /*Configure DOC*/
        //Mock
        ScrapperSensor scrapperSensorMock = mock(ScrapperSensor.class);
        Communicator gameCommunicator = mock(GameCommunicator.class);
        //Stub
        List<GameDesire> gameDesiresList = new ArrayList<>();
        gameDesiresList.add(new GameDesire("FIFA 20",20));
        gameDesiresList.add(new GameDesire("Call of Duty",20));
        List<GameOffer> scrappedGameOffers = new ArrayList<>();
        scrappedGameOffers.add(new GameOffer("",20, ""));
        scrappedGameOffers.add(new GameOffer("",19, ""));
        when(scrapperSensorMock.getValue()).thenReturn(scrappedGameOffers);
        /*Configure SUT*/
        ScrapperCommunicableCondition scrapperCommunicableCondition = new ScrapperCommunicableCondition(gameDesiresList,scrapperSensorMock,gameCommunicator);
        /*Exec*/
        boolean check = scrapperCommunicableCondition.check();
        /*Verify*/
        assertThat(check).isTrue();
        /*Finalize*/
    }
    @Test
    public void check_isFalse_when_any_of_scrappedGames_are_less_than_desiredGames(){
        /*Configure DOC*/
        //Mock
        ScrapperSensor scrapperSensorMock = mock(ScrapperSensor.class);
        Communicator gameCommunicator = mock(GameCommunicator.class);
        //Stub
        List<GameDesire> gameDesiresList = new ArrayList<>();
        gameDesiresList.add(new GameDesire("FIFA 20",20));
        gameDesiresList.add(new GameDesire("Call of Duty",20));
        List<GameOffer> scrappedGameOffers = new ArrayList<>();
        scrappedGameOffers.add(new GameOffer("",20, ""));
        scrappedGameOffers.add(new GameOffer("",20, ""));
        when(scrapperSensorMock.getValue()).thenReturn(scrappedGameOffers);
        /*Configure SUT*/
        ScrapperCommunicableCondition scrapperCommunicableCondition = new ScrapperCommunicableCondition(gameDesiresList,scrapperSensorMock,gameCommunicator);
        /*Exec*/
        boolean check = scrapperCommunicableCondition.check();
        /*Verify*/
        assertThat(check).isTrue();
        /*Finalize*/
    }

}
