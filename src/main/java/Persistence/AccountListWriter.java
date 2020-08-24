package Persistence;

import Model.Account;

public interface AccountListWriter extends Writer<Account> {
    void editHour(Account account);
}
