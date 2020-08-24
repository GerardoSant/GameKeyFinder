package Control;

import Model.Account;
import Model.Action.Action;
import Model.Action.CommunicableAction;
import Model.Actuator.Actuator;
import Model.Actuator.SMTPMailSenderActuator;
import Model.Communicator.Communicator;
import Model.Communicator.GameCommunicator;
import Model.Condition.ClockCondition;
import Model.Condition.Condition;
import Model.Condition.ScrapperCommunicableCondition;
import Model.Game.GameDesire;
import Model.Rule;
import Model.Sensor.ClockSensor;
import Model.Sensor.ScrapperSensor;
import Model.Sensor.Sensor;

import java.util.Arrays;
import java.util.List;

public class Session extends Thread {

    private List<GameDesire> gameDesireList;
    private int scrapHour;
    private String userEmail;
    private Account account;

    public Session(List<GameDesire> gameDesireList, int scrapHour, String userEmail, Account account) {
        this.gameDesireList = gameDesireList;
        this.scrapHour = scrapHour;
        this.userEmail = userEmail;
        this.account = account;
    }




    @Override
    public void run() {
        build();
        while (true){
            try {
                for (Rule rule :account.getRules()) {
                    System.out.println("Checking if the rule is active");
                    if (rule.isActive()) rule.execute();
                    System.out.println("Checking the countdown to scrap");
                    sleep(2*ClockCondition.millisecondsToHour);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void build() {
        Sensor scrapperSensor = new ScrapperSensor(gameDesireList);
        Sensor clockSensor = new ClockSensor();
        Actuator mailSenderActuator = new SMTPMailSenderActuator(userEmail);
        Rule mainRule = getRule(scrapperSensor, clockSensor, mailSenderActuator);
        account = new Account(account.getName(), account.getPassword(), account.getEmail(), account.getScraphour());
        initializeAccount(scrapperSensor, clockSensor, mailSenderActuator, mainRule);
    }

    private void initializeAccount(Sensor scrapperSensor, Sensor clockSensor, Actuator mailSenderActuator, Rule mainRule) {
        account.addRule(mainRule);
        account.addActuator(mailSenderActuator);
        account.addSensor(clockSensor);
        account.addSensor(scrapperSensor);
    }

    private Rule getRule(Sensor scrapperSensor, Sensor clockSensor, Actuator mailSenderActuator) {
        Communicator communicator = new GameCommunicator();
        List<Condition> conditionList = getConditionList(scrapperSensor, clockSensor, communicator);
        List<Action> actionList = getActionList(mailSenderActuator, communicator);
        return new Rule("CheapKeyFinder",true,conditionList,actionList);
    }

    private List<Action> getActionList(Actuator mailSenderActuator, Communicator communicator) {
        Action action = new CommunicableAction(null,mailSenderActuator,communicator);
        return Arrays.asList(action);
    }

    private List<Condition> getConditionList(Sensor scrapperSensor, Sensor clockSensor, Communicator communicator) {
        Condition scrapperCondition = new ScrapperCommunicableCondition(gameDesireList,scrapperSensor,communicator);
        Condition clockCondition = new ClockCondition(scrapHour,clockSensor);
        return Arrays.asList(clockCondition,scrapperCondition);
    }

    public Account getAccount() {
        return account;
    }


}
