package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CCA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDUCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RemoveTagCommand;
import seedu.address.model.person.Cca;
import seedu.address.model.person.Education;
import seedu.address.model.person.Internship;
import seedu.address.model.person.Module;
import seedu.address.model.tag.Tag;

class RemoveTagCommandParserTest {
    private final RemoveTagCommandParser parser = new RemoveTagCommandParser();
    private final String nonEmptyEducation = "Some education";
    private final String nonEmptyModule = "Some module";
    private final String nonEmptyInternship = "Some internship";
    private final String nonEmptyCca = "Some cca";

    @Test
    public void parse_indexSpecifiedOnePrefix_success() {
        Set<Tag> emptySet = new HashSet<>();
        ArrayList<Tag> emptyList = new ArrayList<>(emptySet);

        Set<Tag> eduSet = new HashSet<>();
        eduSet.add(new Education(nonEmptyEducation.trim().toLowerCase()));
        ArrayList<Tag> eduList = new ArrayList<>(eduSet);

        Set<Tag> internSet = new HashSet<>();
        internSet.add(new Internship(nonEmptyInternship.trim().toLowerCase()));
        ArrayList<Tag> internList = new ArrayList<>(internSet);

        Set<Tag> modSet = new HashSet<>();
        modSet.add(new Module(nonEmptyModule.trim().toLowerCase()));
        ArrayList<Tag> moduleList = new ArrayList<>(modSet);

        Set<Tag> ccaSet = new HashSet<>();
        ccaSet.add(new Cca(nonEmptyCca.trim().toLowerCase()));
        ArrayList<Tag> ccaList = new ArrayList<>(ccaSet);

        RemoveTagCommand expectedCommandEduOnly = new RemoveTagCommand(INDEX_FIRST_PERSON, eduList,
                emptyList, emptyList, emptyList);

        RemoveTagCommand expectedCommandInternOnly = new RemoveTagCommand(INDEX_FIRST_PERSON, emptyList,
                internList, emptyList, emptyList);

        RemoveTagCommand expectedCommandModuleOnly = new RemoveTagCommand(INDEX_FIRST_PERSON, emptyList,
                emptyList, moduleList, emptyList);

        RemoveTagCommand expectedCommandCcaOnly = new RemoveTagCommand(INDEX_FIRST_PERSON, emptyList,
                emptyList, emptyList, ccaList);

        String userInputEduOnly = INDEX_FIRST_PERSON.getOneBased() + " "
                + PREFIX_EDUCATION + nonEmptyEducation;

        String userInputInternOnly = INDEX_FIRST_PERSON.getOneBased() + " "
                + PREFIX_INTERNSHIP + nonEmptyInternship;

        String userInputModOnly = INDEX_FIRST_PERSON.getOneBased() + " "
                + PREFIX_MODULE + nonEmptyModule;

        String userInputCcaOnly = INDEX_FIRST_PERSON.getOneBased() + " "
                + PREFIX_CCA + nonEmptyCca;

        assertParseSuccess(parser, userInputEduOnly, expectedCommandEduOnly);
        assertParseSuccess(parser, userInputInternOnly, expectedCommandInternOnly);
        assertParseSuccess(parser, userInputModOnly, expectedCommandModuleOnly);
        assertParseSuccess(parser, userInputCcaOnly, expectedCommandCcaOnly);
    }

    @Test
    public void parse_indexSpecifiedMultiplePrefix_success() {
        Set<Tag> emptySet = new HashSet<>();
        ArrayList<Tag> emptyList = new ArrayList<>(emptySet);

        Set<Tag> eduSet = new HashSet<>();
        eduSet.add(new Education(nonEmptyEducation.trim().toLowerCase()));
        ArrayList<Tag> eduList = new ArrayList<>(eduSet);

        Set<Tag> internSet = new HashSet<>();
        internSet.add(new Internship(nonEmptyInternship.trim().toLowerCase()));
        ArrayList<Tag> internList = new ArrayList<>(internSet);

        Set<Tag> modSet = new HashSet<>();
        modSet.add(new Module(nonEmptyModule.trim().toLowerCase()));
        ArrayList<Tag> moduleList = new ArrayList<>(modSet);

        Set<Tag> ccaSet = new HashSet<>();
        ccaSet.add(new Cca(nonEmptyCca.trim().toLowerCase()));
        ArrayList<Tag> ccaList = new ArrayList<>(ccaSet);

        RemoveTagCommand expectedCommandAll = new RemoveTagCommand(INDEX_FIRST_PERSON, eduList,
                internList, moduleList, ccaList);

        RemoveTagCommand expectedCommandTwoPrefix = new RemoveTagCommand(INDEX_FIRST_PERSON, eduList,
                emptyList, moduleList, emptyList);

        RemoveTagCommand expectedCommandThreePrefix = new RemoveTagCommand(INDEX_FIRST_PERSON, eduList,
                emptyList, moduleList, ccaList);

        //Order of prefixes in user input should not matter
        String userInputAll = INDEX_FIRST_PERSON.getOneBased() + " "
                + PREFIX_EDUCATION + nonEmptyEducation + " "
                + PREFIX_MODULE + nonEmptyModule + " "
                + PREFIX_CCA + nonEmptyCca + " "
                + PREFIX_INTERNSHIP + nonEmptyInternship;

        String userInputTwoPrefix = INDEX_FIRST_PERSON.getOneBased() + " "
                + PREFIX_EDUCATION + nonEmptyEducation + " "
                + PREFIX_MODULE + nonEmptyModule;

        String userInputThreePrefix = INDEX_FIRST_PERSON.getOneBased() + " "
                + PREFIX_CCA + nonEmptyCca + " "
                + PREFIX_EDUCATION + nonEmptyEducation + " "
                + PREFIX_MODULE + nonEmptyModule;

        assertParseSuccess(parser, userInputAll, expectedCommandAll);
        assertParseSuccess(parser, userInputTwoPrefix, expectedCommandTwoPrefix);
        assertParseSuccess(parser, userInputThreePrefix, expectedCommandThreePrefix);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveTagCommand.MESSAGE_USAGE);

        // no prefix
        assertParseFailure(parser, RemoveTagCommand.COMMAND_WORD, expectedMessage);
        assertParseFailure(parser, RemoveTagCommand.COMMAND_WORD + " 1", expectedMessage);

        // no input after prefix
        assertParseFailure(parser, RemoveTagCommand.COMMAND_WORD + " 1 " + PREFIX_EDUCATION, expectedMessage);
        assertParseFailure(parser, RemoveTagCommand.COMMAND_WORD + " 1 " + PREFIX_MODULE, expectedMessage);
        assertParseFailure(parser, RemoveTagCommand.COMMAND_WORD + " 1 " + PREFIX_INTERNSHIP, expectedMessage);
        assertParseFailure(parser, RemoveTagCommand.COMMAND_WORD + " 1 " + PREFIX_CCA, expectedMessage);

        // no index
        assertParseFailure(parser, RemoveTagCommand.COMMAND_WORD + " " + nonEmptyEducation, expectedMessage);
    }
}
