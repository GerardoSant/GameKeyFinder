package Implementation.View.Persistence;

import Model.Account;
import Model.Game.GameDesire;
import Persistence.GameDesireListLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

public class FileGameDesireListLoader implements GameDesireListLoader {

    private Account account;

    public FileGameDesireListLoader(Account account) {
        this.account = account;
    }

    @Override
    public List<GameDesire> load() {
        try (Stream<String> stream = Files.lines(Paths.get("data/" + account.getName() + ".txt"))) {
            return stream.map(line-> {
                String[] arr = line.split(",");
                return new GameDesire(arr[0],parseInt(arr[1]));
            }).collect(Collectors.toList());
        } catch (IOException e) {
            File file = new File("data/" + account.getName() + ".txt");
            try{
                file.createNewFile();
            } catch (Exception ex){

            }
            return new ArrayList<GameDesire>();
        }
    }





}
