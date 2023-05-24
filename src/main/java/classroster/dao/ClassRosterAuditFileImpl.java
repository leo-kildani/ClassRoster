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

import org.springframework.context.annotation.ComponentScans;
import org.springframework.stereotype.Component;

@Component
public class ClassRosterAuditFileImpl implements ClassRosterAuditDAO {
	
	private final File auditLog;
	
	public ClassRosterAuditFileImpl() {
		auditLog = new File("src\\main\\resources\\auditlog.txt");
	}
	
	public ClassRosterAuditFileImpl(String auditFile) {
		auditLog = new File(auditFile);
	}
	
	@Override
	public void writeAuditEntry(String entry) throws ClassRosterPersistenceException {
		LocalDateTime timestamp;
		
		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(auditLog, true)))){
			timestamp = LocalDateTime.now();
			out.println(entry + " : " + timestamp);
		} catch (IOException e) {
			throw new ClassRosterPersistenceException("Could not persist information.", e);
		}
	}
}
