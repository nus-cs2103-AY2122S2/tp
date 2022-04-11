package unibook.logic.parser;

import org.junit.jupiter.api.Test;

import unibook.logic.commands.ListCommand;
import unibook.logic.commands.ListCommand.ListCommandType;
import unibook.logic.commands.ListCommand.ListView;

public class ListCommandParserTest {

    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void viewChange_returnsCorrectCommand() {
        CommandParserTestUtil.assertParseSuccess(parser, "list o/view v/groups",
                new ListCommand(ListView.GROUPS, ListCommandType.VIEW));
    }

    //Tests for parsing commands for "People view"
    @Test
    public void people_listSpecificType_returnsCorrectCommand() {
        CommandParserTestUtil.assertParseSuccess(parser, "list type/professors",
                new ListCommand("professors", ListCommandType.TYPE));
    }

    @Test
    public void people_listModuleAndCode_returnsCorrectCommand() {
        CommandParserTestUtil.assertParseSuccess(parser, "list o/module m/CS2103",
                new ListCommand("CS2103", ListCommandType.MODULE));
    }

    @Test
    public void people_listModuleAndCodeAndType_returnsCorrectCommand() {
        CommandParserTestUtil.assertParseSuccess(parser, "list o/module m/CS2103 type/students",
                new ListCommand("CS2103", "students", ListCommandType.MODULEANDTYPE));
    }

    @Test
    public void people_listGroupAndModuleCodeAndGroupName_returnsCorrectCommand() {
        CommandParserTestUtil.assertParseSuccess(parser, "list o/group m/CS2103 g/W16-1",
                new ListCommand("w16-1", "CS2103", ListCommandType.PEOPLEINGROUP));
    }

    //Tests for parsing commands for "Modules" view
    @Test
    public void modules_moduleCode_returnsCorrectCommand() {
        CommandParserTestUtil.assertParseSuccess(parser, "list m/CS2103",
                new ListCommand("CS2103", ListCommandType.MODULE));
    }

    @Test
    public void modules_name_returnsCorrectCommand() {
        CommandParserTestUtil.assertParseSuccess(parser, "list n/Software",
                new ListCommand("Software", ListCommandType.MODULEWITHNAMEMATCH));
    }

    @Test
    public void modules_keyEvent_returnsCorrectCommand() {
        CommandParserTestUtil.assertParseSuccess(parser, "list ke/EXAM",
                new ListCommand("EXAM", ListCommandType.MODULESWITHKEYEVENT));
    }

    @Test
    public void modules_date_returnsCorrectCommand() {
        CommandParserTestUtil.assertParseSuccess(parser, "list dt/2022-05-04",
                new ListCommand("2022-05-04", ListCommandType.MODULESWITHEVENTDATE));
    }

    @Test
    public void modules_dateAndKeyEvent_returnsCorrectCommand() {
        CommandParserTestUtil.assertParseSuccess(parser, "list dt/2022-05-04 ke/ASSIGNMENT_DUE",
                new ListCommand("2022-05-04", "ASSIGNMENT_DUE", ListCommandType.MODULESWITHDATEANDKEYEVENT));
    }

    @Test
    public void modules_dateAndName_returnsCorrectCommand() {
        CommandParserTestUtil.assertParseSuccess(parser, "list dt/2022-05-04 n/Network",
                new ListCommand("2022-05-04", "Network", ListCommandType.MODULESWITHDATEANDNAME));
    }

    @Test
    public void modules_nameAndKeyEvent_returnsCorrectCommand() {
        CommandParserTestUtil.assertParseSuccess(parser, "list n/Network ke/ASSIGNMENT_DUE",
                new ListCommand("Network", "ASSIGNMENT_DUE", ListCommandType.MODULESWITHNAMEANDKEYEVENT));
    }

    @Test
    public void modules_nameAndKeyEventAndDate_returnsCorrectCommand() {
        CommandParserTestUtil.assertParseSuccess(parser, "list n/Network ke/ASSIGNMENT_DUE dt/2022-05-04",
                new ListCommand("Network", "2022-05-04", "ASSIGNMENT_DUE",
                        ListCommandType.MODULESWITHDATEANDKEYEVENTANDNAME));
    }

    @Test
    public void modules_groupAndGroupName_returnsCorrectCommand() {
        CommandParserTestUtil.assertParseSuccess(parser, "list o/group g/w16-1",
                new ListCommand("w16-1", ListCommandType.GROUPFROMMODULEVIEW));
    }

    //Tests for parsing commands for "Groups" view
    @Test
    public void groups_groupName_returnsCorrectCommand() {
        CommandParserTestUtil.assertParseSuccess(parser, "list g/w16-1",
                new ListCommand("w16-1", ListCommandType.GROUPFROMGROUPVIEW));
    }

    @Test
    public void groups_groupNameAndModuleCode_returnsCorrectCommand() {
        CommandParserTestUtil.assertParseSuccess(parser, "list g/w16-1 m/CS2107",
                new ListCommand("w16-1", "CS2107", ListCommandType.SPECIFICGROUPFROMGROUPVIEW));
    }

    @Test
    public void groups_meeting_returnsCorrectCommand() {
        CommandParserTestUtil.assertParseSuccess(parser, "list mt/2022-04-03",
                new ListCommand("2022-04-03", ListCommandType.GROUPWITHMEETINGDATE));
    }

}
