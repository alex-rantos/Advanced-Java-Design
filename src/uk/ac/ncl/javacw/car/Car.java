package uk.ac.ncl.javacw.car;

/** 
 * Car - interface to rental cars.
 *
 */ 
public interface Car {
	
	/** 
	 * Returns car's registration number.
	 * Each car has a unique registration number
	 * 
     * @return car's registration number
	 */ 
	RegistrationNumber getRegistrationNo();
	
    /**
     * Returns capacity in whole Litres
     * of the car's fuel tank
     * @return car's tank capacity
     */
	int getTankCapacity();

    /**
     * Get the amount of fuel in while Litres
     * currently in the fuel tank
     *
     * @return car's current fuel
     */
	int getCurrentFuel();
	

    /**
     * Indicates where the car's fuel is full or not.
     *
     * @return true if tank is full or false if it is not.
     */
	boolean isTankFull();
	
    /**
     * Indicates how many litres the car needs in order to have a full fuel tank
     *
     * @return the amount of litres
     */
	int litresToFullTank();

    /**
     * Indicates where the car's fuel is empty or not.
     *
     * @return true if tank is empty or false if it is not.
     */
	boolean isTankEmpty();
	
	/**
	 * Full the fuel tank of the car
	 */
	void fullTank();
	
    /**
     * add a given number of whole Litres to the fuel tank (up to the tank's capacity)
     * and which, after execution, indicates how much fuel was added to the tank
     *
     * @param amount the amount of litres to add in Tank
     * @return how much fuel was added to the tank
     */
	int addFuel(int fuelLitres);
	
	/**
     *  "drive" the car for a given number of whole Kilometres 
     *  that returns the number of whole Litres of fuel consumed during the journey 
     
     * @param number of whole Kilometres to drive the car
     * @return the number of whole Litres consumed during the given kilometres
     */
	int drive(int kilometres);
}
