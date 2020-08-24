package Implementation.View.Persistence;

import Model.Account;
import Persistence.AccountListWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileAccountListWriter implements AccountListWriter {

    private String fileUrl;

    public FileAccountListWriter(String fileUrl){
        this.fileUrl = fileUrl;
    }

    @Override
    public void add(Account account) {
        if(account.getName() != null && account.getPassword() != null && account.getEmail() != null &&
                account.getScraphour() >= 0 && account.getScraphour() <=23){
            try {
                String toAdd = account.getName()+","+account.getPassword()+","+account.getEmail()+","+account.getScraphour();
                checkIfAcocuntAlreadyInSystem(toAdd);
                FileWriter writer = new FileWriter(fileUrl,true); //the true will append the new data
                writer.write(toAdd+"\r\n");//appends the string to the file
                writer.close();
            } catch (IOException e) {
                System.out.println("Excepcion reading file:"+ e);
            }

        }else{
            throw new NumberFormatException();
        }

    }

    @Override
    public void remove(Account account)  {

        String toRemove = account.getName()+","+account.getPassword()+","+account.getEmail()+","+account.getScraphour();
        try {
            List<String> tmp = searchLineToDelete(account, toRemove);
            clearFile(fileUrl);
            rewriteFile(tmp);

        }catch(Exception e){
            System.out.println("Excepcion reading file:"+ e);
        }

    }

    public void editHour(Account account){
        try{
            List<String> tmp = searchLineToEdit(account);
            clearFile(fileUrl);
            rewriteFile(tmp);
        }
        catch(Exception e) {
            System.out.println("Excepcion reading file:"+ e);
        }
    }

    private boolean equals(String line, Account object) {
        String[] arr = line.split(",");
        if (arr[0].equals(object.getName()) && arr[1].equals(object.getPassword())){
            return true;
        }
        return false;
    }

    public void checkIfAcocuntAlreadyInSystem(String toadd) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(fileUrl)));
        String line;
        while((line = reader.readLine()) != null)
            if(line.equals(toadd)){
                reader.close();
            }
        reader.close();

    }

    public List<String>  searchLineToDelete(Account account, String toRemove) throws IOException {
        List<String> tmp = new ArrayList();
        BufferedReader file = new BufferedReader(new FileReader(new File(fileUrl)));
        String line;
        while ((line = file.readLine()) != null)
            if (line.equals(toRemove) == false) {
                tmp.add(line);
            }
        file.close();
        return tmp;
    }

    public List<String>  searchLineToEdit(Account account) throws IOException {
        List<String> tmp = new ArrayList();
        BufferedReader file = new BufferedReader(new FileReader(new File(fileUrl)));
        String line;
        while ((line = file.readLine()) != null)
            if (!equals(line, account)) {
                tmp.add(line);
            } else {
                String[] parts = line.split(",");
                line = parts[0] + "," + parts[1] + "," + parts[2] + "," + account.getScraphour();
                tmp.add(line);
            }
        file.close();
        return tmp;
    }



    public void clearFile(String fileUrl){
        try {

            FileWriter delete = new FileWriter(fileUrl, false);
            delete.write("");
            delete.close();

        }catch(Exception e){
        }
    }

    public void rewriteFile(List<String> tmp){
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(fileUrl, true));
            for (String correctLine : tmp) {
                writer.println(correctLine);
            }
            writer.close();
        }catch(Exception e){};
    }


}

