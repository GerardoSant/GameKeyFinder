package modelTest.Sensor;

import Implementation.Scrapper.GameOffer;
import Model.Game.GameDesire;
import Model.Sensor.ScrapperSensor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ScrapperSensorTest {
    @Test
    public void getValue_scrapped_gameOffer_has_same_name_as_gameDesire(){
        /*Configure DOC*/
        List<GameDesire> gameDesireList = new ArrayList<>();
        gameDesireList.add(new GameDesire("Left 4 Dead",1));

        /*Configure SUT*/

        ScrapperSensor scrapperSensor = new ScrapperSensor(gameDesireList);
        /*Exec*/
        List<GameOffer> gameOfferList = (List<GameOffer>) scrapperSensor.getValue();

        /*Verify*/
        gameOfferList.forEach(gameOffer -> assertThat(gameOffer.getName()).isEqualTo(gameDesireList.get(gameOfferList.indexOf(gameOffer)).getGameName()));
        /*Finalize*/
    }

    @Test
    public void getValue_scrapped_gameOffers_has_same_name_as_gameDesires(){
        /*Configure DOC*/
        List<GameDesire> gameDesireList = new ArrayList<>();
        gameDesireList.add(new GameDesire("Left 4 Dead",1));
        gameDesireList.add(new GameDesire("Cuphead",1));

        /*Configure SUT*/

        ScrapperSensor scrapperSensor = new ScrapperSensor(gameDesireList);
        /*Exec*/
        List<GameOffer> gameOfferList = (List<GameOffer>) scrapperSensor.getValue();

        /*Verify*/
        gameOfferList.forEach(gameOffer -> assertThat(gameOffer.getName()).isEqualTo(gameDesireList.get(gameOfferList.indexOf(gameOffer)).getGameName()));
        /*Finalize*/
    }
}
