package uk.ac.ncl.javacw.car;

import uk.ac.ncl.javacw.person.Customer;

/**
 * RegistrationNumber - immutable object
 * //(Strings are immutable objects too)
 */
public final class RegistrationNumber {
	private final String firstComp;
	private final String secondComp;
	
	RegistrationNumber(String carPlate) {
		String firstComp = carPlate.substring(0, 4);
		String secondComp = carPlate.substring(4);
		//System.out.println(carPlate + " = " + firstComp +" + " + secondComp);
		if (firstComp.length() == 4) {
			if (isCapitalLetters(firstComp.substring(0, 2))) {
				//int twoDigitNumber = Integer.parseInt(firstComp.substring(2));
				//if (twoDigitNumber >= 10 && twoDigitNumber <= 99) {
				if (firstComp.substring(2).matches("\\d\\d")) {
					this.firstComp = firstComp;
				} else {
					throw new IllegalArgumentException("first component of the Registration must end a 2 digit number");
				}
			} else {
				throw new IllegalArgumentException("first component of the Registration must begin with 2 Capital letters");
			}
		}
		else
			throw new IllegalArgumentException("FirstComp of the Registration" + " Number must be exactly two letters");

		if (secondComp.length() == 3 && isCapitalLetters(secondComp))
			this.secondComp = secondComp;
		else
			throw new IllegalArgumentException("secondComp of the Registration" + " Number must be exactly threee letters");
	}
	
	public RegistrationNumber(String firstComp, String secondComp) {
		this.firstComp = firstComp;
		this.secondComp = secondComp;
	}
	
	/**
	 * return the first component of the licence
	 * 
	 * @return the first component
	 */
	public String getFirstComp() {
		return firstComp;		
	}
	
	/**
	 * return the second component of the licence
	 * 
	 * @return the second component
	 */
	public String getSecondComp() {
		return secondComp;		
	}

	/** Helper function to check if an string consists only of capital letters
	 * 
	 * @param input
	 * @return true if the input consists only of Capital Letter
	 */
	private boolean isCapitalLetters(String input) {
	    return input.matches("[A-Z]+");
	}

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
	public boolean equals(Object obj) {
        if (this == obj)
            return true;
        
        if (!(obj instanceof RegistrationNumber))
            return false;
        
        final RegistrationNumber regNo = (RegistrationNumber) obj;
        
        return this.firstComp == regNo.firstComp 
                && this.secondComp == regNo.secondComp;
		
	}

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {        
        return (this.firstComp + this.secondComp).hashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
	@Override
	public String toString() {
		return firstComp + " " + secondComp;
	}
}