/*
*@author Leo K
*@date Mar 2, 2023
*/

package classroster.dao;

public interface ClassRosterAuditDAO {
	
	public void writeAuditEntry(String entry) throws ClassRosterPersistenceException;
}
