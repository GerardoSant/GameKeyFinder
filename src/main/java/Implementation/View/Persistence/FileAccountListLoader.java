package Implementation.View.Persistence;

import Model.Account;
import Persistence.AccountListLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileAccountListLoader implements AccountListLoader {

    private String fileUrl;

    public FileAccountListLoader(String fileUrl){
        this.fileUrl = fileUrl;
    }

    @Override
    public List<Account> load() {
        List<Account> accounts = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileUrl)));
            while (true){
                String line = reader.readLine();
                if (line == null) break;
                String[] parts = line.split(",");
                accounts.add(new Account(parts[0],parts[1],parts[2],Integer.parseInt(parts[3])));
            }
        } catch (IOException ex) {
        }
        return accounts;
    }

    public String loadEmail(Account account) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileUrl)));
            while (true){
                String line = reader.readLine();
                if (line == null) break;
                String[] parts = line.split(",");
                if (parts[0].equals(account.getName())) {
                    return parts[2];
                }
            }
        } catch (IOException ex) {
        }
        return "notFound";
    }

    public String loadHour(Account account) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileUrl)));
            while (true){
                String line = reader.readLine();
                if (line == null) break;
                String[] parts = line.split(",");
                if (parts[0].equals(account.getName())) {
                    return parts[3];
                }
            }
        } catch (IOException ex) {
        }
        return "notFound";
    }
}
