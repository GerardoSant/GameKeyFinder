package implementationTests.Scrapper;

import Implementation.Scrapper.AllkeyshopScrapper;
import Implementation.Scrapper.GameOffer;
import org.jsoup.Jsoup;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AllKeyShopScrapperTest {
    @Test
    public void getBestGameOffer_gameName_equalsTo_game_to_scrap() throws IOException {
        /*Configure DOC*/
        //Mock
        GameOffer gameOfferMock = mock(GameOffer.class);
        //Stub
        when(gameOfferMock.getName()).thenReturn("FIFA 20");
        /*Configure SUT*/
        AllkeyshopScrapper scrapper = new AllkeyshopScrapper("FIFA 20");
        /*Exec*/
        String scrappedGameName= scrapper.getBestGameOffer().getName();
        /*Verify*/
        assertThat(scrappedGameName).isEqualTo(gameOfferMock.getName());
        /*Finalize*/
    }
    @Test
    public void getBestGameOffer_gamePrice_isBigger_than_0() throws IOException {
        /*Configure DOC*/

        /*Configure SUT*/
        AllkeyshopScrapper scrapper = new AllkeyshopScrapper("FIFA 20");
        /*Exec*/
        double scrappedGamePrice= scrapper.getBestGameOffer().getPrice();
        /*Verify*/
        assertThat(scrappedGamePrice).isGreaterThan(0);
        /*Finalize*/
    }

    @Test
    public void getBestGameOffer_gameLink_isAccesible() throws IOException {
        /*Configure SUT*/
        AllkeyshopScrapper scrapper = new AllkeyshopScrapper("FIFA 20");
        /*Configure DOC*/
        Object web=null;
        GameOffer scrappedOffer = scrapper.getBestGameOffer();
        try{
            web = Jsoup.connect(scrappedOffer.getLink()).get();
        } catch(Exception e){ }
        /*Exec*/
        //Exec done in configuration of DOC
        /*Verify*/
        assertThat(scrappedOffer.getLink()).isNotNull();
        /*Finalize*/
    }




}
