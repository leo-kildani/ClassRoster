/*
*@author Leo K
*@date Mar 2, 2023
*/

package classroster.service;

public class ClassRosterDuplicateIdException extends Exception {
 
		public ClassRosterDuplicateIdException(String msg) {
			super(msg);
		}
		
		public ClassRosterDuplicateIdException(String msg, Throwable cause) {
			super(msg, cause);
		}
}
