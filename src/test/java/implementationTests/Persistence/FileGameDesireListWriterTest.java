package implementationTests.Persistence;

import Implementation.View.Persistence.FileGameDesireListLoader;
import Implementation.View.Persistence.FileGameDesireListWriter;
import Model.Account;
import Model.Game.GameDesire;
import Persistence.GameDesireListLoader;
import Persistence.GameDesireListWriter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FileGameDesireListWriterTest {
    @Test
    public void check_add_adds_correct_GameDesire_to_file(){
        /*Configure DOC*/
        //Mock
        List<GameDesire> expectedGames = new ArrayList<>();
        expectedGames.add(new GameDesire("testGame",12));

        Account account = new Account("test2","test","test@gmail.com",12);
        GameDesire game = new GameDesire("testGame",12);

        /*Configure SUT*/
        GameDesireListWriter writer = new FileGameDesireListWriter(account);
        /*Exec*/
        writer.add(game);
        /*Verify*/
        GameDesireListLoader loader = new FileGameDesireListLoader(account);
        List<GameDesire> games = loader.load();

        for (int i = 0;i < games.size();i++){
            assertThat(expectedGames.get(i).getGameName()+","+expectedGames.get(i).getDesiredPrice())
                    .isEqualTo(games.get(i).getGameName()+","+games.get(i).getDesiredPrice());
        }
        /*Finalize*/
        writer.remove(game);
    }

    @Test(expected = Exception.class)
    public void check_add_does_not_add_incorrect_account_to_file_and_return_exception(){
        /*Configure DOC*/
        //Mock
        GameDesire game = mock(GameDesire.class);
        Account account = new Account("test2","test","test@gmail.com",12);
        when(game.getGameName()).thenReturn(null);


        /*Configure SUT*/
        GameDesireListWriter writer = new FileGameDesireListWriter(account);
        /*Exec*/
        writer.add(game);

    }

    @Test
    public void check_remove_removes_correct_GameDesire_from_file(){
        /*Configure DOC*/
        //Mock
        List<GameDesire> expectedGames = new ArrayList<>();
        expectedGames.add(new GameDesire("testGame",12));

        Account account = new Account("test2","test","test@gmail.com",12);
        GameDesire game = new GameDesire("testGame",12);

        /*Configure SUT*/
        GameDesireListWriter writer = new FileGameDesireListWriter(account);
        /*Exec*/
        writer.add(game);
        writer.remove(game);
        /*Verify*/
        GameDesireListLoader loader = new FileGameDesireListLoader(account);
        List<GameDesire> games = loader.load();

        for (int i = 0;i < games.size();i++){
            assertThat(expectedGames.get(i).getGameName()+","+expectedGames.get(i).getDesiredPrice())
                    .isEqualTo(games.get(i).getGameName()+","+games.get(i).getDesiredPrice());
        }
        /*Finalize*/
    }
}
