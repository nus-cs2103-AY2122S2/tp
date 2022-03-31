package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS_WITH_LINEUP;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SCHEDULES;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Person;

public class ViewCommandTest {
    private Model model = new ModelManager();

    @Test
    public void execute_viewSchedule_success() throws CommandException {
        ViewCommand viewCommand = new ViewCommand(null, PREDICATE_SHOW_ALL_SCHEDULES, Collections.singletonList("S/"));
        String res = viewCommand.execute(model).getFeedbackToUser();
        String expectedMessage = String.format(
                Messages.MESSAGE_SCHEDULE_LISTED_OVERVIEW, model.getFilteredScheduleList().size());
        assertEquals(res, expectedMessage);
    }

    @Test
    public void execute_viewLineup_success() throws CommandException {
        List<String> list = new ArrayList<>();
        list.add("L/");
        list.add("P/");
        List<Predicate<Person>> list2 = new ArrayList<>();
        list2.add(PREDICATE_SHOW_ALL_PERSONS_WITH_LINEUP);
        ViewCommand viewCommand = new ViewCommand(list2, null, list);
        String res = viewCommand.execute(model).getFeedbackToUser();
        String expectedMessage = String.format(
                Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size());
        assertEquals(res, expectedMessage);
    }
}
