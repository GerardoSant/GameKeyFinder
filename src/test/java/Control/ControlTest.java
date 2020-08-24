package Control;


import Model.Account;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.Test;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControlTest {
    @Test
    public void exampleTest(){
        /*Configure DOC*/
        //Mock
        Account account = mock(Account.class);

        //Stub
        when(account.getScraphour()).thenReturn(0);
        when(account.getName()).thenReturn("example");
        /*Configure SUT*/
        Control control = new Control(account);

        /*Exec*/
        control.init(account, "",0);
        control.initGameDesireList(new ArrayList<>());

        /*Verify*/
        AssertionsForClassTypes.assertThat(account.getScraphour()).isEqualTo(0);
        AssertionsForClassTypes.assertThat(account.getName()).isEqualTo("example");


        /*Finalize*/
    }
}
