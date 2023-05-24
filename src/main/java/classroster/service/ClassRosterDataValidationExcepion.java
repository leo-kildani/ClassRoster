/*
*@author Leo K
*@date Mar 2, 2023
*/

package classroster.service;

public class ClassRosterDataValidationExcepion extends Exception {
	
	public ClassRosterDataValidationExcepion(String msg) {
		super(msg);
	}
	
	public ClassRosterDataValidationExcepion(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
