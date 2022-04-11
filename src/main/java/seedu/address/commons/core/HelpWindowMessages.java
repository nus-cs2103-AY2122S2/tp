package seedu.address.commons.core;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import seedu.address.logic.commands.AddAssessmentCommand;
import seedu.address.logic.commands.AddClassCommand;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteAssessmentCommand;
import seedu.address.logic.commands.DeleteClassCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.GradeCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListAssessmentCommand;
import seedu.address.logic.commands.ListAttendanceCommand;
import seedu.address.logic.commands.ListClassCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListScoreCommand;
import seedu.address.logic.commands.ListStudentCommand;
import seedu.address.logic.commands.MarkAttendanceCommand;
import seedu.address.logic.commands.RemoveCommentCommand;
import seedu.address.logic.commands.RemoveStudentCommand;
import seedu.address.logic.commands.UnmarkAttendanceCommand;
import seedu.address.logic.commands.ViewCommentCommand;

public class HelpWindowMessages {
    private static final String defaultFont = "System";

    private static final String headerFont = "System";
    private static final FontWeight headerFontWeight = FontWeight.BOLD;
    private static final int headerFontSize = 40;

    private static final String introFont = defaultFont;
    private static final int introFontSize = 25;

    private static Text createSectionHeader(String header) {
        Text text = new Text(header);
        text.setFont(Font.font(headerFont, headerFontWeight, headerFontSize));
        text.setUnderline(true);
        text.setFill(Color.WHITE);
        return text;
    }

    public static ArrayList<String> getHelpCommandDisplayList() {
        ArrayList<String> list = new ArrayList<>();
        list.add(AddCommand.COMMAND_WORD);
        list.add(AddCommand.MESSAGE_USAGE);
        list.add(AddStudentCommand.COMMAND_WORD);
        list.add(AddStudentCommand.MESSAGE_USAGE);
        list.add(AddClassCommand.COMMAND_WORD);
        list.add(AddClassCommand.MESSAGE_USAGE);
        list.add(AddAssessmentCommand.COMMAND_WORD);
        list.add(AddAssessmentCommand.MESSAGE_USAGE);
        list.add(DeleteCommand.COMMAND_WORD);
        list.add(DeleteCommand.MESSAGE_USAGE);
        list.add(DeleteClassCommand.COMMAND_WORD);
        list.add(DeleteClassCommand.MESSAGE_USAGE);
        list.add(DeleteAssessmentCommand.COMMAND_WORD);
        list.add(DeleteAssessmentCommand.MESSAGE_USAGE);
        list.add(EditCommand.COMMAND_WORD);
        list.add(EditCommand.MESSAGE_USAGE);
        list.add(GradeCommand.COMMAND_WORD);
        list.add(GradeCommand.MESSAGE_USAGE);
        list.add(ListCommand.COMMAND_WORD);
        list.add(ListCommand.MESSAGE_USAGE);
        list.add(ListClassCommand.COMMAND_WORD);
        list.add(ListClassCommand.MESSAGE_USAGE);
        list.add(ListStudentCommand.COMMAND_WORD);
        list.add(ListStudentCommand.MESSAGE_USAGE);
        list.add(ListAssessmentCommand.COMMAND_WORD);
        list.add("list_assessment: Lists all the assessments in the application");
        list.add(ListAttendanceCommand.COMMAND_WORD);
        list.add(ListAttendanceCommand.MESSAGE_USAGE);
        list.add(ListScoreCommand.COMMAND_WORD);
        list.add(ListScoreCommand.MESSAGE_USAGE);
        list.add(ListStudentCommand.COMMAND_WORD);
        list.add(ListStudentCommand.MESSAGE_USAGE);
        list.add(MarkAttendanceCommand.COMMAND_WORD);
        list.add(MarkAttendanceCommand.MESSAGE_USAGE);
        list.add(UnmarkAttendanceCommand.COMMAND_WORD);
        list.add(UnmarkAttendanceCommand.MESSAGE_USAGE);
        list.add(ViewCommentCommand.COMMAND_WORD);
        list.add(ViewCommentCommand.MESSAGE_USAGE);
        list.add(RemoveCommentCommand.COMMAND_WORD);
        list.add(RemoveCommentCommand.MESSAGE_USAGE);
        list.add(RemoveStudentCommand.COMMAND_WORD);
        list.add(RemoveStudentCommand.MESSAGE_USAGE);
        list.add(FindCommand.COMMAND_WORD);
        list.add(FindCommand.MESSAGE_USAGE);
        list.add(HelpCommand.COMMAND_WORD);
        list.add(HelpCommand.MESSAGE_USAGE);
        list.add(ClearCommand.COMMAND_WORD);
        list.add("clear: Clears all data in the app");
        list.add(ExitCommand.COMMAND_WORD);
        list.add("exit: Closes the application");
        return list;
    }

    /**
     * Method for creating the introduction text for the Help Window.
     * @return A TextFlow object containing the introduction text.
     */
    public static TextFlow createIntroText() {
        Text[] textArray = {
                createSectionHeader("Introduction\n\n"),
                createNormalIntroText("camNUS is a "),
                createBoldIntroText("desktop app "),
                createNormalIntroText("for Teaching Assistants (TAs) to "),
                createBoldIntroText("manage their own contacts, as well as assessments "
                        + "and class participation among students "),
                createNormalIntroText("in their contact book. It is optimized for use via a Command "
                        + "Line Interface (CLI) while still having the benefits of a Graphical "
                        + "User Interface (GUI).")
        };
        return new TextFlow(textArray);
    }

    /**
     * Method for creating the developer guide text for the Help Window.
     * @return A TextFlow object containing the developer guide text.
     */
    public static TextFlow createDevGuideText() {
        Text header = createSectionHeader("Developer Guide\n\n");
        Text text = createNormalIntroText("Refer to the developer guide here:\n");
        Hyperlink hyperlink = new Hyperlink(
                "https://github.com/AY2122S2-CS2103T-W13-2/tp/blob/master/docs/DeveloperGuide.md");
        hyperlink.setFont(Font.font(introFont, FontWeight.NORMAL, introFontSize));
        hyperlink.setWrapText(true);
        AnchorPane.setLeftAnchor(hyperlink, 0.0);
        AnchorPane.setRightAnchor(hyperlink, 15.0);
        TextFlow textFlow = new TextFlow(header, text, hyperlink);
        hyperlink.prefWidthProperty().bind(textFlow.widthProperty());
        prepareHyperlink(hyperlink);
        return textFlow;
    }

    private static Text createBoldIntroText(String message) {
        Text text = new Text(message);
        text.setFont(Font.font(introFont, FontWeight.BOLD, introFontSize));
        text.setFill(Color.WHITE);
        return text;
    }

    private static Text createNormalIntroText(String message) {
        Text text = new Text(message);
        text.setFont(Font.font(introFont, FontWeight.NORMAL, introFontSize));
        text.setFill(Color.WHITE);
        return text;
    }

    private static void prepareHyperlink(Hyperlink hyperlink) {
        hyperlink.setBorder(Border.EMPTY);
        hyperlink.setStyle("-fx-text-fill: #e06519;");
        hyperlink.setUnderline(true);
        hyperlink.setOnAction(e -> {
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().browse(new URI(hyperlink.getText()));
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
