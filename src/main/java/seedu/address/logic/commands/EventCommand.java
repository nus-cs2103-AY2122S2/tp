package seedu.address.logic.commands;


import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";

    private final Index index;
    private final String name;
    private final String information;
    private final List<Person> persons;
    private final Date date;
    private final Time time;

    public EventCommand(Index index, String name, String information, List<Person> persons, Date date, Time time) {
        requireAllNonNull(index, name, information, persons, date, time);

        this.index = index;
        this.name = name;
        this.information = information;
        this.persons = persons;
        this.date = date;
        this.time = time;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EventCommand)) {
            return false;
        }

        EventCommand e = (EventCommand) other;
        return index.equals(e.index) && name.equals(e.name) && information.equals(e.information)
                && persons.equals(e.persons) && date.equals(date) && time.equals(time);
    }
}