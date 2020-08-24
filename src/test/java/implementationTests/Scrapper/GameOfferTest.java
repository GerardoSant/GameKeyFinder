package implementationTests.Scrapper;

import Implementation.Scrapper.GameOffer;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class GameOfferTest {
    @Test
    public void compareTo_return_0_when_gameOffers_have_same_price(){
        /*Configure DOC*/
        //Mock
        GameOffer gameOfferMock = mock(GameOffer.class);
        //Stub
        when(gameOfferMock.getPrice()).thenReturn(15.0);
        /*Configure SUT*/
        GameOffer gameOffer = new GameOffer("www.google.com", 15,"Google");
        /*Exec*/
        int compareResult = gameOffer.compareTo(gameOfferMock);
        /*Verify*/
        assertThat(compareResult).isEqualTo(0);
        /*Finalize*/
    }

    @Test
    public void compareTo_return_minus_when_gameOffer_have_lower_price_than_GameOffer2(){
        /*Configure DOC*/
        //Mock
        GameOffer gameOfferMock = mock(GameOffer.class);
        //Stub
        when(gameOfferMock.getPrice()).thenReturn(15.0);
        /*Configure SUT*/
        GameOffer gameOffer = new GameOffer("www.google.com", 14,"Google");
        /*Exec*/
        int compareResult = gameOffer.compareTo(gameOfferMock);
        /*Verify*/
        assertThat(compareResult).isEqualTo(-1);
        /*Finalize*/
    }

    @Test
    public void compareTo_return_1_when_gameOffers_greater_price_than_GameOffer2(){
        /*Configure DOC*/
        //Mock
        GameOffer gameOfferMock = mock(GameOffer.class);
        //Stub
        when(gameOfferMock.getPrice()).thenReturn(15.0);
        /*Configure SUT*/
        GameOffer gameOffer = new GameOffer("www.google.com", 16,"Google");
        /*Exec*/
        int compareResult = gameOffer.compareTo(gameOfferMock);
        /*Verify*/
        assertThat(compareResult).isEqualTo(1);
        /*Finalize*/
    }




}
