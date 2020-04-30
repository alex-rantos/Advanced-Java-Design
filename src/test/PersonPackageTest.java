package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import uk.ac.ncl.javacw.person.*;

class PersonPackageTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	static Customer getInstanceCustomer() {
	    Calendar cal = Calendar.getInstance();
	    cal.clear();

	    cal.set(Calendar.YEAR, 1995);
	    cal.set(Calendar.MONTH, 5);
	    cal.set(Calendar.DATE, 3);
	    
	    Name custName = new Name("Alex", "Rantos");
	    Customer cust = new Customer(custName,cal.getTime());

	    cal.set(Calendar.YEAR, 2012);
	    cal.set(Calendar.MONTH, 6);
	    cal.set(Calendar.DATE, 3);
	    
		cust.setDrivingLicence(DrivingLicenceFactory.issueDrivingLicence(cust,cal.getTime(),true));

	    return cust;
	}
	
	static DrivingLicence getInstanceCustomersLicence() {
		return PersonPackageTest.getInstanceCustomer().getDrivingLicence();
	}
	
	/****************** Driving Licence Creation Tests******************/

	@Test
	void drivingLicenceCreationTest() {

	    Calendar cal = Calendar.getInstance();
	    cal.clear();

	    cal.set(Calendar.YEAR, 1995);
	    cal.set(Calendar.MONTH, 5);
	    cal.set(Calendar.DATE, 3);
	    
	    Name custName = new Name("Alex", "Rantos");
		Customer cust = new Customer(custName,cal.getTime());

		assertEquals(cust.getYearOfBirth(), 1995);



	    DrivingLicence dc1 = PersonPackageTest.getInstanceCustomersLicence();
		assertEquals(dc1.yearsActive(), 2019-2012);
		assertEquals(dc1.getYearIssued(), 2012);
		assertEquals(dc1.isDrivingLicenceFull(), true);
		assertEquals(dc1.toString(), "AR-2012-0");

		// checking if same person gets the same drivingLicence
	    Calendar calDuplicate = Calendar.getInstance();
	    calDuplicate.clear();

	    calDuplicate.set(Calendar.YEAR, 1995);
	    calDuplicate.set(Calendar.MONTH, 5);
	    calDuplicate.set(Calendar.DATE, 3);

	    Name custName2 = new Name("Alex", "Rantos");
		Customer custDuplicate = new Customer(custName2,calDuplicate.getTime());
		assertEquals(cust,custDuplicate);

		DrivingLicence dcDuplicate = DrivingLicenceFactory.issueDrivingLicence(custDuplicate,cal.getTime(),true);
		
		assertEquals(dc1, dcDuplicate);	
	}
}
