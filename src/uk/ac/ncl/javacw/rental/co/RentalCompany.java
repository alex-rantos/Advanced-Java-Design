package uk.ac.ncl.javacw.rental.co;

import java.util.ArrayList;
import java.util.Collection;

import uk.ac.ncl.javacw.car.Car;
import uk.ac.ncl.javacw.person.DrivingLicence;
import uk.ac.ncl.javacw.person.Person;

public interface RentalCompany {
	
	/**
	 * 
	 * @param typeOfCar is a specifc car type
	 * @return the number of car of the specified type that are available to rent
	 */
	int availableCars(Car typeOfCar);

	/**
	 * A method to return ALL cars of the rental company
	 * used for testing purposes since cars are generated automatically
	 * 
	 * @return an ArrayList of cars
	 */
	public Collection<Car> getAllCars();
	
	/**
	 * 
	 * @return the collection of all the cars currently rented out (if any)
	 */
	Collection<Car> getRentedCars();
	
	/**
	 * 
	 * @param person who might have rented a car
	 * @return the car person has rented (if any)
	 */
	Car getCar(Person person);
	
	/**
	 * Determines whether the person is eligible to rent a car of the specified type
	 * and, if there is a car available, issues a car of the specified type.
	 * @param person the rentor
	 * @param drivingLicence of the rentor
	 * @param typeOfCar specification of the type of the wanted car to rent
	 * @return true person can rent the given car or false if it is not possible.
	 */
	boolean issueCar(Person person, DrivingLicence drivingLicence, Car typeOfCar);
	
	/**
	 * Terminates the given person's rental contract with a rental car and renders that car
	 * available to rent once again. Returns the amount of fuel in Litres required to fill the car's tank.
	 * if a person attempts to terminate a non-existent contract, this method does nothing
	 * 
	 * @param person the person who's contract is about to terminate
	 * @return the amount of fuel in Litres required to fill the car's tank
	 *         or '-1' if the person attempts to terminate a non-existent contract
	 */
	int terminateRental(Person person);
}
