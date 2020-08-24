package modelTest.Communicator;

import Model.Communicator.GameCommunicator;
import Model.Game.GameSale;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class GameCommunicatorTest {

    @Test
    public void check_GameSales_Are_Saved_In_The_List(){
        /*Configure DOC*/
        //Mock
        GameSale game1 = mock(GameSale.class);
        GameSale game2 = mock(GameSale.class);

        //Stub

        /*Configure SUT*/
        List<GameSale> gameSaleList = new ArrayList<>();
        gameSaleList.add(game1);
        gameSaleList.add(game2);
        GameCommunicator gameCommunicator = new GameCommunicator();

        /*Exec*/
        gameCommunicator.set(gameSaleList);

        /*Verify*/
        assertThat((List<GameSale>)gameCommunicator.get()).isEqualTo(gameSaleList);

        /*Finalize*/
    }

    @Test
    public void check_List_Is_Null_When_The_GameSaleList_Is_Null(){
        /*Configure DOC*/
        //Mock

        //Stub

        /*Configure SUT*/
        List<GameSale> gameSaleList = null;
        GameCommunicator gameCommunicator = new GameCommunicator();

        /*Exec*/
        gameCommunicator.set(gameSaleList);

        /*Verify*/
        assertThat((List<GameSale>)gameCommunicator.get()).isEqualTo(gameSaleList);

        /*Finalize*/
    }

    @Test
    public void check_List_Is_Empty_When_The_GameSaleList_Is_Empty(){
        /*Configure DOC*/
        //Mock

        //Stub

        /*Configure SUT*/
        List<GameSale> gameSaleList = new ArrayList<>();
        GameCommunicator gameCommunicator = new GameCommunicator();

        /*Exec*/
        gameCommunicator.set(gameSaleList);

        /*Verify*/
        assertThat((List<GameSale>)gameCommunicator.get()).isEqualTo(gameSaleList);

        /*Finalize*/
    }
}
