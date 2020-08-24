package Control;

import Model.Account;
import Model.Game.GameDesire;

import java.util.List;


public class Control {

    private Account account;
    private List<GameDesire> gameDesireList;
    private String userEmail;
    private int scrapHour;
    private Thread thread;
    private boolean isRunning;


    public Control(Account account) {
        isRunning = true;
    }


    private void initIFTTT(){
        Account accountAux = new Account(account.getName(),account.getPassword(),account.getEmail(),account.getScraphour());
        System.out.println("Starting IFTTT for account " + accountAux.getName() + " with email " + userEmail + " with the folliwing options:");
        System.out.println("  -Scrap hour: " + scrapHour);
        System.out.println("  -Game list: ");
        for (GameDesire g : gameDesireList){
            System.out.println( "   -" +  g.getGameName());

        }
        thread= new Session(gameDesireList,scrapHour,userEmail,account);
        thread.start();
    }


    public void init(Account viewAccount, String userEmail, int scrapHour){
        this.account= viewAccount;
        this.userEmail=userEmail;
        this.scrapHour= scrapHour;
    }

    public void initGameDesireList(List<GameDesire> gameDesires){
        this.gameDesireList=gameDesires;
        initIFTTT();
    }

    public void setGameDesireList(List<GameDesire> gameDesireList) {
        this.gameDesireList = gameDesireList;
        System.out.println("New game list:");
        for (GameDesire g : gameDesireList){
            System.out.println(g.getGameName());
        }
        restart();
    }

    public void setScrapHour(int scrapHour) {
        this.scrapHour = scrapHour;
        System.out.println("New scrap hour: " + scrapHour);
        restart();
    }

    private void restart() {
        if (thread.isAlive())thread.stop();
        if(isRunning){
            thread = new Session(gameDesireList, scrapHour, userEmail, account);
            thread.start();
        }
    }

    public void activate(){
        System.out.println("Rule turned ON");
        isRunning = true;
        restart();
    }

    public void deactivate(){
        System.out.println("Rule turned OFF");
        isRunning = false;
        restart();
    }
}
