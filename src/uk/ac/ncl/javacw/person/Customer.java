package uk.ac.ncl.javacw.person;

import java.util.Date;

/**
 * Customer class
 * holds a drivingLicence for the necessary checks
 *
 */

public final class Customer extends AbstractPerson{
    private DrivingLicence drivingLicence;
    
    /**
     * Create person with given name and date of birth.
     *
     * @param name the person name
     * @param dateOfBirth the person's date of birth
     * @throws NullPointerException if either <code>name</code>
     * or <code>dateOfBirth</code> is null
     */
    public Customer(Name name, Date dateOfBirth) { 
    	super(name,dateOfBirth);
        this.drivingLicence = null;
    }

    /**
     * sets the driving licence of the person
     * 
     * param drivingLicence the driving licence 
     */
    public void setDrivingLicence(DrivingLicence drivingLicence) {

        this.drivingLicence = drivingLicence;
    }

    /**
     * returns the driving licence of the person
     * 
     * @return the driving licence
     */
    public DrivingLicence getDrivingLicence() {

        return  this.drivingLicence;
    }
}
