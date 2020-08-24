package Implementation.View.Persistence;

import Model.Account;
import Model.Game.GameDesire;
import Persistence.GameDesireListWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileGameDesireListWriter implements GameDesireListWriter {

    private Account account;

    public FileGameDesireListWriter(Account account) {
        this.account = account;
    }


    @Override
    public void add(GameDesire object) {
        if(object.getDesiredPrice() != 0 && object.getGameName()!=null) {
            try (PrintWriter output = new PrintWriter(new FileWriter("data/" + account.getName() + ".txt", true))) {
                output.printf("%s\r\n", object.getGameName() + "," + object.getDesiredPrice());

            } catch (Exception e) {
            }
        }else{
            throw new NumberFormatException();
        }
    }

    @Override
    public void remove(GameDesire object) {
        try {
            Stream<String> stream = Files.lines(Paths.get("data/" + account.getName() + ".txt"));
            ArrayList<String> list= (ArrayList<String>) stream.collect(Collectors.toList());
            String line = object.getGameName() +","+ object.getDesiredPrice();
            int index = list.indexOf(line);
            if (index!=-1){
                list.remove(index);
                Files.write(Paths.get("data/" + account.getName() + ".txt"), list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean equals(String line, GameDesire object) {
        String[] arr = line.split(",");
        if (arr[0].equals(object.getGameName()) && arr[1].equals(object.getDesiredPrice())){
            return true;
        }
        return false;
    }
}
