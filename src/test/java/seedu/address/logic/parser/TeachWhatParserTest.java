package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddLessonCommand;
import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.logic.commands.AssignCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteLessonCommand;
import seedu.address.logic.commands.DeleteStudentCommand;
import seedu.address.logic.commands.EditLessonCommand;
import seedu.address.logic.commands.EditStudentCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindLessonCommand;
import seedu.address.logic.commands.FindStudentCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListLessonsCommand;
import seedu.address.logic.commands.ListStudentsCommand;
import seedu.address.logic.commands.UnassignCommand;
import seedu.address.logic.commands.ViewLessonInfoCommand;
import seedu.address.logic.commands.ViewStudentInfoCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class TeachWhatParserTest {
    private TeachWhatParser teachWhatParser;

    @BeforeEach
    public void setUp() {
        teachWhatParser = new TeachWhatParser();
    }

    @Test
    public void testParse_nullString() {
        assertThrows(NullPointerException.class, () -> teachWhatParser.parseCommand(null));
    }

    @Test
    public void testParse_invalidInput() {
        String invalidInput = "rubbishsdaisdjoiwadjsidwad";
        assertThrows(ParseException.class, () -> teachWhatParser.parseCommand(invalidInput));
    }

    @Test
    public void testParse_command_addStudent() throws ParseException {
        String input = AddStudentCommand.COMMAND_WORD + " -n James -p 999 -e jamesboyo@gmail.com "
                + "-a 34 Lor 11 Geylang -t hardworking -t small";
        String shortenedInput = AddStudentCommand.SHORTENED_COMMAND_WORD + " -n James -p 999 -e jamesboyo@gmail.com"
                + " -a 34 Lor 11 Geylang -t hardworking -t small";
        Command resultCommand = teachWhatParser.parseCommand(input);
        assertInstanceOf(AddStudentCommand.class, resultCommand);
        Command resultCommand2 = teachWhatParser.parseCommand(shortenedInput);
        assertInstanceOf(AddStudentCommand.class, resultCommand2);
    }

    @Test
    public void testParse_command_deleteStudent() throws ParseException {
        String input = DeleteStudentCommand.COMMAND_WORD + " 1";
        String shortenedInput = DeleteStudentCommand.SHORTENED_COMMAND_WORD + " 1";
        Command resultCommand = teachWhatParser.parseCommand(input);
        assertInstanceOf(DeleteStudentCommand.class, resultCommand);
        Command resultCommand2 = teachWhatParser.parseCommand(shortenedInput);
        assertInstanceOf(DeleteStudentCommand.class, resultCommand2);
    }

    @Test
    public void testParse_command_addLesson() throws ParseException {
        String input = AddLessonCommand.COMMAND_WORD + " -r -n Biology group 1 -s Biology"
                + " -a Blk 11 Ang Mo Kio Street 74, #11-04 -d 27-02-2022 -t 18:00 -h 1 -m 50";
        String input2 = AddLessonCommand.SHORTENED_COMMAND_WORD + " -r -n Biology group 1 -s Biology "
                + "-a Blk 11 Ang Mo Kio Street 74, #11-04 -d 27-02-2022 -t 18:00 -h 1 -m 50";
        Command resultCommand = teachWhatParser.parseCommand(input);
        assertInstanceOf(AddLessonCommand.class, resultCommand);
        Command resultCommand2 = teachWhatParser.parseCommand(input2);
        assertInstanceOf(AddLessonCommand.class, resultCommand2);
    }

    @Test
    public void testParse_command_deleteLesson() throws ParseException {
        String input = DeleteLessonCommand.COMMAND_WORD + " 1";
        String input2 = DeleteLessonCommand.SHORTENED_COMMAND_WORD + " 1";
        Command resultCommand = teachWhatParser.parseCommand(input);
        assertInstanceOf(DeleteLessonCommand.class, resultCommand);
        Command resultCommand2 = teachWhatParser.parseCommand(input2);
        assertInstanceOf(DeleteLessonCommand.class, resultCommand2);
    }

    @Test
    public void testParse_command_editStudent() throws ParseException {
        String input = EditStudentCommand.COMMAND_WORD + " 2 -n Sammy -p 123 -t codinggod -t extrageeky -t extrahansum";
        String input2 = EditStudentCommand.SHORTENED_COMMAND_WORD + " 2 -n Sammy -p 123 -t codinggod "
                + "-t extrageeky -t extrahansum";
        Command resultCommand = teachWhatParser.parseCommand(input);
        assertInstanceOf(EditStudentCommand.class, resultCommand);
        Command resultCommand2 = teachWhatParser.parseCommand(input2);
        assertInstanceOf(EditStudentCommand.class, resultCommand2);
    }

    @Test
    public void testParse_command_editLesson() throws ParseException {
        String input = EditLessonCommand.COMMAND_WORD + " 2 -n Bio Make Up Session -t 17:00 -h 2";
        String input2 = EditLessonCommand.SHORTENED_COMMAND_WORD + " 2 -n Bio Make Up Session -t 17:00 -h 2";
        Command resultCommand = teachWhatParser.parseCommand(input);
        assertInstanceOf(EditLessonCommand.class, resultCommand);
        Command resultCommand2 = teachWhatParser.parseCommand(input2);
        assertInstanceOf(EditLessonCommand.class, resultCommand2);
    }

    @Test
    public void testParse_command_findStudent() throws ParseException {
        String input = FindStudentCommand.COMMAND_WORD + " test";
        String input2 = FindStudentCommand.SHORTENED_COMMAND_WORD + " test";
        Command resultCommand = teachWhatParser.parseCommand(input);
        assertInstanceOf(FindStudentCommand.class, resultCommand);
        Command resultCommand2 = teachWhatParser.parseCommand(input2);
        assertInstanceOf(FindStudentCommand.class, resultCommand2);
    }

    @Test
    public void testParse_command_findLesson() throws ParseException {
        String input = FindLessonCommand.COMMAND_WORD + " test";
        String input2 = FindLessonCommand.SHORTENED_COMMAND_WORD + " test";
        Command resultComamnd = teachWhatParser.parseCommand(input);
        assertInstanceOf(FindLessonCommand.class, resultComamnd);
        Command resultCommand2 = teachWhatParser.parseCommand(input2);
        assertInstanceOf(FindLessonCommand.class, resultCommand2);
    }

    @Test
    public void testParse_command_listStudents() throws ParseException {
        String input = ListStudentsCommand.COMMAND_WORD;
        String input2 = ListStudentsCommand.SHORTENED_COMMAND_WORD;
        Command resultCommand = teachWhatParser.parseCommand(input);
        assertInstanceOf(ListStudentsCommand.class, resultCommand);
        Command resultCommand2 = teachWhatParser.parseCommand(input2);
        assertInstanceOf(ListStudentsCommand.class, resultCommand2);
    }

    @Test
    public void testParse_command_listLessons() throws ParseException {
        Command resultCommand = teachWhatParser.parseCommand(ListLessonsCommand.COMMAND_WORD);
        assertInstanceOf(ListLessonsCommand.class, resultCommand);
        Command resultCommand2 = teachWhatParser.parseCommand(ListLessonsCommand.SHORTENED_COMMAND_WORD);
        assertInstanceOf(ListLessonsCommand.class, resultCommand2);
    }

    @Test
    public void testParse_command_viewStudentInfo() throws ParseException {
        String input = ViewStudentInfoCommand.COMMAND_WORD + " 1";
        Command resultCommand = teachWhatParser.parseCommand(input);
        assertInstanceOf(ViewStudentInfoCommand.class, resultCommand);
    }

    @Test
    public void testParse_command_viewLessonInfo() throws ParseException {
        String input = ViewLessonInfoCommand.COMMAND_WORD + " 1";
        Command resultCommand = teachWhatParser.parseCommand(input);
        assertInstanceOf(ViewLessonInfoCommand.class, resultCommand);
    }

    @Test
    public void testParse_command_assign() throws ParseException {
        String input = AssignCommand.COMMAND_WORD + " -s 1 -l 1";
        Command resultCommand = teachWhatParser.parseCommand(input);
        assertInstanceOf(AssignCommand.class, resultCommand);
    }

    @Test
    public void testParse_command_unassign() throws ParseException {
        String input = UnassignCommand.COMMAND_WORD + " -s 1 -l 1";
        Command resultCommand = teachWhatParser.parseCommand(input);
        assertInstanceOf(UnassignCommand.class, resultCommand);
    }

    @Test
    public void testParse_command_help() throws ParseException {
        Command resultCommand = teachWhatParser.parseCommand(HelpCommand.COMMAND_WORD);
        assertInstanceOf(HelpCommand.class, resultCommand);
    }

    @Test
    public void testParse_command_clear() throws ParseException {
        String input = ClearCommand.COMMAND_WORD + " -f";
        Command resultCommand = teachWhatParser.parseCommand(input);
        assertInstanceOf(ClearCommand.class, resultCommand);
    }

    @Test
    public void testParse_command_exit() throws ParseException {
        Command resultCommand = teachWhatParser.parseCommand(ExitCommand.COMMAND_WORD);
        assertInstanceOf(ExitCommand.class, resultCommand);
    }
}
