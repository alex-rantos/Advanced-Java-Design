package uk.ac.ncl.javacw.rental.co;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import uk.ac.ncl.javacw.car.Car;
import uk.ac.ncl.javacw.car.SmallCar;
import uk.ac.ncl.javacw.car.LargeCar;
import uk.ac.ncl.javacw.car.RegNoFactory;
import uk.ac.ncl.javacw.car.RegistrationNumber;
import uk.ac.ncl.javacw.person.DrivingLicence;
import uk.ac.ncl.javacw.person.Person;

abstract class AbstractRentalCompany implements RentalCompany {
	
	// Integer : HashCode of the rented car & Person : the customer who has rented the car
	Map<Integer,Person> contracts;
	
	// a company can increase its car fleet so dont make the following members immutable
	Map<Integer,Car> carsFleet;
	private int carsFleetSize;
	
	AbstractRentalCompany(int carsFleetSize) {
		this.carsFleetSize = carsFleetSize;
		// not HashMap<Integer,Car>(carsFleetSize) since increasing a company's fleet is allowed
		carsFleet = new HashMap<Integer,Car>();
		contracts = new HashMap<Integer,Person>();
	}
	
	/**
	 * Check if current cars amount exceeds the size of the company's cars allowance
	 * and if true proceeds to add given <code> car </code> to the fleet
	 * 
	 * @param car car to add to fleet
	 */
	void addCarToFleet(Car car) {
		if (this.carsFleet.size() < this.carsFleetSize)
			this.carsFleet.put(car.hashCode(), car);
		else throw new IllegalStateException("Cannot add any more cars to the rental service");
		car.fullTank();
	}
	
	/**
	 * return the car fleet size of the rental company
	 * 
	 * @return the car fleet size
	 */
	private int getCarsFleet() {
		return this.carsFleetSize;
	}
	
	/**
	 * @see uk.ac.ncl.javacw.rental.co.RentalCompany#getAllCars()
	 */
	public ArrayList<Car> getAllCars() {
		ArrayList<Car> cars = new ArrayList<Car>(this.carsFleetSize);
		for (Car car:this.carsFleet.values()) 
			cars.add(car);
		return cars;
	}
	
	/**
	 * @see uk.ac.ncl.javacw.rental.co.RentalCompany#availableCars(Car)
	 */
	@Override
	public int availableCars(Car typeOfCar) {
		int[] availableCars = {0};
		
		this.carsFleet.forEach((k,v)->{
			if (!this.contracts.containsKey(v) &&
					v.getClass().equals(typeOfCar.getClass())) {
				availableCars[0]++;				
			}
		});
		
		return availableCars[0];
	}

	/**
	 * @see uk.ac.ncl.javacw.rental.co.RentalCompany#getRentedCars()
	 */
	@Override
	public ArrayList<Car> getRentedCars() {
		if (this.carsFleet.size() == 0 || this.contracts.size() == 0)
			return null;
		
		ArrayList<Car> rentedCars = new ArrayList<Car>(this.contracts.size());
		for (Map.Entry<Integer, Person> contract : this.contracts.entrySet()) {
			if (this.carsFleet.containsKey(contract.getKey())) {
				rentedCars.add(this.carsFleet.get(contract.getKey()));
			} else {
				throw new IllegalStateException("Undefined behavior!");
			}
		}
		return rentedCars;
	}
	
	/**
	 * Check if a person has rented a car and returns the hashcode of that car
	 * Return type is String because there is no invalid Integer to return
	 * if there is no hashCode to return.
	 * 
	 * @param person the person who might have rented a car
	 * @return the hashcode as String of the rented car if the given person has rented one
	 *         or 	 if that person has not. 	      
	 */
	private String getCarHashCode(Person person) {
		if (person == null || this.contracts.size() == 0)
			return null;
		if (this.contracts.containsValue(person)) {
			for (Map.Entry<Integer, Person> contract : this.contracts.entrySet()) {
				if (person.equals(contract.getValue())) {
					return contract.getKey().toString();
				}
			}
		} 
		return null;
	}
	
	/**
	 * @see uk.ac.ncl.javacw.rental.co.RentalCompany#getCar(Person)
	 */
	@Override
	public Car getCar(Person person) {
		if (person == null || this.contracts.size() == 0)
			return null;
		if (this.contracts.containsValue(person)) {
			for (Map.Entry<Integer, Person> contract : this.contracts.entrySet()) {
				if (person.equals(contract.getValue())) {
					return this.carsFleet.get(contract.getKey());
				}
			}
		} 
		return null;
	}
	

	/**
	 * @see uk.ac.ncl.javacw.rental.co.RentalCompany#issueCar(Person, DrivingLicence, Car)
	 */
	@Override
	public boolean issueCar(Person person, DrivingLicence drivingLicence, Car typeOfCar) {
		if ( this.isCarAvailable(typeOfCar) &&
			canPersonRent(person, drivingLicence, typeOfCar) &&
			isDrivingLicenceEligible(drivingLicence, typeOfCar) )
		{
			this.contracts.put(typeOfCar.hashCode(),person);
			System.out.println(typeOfCar + " issued @ " + person);
			typeOfCar.fullTank();
			return true;
		} else {
			System.out.println("not issued.");
			return false;			
		}
	}

	/**
	 * @see uk.ac.ncl.javacw.rental.co.RentalCompany#terminateRental(Person)
	 */
	@Override
	public int terminateRental(Person person) {
		if (!this.contracts.containsValue(person))
			return -1;
		else {
			int rentedCarHashCode = Integer.parseInt(this.getCarHashCode(person));
			if (!this.carsFleet.containsKey(rentedCarHashCode))		
				throw new IllegalStateException("Undefinied behaviour. CarHashCode should have existed in carsFleet");
			else {
				this.contracts.remove(rentedCarHashCode);
				System.out.println(rentedCarHashCode);
				Car carToUnrent = this.carsFleet.get(rentedCarHashCode);
				return carToUnrent.litresToFullTank();
			}
		}
	}	
	
	/**
	 * See if a customer can rent a car or not by checking if the given person
	 * is already renting a car or not.
	 * @param person the given customer to check
	 * @return true if the given person can rent a car or false if he is not.
	 */
	boolean canPersonRent(Person person, DrivingLicence drivingLicence, Car typeOfCar) {
		// check if the given person is already renting a car
		for (Person registeredCust : this.contracts.values()) {
			if (person.equals(registeredCust)) {
				System.out.println(person +" is already renting a car.");
				return false;
			}
		}
		if (isDrivingLicenceEligible(drivingLicence, typeOfCar))
			return true;				
		System.out.println(person +" cannot rent.");
		return false;
	}
	
	/**
	 * check if the driving licence is eligible to rent a car
	 * 
	 * @param drivingLicence the driving Licence
	 * @param typeOfCar the specific car to rent
	 * @return
	 */
	boolean isDrivingLicenceEligible(DrivingLicence drivingLicence, Car typeOfCar) {
		if (drivingLicence.isDrivingLicenceFull())
			return  true;
		else 
			return false;
	}
	
	/**
	 * Checks if there are cars of the given type available for rent
	 * @param typeOfCar specified type of car
	 * @return true if they are car available or false if they are not.
	 */
	boolean isCarAvailable(Car typeOfCar) {
		// check if there is a car available
		if (!this.contracts.containsKey(typeOfCar.hashCode())) {
			return true;
		} else {
			System.out.println(typeOfCar +" not available to rent.");
			return false;
		}
	}
}
