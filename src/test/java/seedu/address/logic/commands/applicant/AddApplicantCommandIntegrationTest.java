package seedu.address.logic.commands.applicant;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalHireLah.getTypicalHireLah;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.applicant.Applicant;
import seedu.address.testutil.ApplicantBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddApplicantCommand}.
 */
public class AddApplicantCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalHireLah(), new UserPrefs());
    }


    @Test
    public void execute_newApplicant_success() {
        Applicant validApplicant = new ApplicantBuilder().build();

        Model expectedModel = new ModelManager(model.getHireLah(), new UserPrefs());
        expectedModel.addApplicant(validApplicant);

        assertCommandSuccess(new AddApplicantCommand(validApplicant), model,
                String.format(AddApplicantCommand.MESSAGE_SUCCESS, validApplicant), expectedModel);
    }

    @Test
    public void execute_duplicateApplicant_throwsCommandException() {
        Applicant applicantInList = model.getHireLah().getApplicantList().get(0);
        assertCommandFailure(new AddApplicantCommand(applicantInList), model,
                AddApplicantCommand.MESSAGE_DUPLICATE_APPLICANT);
    }

}
