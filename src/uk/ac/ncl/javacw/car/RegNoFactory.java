package uk.ac.ncl.javacw.car;

import java.util.HashMap;
import java.util.Map;

/** 
 * RegNumber - Registration Number of Cars.
 * NG57 HXE
 */

public class RegNoFactory {
	
	private static final Map<Integer, RegistrationNumber> regNumbers
						= new HashMap<Integer, RegistrationNumber>();

    
	public static RegistrationNumber issueRegNo(String carPlate) {

		carPlate = carPlate.replaceAll(" ","");
		
		int regNoHash = carPlate.hashCode();
		RegistrationNumber regNo = regNumbers.get(regNoHash);
		
		if (regNo != null) {
			//System.out.println("WARNING! Registration number : " + regNo + " has already been issued.");
			return regNo;	
		}
		
		regNo = new RegistrationNumber(carPlate);
		regNumbers.put(regNoHash, regNo);

		//System.out.println("Registration number : " + regNo + " has been successfully issued.");
		return regNo;
		}
}


