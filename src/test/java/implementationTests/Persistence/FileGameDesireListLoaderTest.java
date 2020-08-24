package implementationTests.Persistence;

import Implementation.View.Persistence.FileGameDesireListLoader;
import Model.Account;
import Model.Game.GameDesire;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FileGameDesireListLoaderTest {
    @Test
    public void check_if_load_return_correct_list_of_accounts_from_file(){
        /*Configure DOC*/
        Account account = new Account("test","test","test@gmail.com",12);

        GameDesire game = new GameDesire("test game",12);
        List<GameDesire> games = new ArrayList<>();
        games.add(game);

        /*Configure SUT*/
        FileGameDesireListLoader loader = new FileGameDesireListLoader(account);
        /*Exec*/
        List<GameDesire> loadedGames = loader.load();
        /*Verify*/
        for (int i = 0;i < games.size();i++){
            assertThat(loadedGames.get(i).getGameName()+","+loadedGames.get(i).getDesiredPrice())
                    .isEqualTo(games.get(i).getGameName()+","+games.get(i).getDesiredPrice());
        }
        /*Finalize*/
    }
}
