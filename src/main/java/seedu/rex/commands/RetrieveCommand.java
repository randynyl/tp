package seedu.rex.commands;

import seedu.rex.Rex;
import seedu.rex.data.PatientList;
import seedu.rex.data.exception.RexException;
import seedu.rex.data.hospital.Appointment;
import seedu.rex.storage.Storage;
import seedu.rex.ui.Ui;

import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Retrieves patient details.
 */
public class RetrieveCommand extends Command {

    public static final String COMMAND_WORD = "retrieve";
    private final String trimmedCommand;

    public RetrieveCommand(String trimmedCommand) {
        this.trimmedCommand = trimmedCommand;
    }

    /**
     * Retrieves patient from patient list using details inputted by the user.
     *
     * @param patients     PatientList object.
     * @param appointments ArrayList of appointment.
     * @param ui           Ui object.
     * @param storage      Storage object.
     * @throws RexException If there is issue executing command.
     */
    @Override
    public void execute(PatientList patients, ArrayList<Appointment> appointments, Ui ui, Storage storage)
            throws RexException {
        assert patients != null : "patient ArrayList is null";
        assert ui != null : "ui is null";
        assert storage != null : "storage is null";
        Rex.logger.log(Level.INFO, "going to extract NRIC");
        String nric = extractNric(trimmedCommand, COMMAND_WORD);
        Rex.logger.log(Level.INFO, "extracted NRIC");
        int index = patients.getExistingPatient(nric);
        assert index > -2 : "Unexpected index!";
        if (index < 0) {
            throw new RexException("No such patient!");
        }
        Rex.logger.log(Level.INFO, "show patients");
        ui.showPatient(patients.getPatientUsingIndex(index));
    }
}
