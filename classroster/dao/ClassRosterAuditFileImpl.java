/*
*@author Leo K
*@date Mar 2, 2023
*/

package classroster.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class ClassRosterAuditFileImpl implements ClassRosterAuditDAO {
	
	private final File AUDIT_LOG = new File("auditlog.txt");
	
	@Override
	public void writeAuditEntry(String entry) throws ClassRosterPersistenceException {
		LocalDateTime timestamp;
		
		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(AUDIT_LOG, true)))){
			timestamp = LocalDateTime.now();
			out.println(entry + " : " + timestamp);
		} catch (IOException e) {
			throw new ClassRosterPersistenceException("Could not persist information.", e);
		}
	}
}
