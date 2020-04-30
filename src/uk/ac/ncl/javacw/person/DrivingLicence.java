package uk.ac.ncl.javacw.person;

import java.util.Calendar;
import java.util.Date;

import uk.ac.ncl.javacw.car.RegistrationNumber;

public class DrivingLicence {
	private final int drivingLicenceID;
	private Date dateIssued;
	
	/**
	 * Components of the licence Number
	 * 
	 * Licence Number : driversInitials + '-' + yearIssued + '-' + serialNumber
	 */
	private String driversInitials;
	private int yearIssued;
	private final int serialNumber;
	
	private boolean isFullDrivingLicence;

	/**
	 * private-package constructor
	 * Creates a DrivingLicence with the given parameters
	 * 
	 * @param id licence's id
	 * @param dateIssued licence's issue date
	 * @param driversInitials driver's initials
	 * @param serialNumber licence's serial Number
	 * @param isFull licence's flag if full
	 */
	DrivingLicence(int id, Date dateIssued, String driversInitials, int serialNumber, boolean isFull) {
		this.drivingLicenceID = id;
		this.dateIssued = dateIssued;
		this.driversInitials = driversInitials;
		// Date Format : Day Month hh:mm:ss BST Year
		yearIssued = Integer.parseInt(dateIssued.toString().split(" ")[5]);
		this.serialNumber = serialNumber;
		this.isFullDrivingLicence = isFull;
	}

	/**
	 * return the unique ID of the driving licence
	 * 
	 * @return the ID of the licence
	 */
	private int getLicenceID() {
		return this.drivingLicenceID;
	}
	
	/**
	 * return the serial Number of the licence
	 * 
	 * @return the serial number
	 */
	private int getSerialNumber() {
		return this.serialNumber;
	}
	
	/**
	 * return the year the licence had been issued
	 * 
	 * @return the year issued
	 */
	public int getYearIssued() {
		return this.yearIssued;
	}
	
	/**
	 * return the initials of the driver
	 * 
	 * @return the initials
	 */
	public String getInitials() {
		return this.driversInitials;
	}
	
	/**
	 * Calculates how many years the drivingLicence is issued
	 * @return the years this drivingLicence is in effect
	 */
	public int yearsActive() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        //System.out.println(currentYear + " - " + this.yearIssued);
        return (currentYear - this.yearIssued);
	}
	
	
	/**
	 * returns if the drivingLicence is full or not
	 * @return true if drivingLicence is flagged as full or false if otherwise
	 */
	public boolean isDrivingLicenceFull() {
		return this.isFullDrivingLicence;
	}

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
	public boolean equals(Object obj) {
        if (this == obj)
            return true;
        
        if (!(obj instanceof DrivingLicence))
            return false;
        
        final DrivingLicence drivingLicence = (DrivingLicence) obj;
        
        return this.toString() == drivingLicence.toString();
		
	}

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {        
        return (this.toString()).hashCode();
    }

    /**
     * Return the LicenceNumber
     * @see java.lang.Object#toString()
     */
	@Override
	public String toString() {
		return driversInitials + "-" + yearIssued + "-" + serialNumber;
	}
}
