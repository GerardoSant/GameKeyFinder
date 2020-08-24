package implementationTests.Persistence;

import Implementation.View.Persistence.FileAccountListLoader;
import Model.Account;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FileAccountListLoaderTest {
    @Test
    public void check_if_load_return_correct_list_of_accounts_from_file(){
        /*Configure DOC*/
        Account account = new Account("test","test","test@gmail.com",12);

        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account("test","test","test@gmail.com",12));

        /*Configure SUT*/
        FileAccountListLoader loader = new FileAccountListLoader("src/test/java/implementationTests/Persistence/tests.txt");
        /*Exec*/
        List<Account> loadedAccounts = loader.load();
        /*Verify*/

        for (int i = 0;i < accounts.size();i++){
            assertThat(loadedAccounts.get(i).getName()+","+loadedAccounts.get(i).getPassword()+","+
                    loadedAccounts.get(i).getEmail()+","+loadedAccounts.get(i).getScraphour()).isEqualTo(
                            accounts.get(i).getName()+","+accounts.get(i).getPassword()+","+accounts.get(i).getEmail()
                                    +","+accounts.get(i).getScraphour());
        }
        /*Finalize*/
    }

    @Test
    public void check_if_loadEmail_return_correct_email_from_file(){
        /*Configure DOC*/
        String email = "test@gmail.com";

        Account account = new Account("test","test","test@gmail.com",12);

        /*Configure SUT*/
        FileAccountListLoader loader = new FileAccountListLoader("src/test/java/implementationTests/Persistence/tests.txt");
        /*Exec*/
        String loadedEmail = loader.loadEmail(account);
        /*Verify*/
        assertThat(email).isEqualTo(loadedEmail);
        /*Finalize*/
    }

    @Test
    public void check_if_loadHour_return_correct_hour_from_file(){
        /*Configure DOC*/
        int hour = 12;

        Account account = new Account("test","test","test@gmail.com",12);

        /*Configure SUT*/
        FileAccountListLoader loader = new FileAccountListLoader("src/test/java/implementationTests/Persistence/tests.txt");
        /*Exec*/
        String loadedHour = loader.loadHour(account);
        /*Verify*/
        assertThat(hour).isEqualTo(Integer.parseInt(loadedHour));
        /*Finalize*/
    }
}
