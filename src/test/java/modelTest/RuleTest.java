package modelTest;

import Model.Action.Action;
import Model.Actuator.Actuator;
import Model.Condition.ClockCondition;
import Model.Condition.Condition;
import Model.Condition.ScrapperCommunicableCondition;
import Model.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RuleTest {

    @Test
    public void isTrueAllConditions_false_when_one_condition_are_false(){
        /*Configure DOC*/
        //Mock
        ClockCondition clockConditionMock = mock(ClockCondition.class);
        ScrapperCommunicableCondition scrapperCommunicableConditionMock = mock(ScrapperCommunicableCondition.class);

        //Stub
        when(clockConditionMock.check()).thenReturn(true);
        when(scrapperCommunicableConditionMock.check()).thenReturn(false);
        //Other docs
        List<Condition> conditionsDoc = new ArrayList<>();
        conditionsDoc.add(clockConditionMock);
        conditionsDoc.add(scrapperCommunicableConditionMock);

        /*Configure SUT*/
        Rule rule = new Rule("IFTTT",true,conditionsDoc, null);

        /*Exec*/
        boolean isTrueAllConditionsResult= rule.isTrueAllConditions();

        /*Verify*/
        assertThat(isTrueAllConditionsResult).isFalse();
        /*Finalize*/
    }

    @Test
    public void isTrueAllConditions_false_when_both_condition_are_false(){
        /*Configure DOC*/
        //Mock
        ClockCondition clockConditionMock = mock(ClockCondition.class);
        ScrapperCommunicableCondition scrapperCommunicableConditionMock = mock(ScrapperCommunicableCondition.class);

        //Stub
        when(clockConditionMock.check()).thenReturn(false);
        when(scrapperCommunicableConditionMock.check()).thenReturn(false);
        //Other docs
        List<Condition> conditionsDoc = new ArrayList<>();
        conditionsDoc.add(clockConditionMock);
        conditionsDoc.add(scrapperCommunicableConditionMock);

        /*Configure SUT*/
        Rule rule = new Rule("IFTTT",true,conditionsDoc, null);

        /*Exec*/
        boolean isTrueAllConditionsResult= rule.isTrueAllConditions();

        /*Verify*/
        assertThat(isTrueAllConditionsResult).isFalse();
        /*Finalize*/
    }

    @Test
    public void isTrueAllConditions_true_when_all_conditions_are_true(){
        /*Configure DOC*/
        //Mock
        ClockCondition clockConditionMock = mock(ClockCondition.class);
        ScrapperCommunicableCondition scrapperCommunicableConditionMock = mock(ScrapperCommunicableCondition.class);

        //Stub
        when(clockConditionMock.check()).thenReturn(true);
        when(scrapperCommunicableConditionMock.check()).thenReturn(true);
        //Other docs
        List<Condition> conditionsDoc = new ArrayList<>();
        conditionsDoc.add(clockConditionMock);
        conditionsDoc.add(scrapperCommunicableConditionMock);

        /*Configure SUT*/
        Rule rule = new Rule("IFTTT",true,conditionsDoc,null);

        /*Exec*/
        boolean isTrueAllConditionsResult= rule.isTrueAllConditions();


        /*Verify*/
        assertThat(isTrueAllConditionsResult).isTrue();

        /*Finalize*/
    }
    @Test
    public void execute_call_actions_when_all_conditions_are_true(){
        /*Configure DOC*/
        //Mock
        ClockCondition clockConditionMock = mock(ClockCondition.class);
        ScrapperCommunicableCondition scrapperCommunicableConditionMock = mock(ScrapperCommunicableCondition.class);

        //Stub
        when(clockConditionMock.check()).thenReturn(true);
        when(scrapperCommunicableConditionMock.check()).thenReturn(true);
        //Other docs
        List<Condition> conditionsDoc = new ArrayList<>();
        final Boolean[] actionCalled = {false};
        List<Action> actionList = new ArrayList<>();
        actionList.add(new Action(10, new Actuator() {
            @Override
            public void actuate(Object object) {
                actionCalled[0] =true;
            }
        }));
        conditionsDoc.add(clockConditionMock);
        conditionsDoc.add(scrapperCommunicableConditionMock);

        /*Configure SUT*/
        Rule rule = new Rule("IFTTT",true,conditionsDoc, actionList);

        /*Exec*/
        rule.execute();

        /*Verify*/
        assertThat(actionCalled[0]).isTrue();

        /*Finalize*/
    }

    @Test
    public void execute_dont_call_actions_when_not_all_conditions_are_true(){
        /*Configure DOC*/
        //Mock
        ClockCondition clockConditionMock = mock(ClockCondition.class);
        ScrapperCommunicableCondition scrapperCommunicableConditionMock = mock(ScrapperCommunicableCondition.class);

        //Stub
        when(clockConditionMock.check()).thenReturn(true);
        when(scrapperCommunicableConditionMock.check()).thenReturn(false);
        //Other docs
        List<Condition> conditionsDoc = new ArrayList<>();
        final Boolean[] actionCalled = {false};
        List<Action> actionList = new ArrayList<>();
        actionList.add(new Action(10, new Actuator() {
            @Override
            public void actuate(Object object) {
                actionCalled[0] =true;
            }
        }));
        conditionsDoc.add(clockConditionMock);
        conditionsDoc.add(scrapperCommunicableConditionMock);

        /*Configure SUT*/
        Rule rule = new Rule("IFTTT",true,conditionsDoc, actionList);

        /*Exec*/
        rule.execute();

        /*Verify*/
        assertThat(actionCalled[0]).isFalse();

        /*Finalize*/
    }



}
