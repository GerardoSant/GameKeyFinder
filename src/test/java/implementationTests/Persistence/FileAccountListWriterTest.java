package implementationTests.Persistence;

import Implementation.View.Persistence.FileAccountListLoader;
import Implementation.View.Persistence.FileAccountListWriter;
import Model.Account;
import Persistence.AccountListLoader;
import Persistence.AccountListWriter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FileAccountListWriterTest {
    @Test
    public void check_add_adds_correct_account_to_file(){
        /*Configure DOC*/
        //Mock
        List<Account> expectedAccounts = new ArrayList<>();
        Account account = new Account("test","test","test@gmail.com",12);

        expectedAccounts.add(new Account("test","test","test@gmail.com",12));
        /*Configure SUT*/
        AccountListWriter writer = new FileAccountListWriter("src/test/java/implementationTests/Persistence/tests2.txt");
        /*Exec*/
        writer.add(account);
        /*Verify*/
        AccountListLoader loader = new FileAccountListLoader("src/test/java/implementationTests/Persistence/tests2.txt");
        List<Account> accounts = loader.load();

        for (int i = 0;i < accounts.size();i++){
            assertThat(expectedAccounts.get(i).getName()+","+expectedAccounts.get(i).getPassword()+","+
                    expectedAccounts.get(i).getEmail()+","+expectedAccounts.get(i).getScraphour()).isEqualTo(
                    accounts.get(i).getName()+","+accounts.get(i).getPassword()+","+accounts.get(i).getEmail()
                            +","+accounts.get(i).getScraphour());
        }
        /*Finalize*/
        writer.remove(account);
    }

    @Test(expected = Exception.class)
    public void check_add_does_not_add_incorrect_account_to_file_and_return_exception(){
        /*Configure DOC*/
        //Mock
        Account account = mock(Account.class);
        when(account.getName()).thenReturn(null);

        /*Configure SUT*/
        AccountListWriter writer = new FileAccountListWriter("src/test/java/implementationTests/Persistence/tests2.txt");
        /*Exec*/
        writer.add(account);


    }



    @Test
    public void check_remove_removes_correct_account_from_file(){
        /*Configure DOC*/
        //Mock
        List<Account> expectedAccounts = new ArrayList<>();
        Account account = new Account("test","test","test@gmail.com",12);

        expectedAccounts.add(new Account("test","test","test@gmail.com",12));
        /*Configure SUT*/
        AccountListWriter writer = new FileAccountListWriter("src/test/java/implementationTests/Persistence/tests2.txt");
        /*Exec*/
        writer.add(account);
        writer.remove(account);
        /*Verify*/
        AccountListLoader loader = new FileAccountListLoader("src/test/java/implementationTests/Persistence/tests2.txt");
        List<Account> accounts = loader.load();

        for (int i = 0;i < accounts.size();i++){
            assertThat(expectedAccounts.get(i).getName()+","+expectedAccounts.get(i).getPassword()+","+
                    expectedAccounts.get(i).getEmail()+","+expectedAccounts.get(i).getScraphour()).isEqualTo(
                    accounts.get(i).getName()+","+accounts.get(i).getPassword()+","+accounts.get(i).getEmail()
                            +","+accounts.get(i).getScraphour());
        }
        /*Finalize*/
    }

    @Test
    public void check_editHour_changes_hour_from_account_in_file(){
        /*Configure DOC*/
        //Mock
        Account account = new Account("test","test","test@gmail.com",12);

        /*Configure SUT*/
        AccountListWriter writer = new FileAccountListWriter("src/test/java/implementationTests/Persistence/tests2.txt");

        /*Exec*/
        writer.add(account);
        account.setScraphour(15);
        writer.editHour(account);

        /*Verify*/
        AccountListLoader loader = new FileAccountListLoader("src/test/java/implementationTests/Persistence/tests2.txt");
        List<Account> accounts = loader.load();

        assertThat(account.getScraphour()).isEqualTo(15);

        /*Finalize*/
        writer.remove(account);
    }


}
