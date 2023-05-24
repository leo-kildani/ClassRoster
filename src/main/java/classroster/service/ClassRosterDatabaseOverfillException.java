package classroster.service;

public class ClassRosterDatabaseOverfillException extends Exception {

    public ClassRosterDatabaseOverfillException(String message) {
        super(message);
    }

    public ClassRosterDatabaseOverfillException(String message, Throwable cause) {
        super(message, cause);
    }
}
