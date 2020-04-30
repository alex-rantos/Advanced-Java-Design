package uk.ac.ncl.javacw.person;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DrivingLicenceFactory {
	private static int idSequence = 0;
	private static int serialNumberSequence = 0;
	
	private static Map<Integer,DrivingLicence> drivingLicences = 
			new HashMap<Integer,DrivingLicence>();
	
	/**
	 * static factory method 
	 * 
	 * Creates a drivingLicence for the given person if he has not have one,
	 * otherwise returns the person's licence
	 * 
	 * @param person the given person
	 * @param dateIssued issue date of licence 
	 * @param isDrivingLicenceFull if the licence is full or not
	 * @return the corresponding driving licence for the given person
	 */
	public static DrivingLicence issueDrivingLicence(AbstractPerson person,Date dateIssued, boolean isDrivingLicenceFull) {
		
        // enforce single instance per Person
		DrivingLicence existingDrivingLicence = drivingLicences.get(person.hashCode());
        
        if (existingDrivingLicence != null)
            return existingDrivingLicence;
        
        if (person == null)
        	throw new IllegalArgumentException("Cannot issue a licence for a non existing person"); 
		
		DrivingLicence newDL = new DrivingLicence(idSequence++, dateIssued, person.getName().getInitials(),
												serialNumberSequence++,isDrivingLicenceFull);
		
		drivingLicences.put(person.hashCode(), newDL);
		
		return newDL;
	}
}
