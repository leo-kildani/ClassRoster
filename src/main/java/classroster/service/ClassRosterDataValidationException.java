/*
*@author Leo K
*@date Mar 2, 2023
*/

package classroster.service;

public class ClassRosterDataValidationException extends Exception {
	
	public ClassRosterDataValidationException(String msg) {
		super(msg);
	}
	
	public ClassRosterDataValidationException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
