package uk.ac.ncl.javacw.rental.co;

import java.util.Random;

import uk.ac.ncl.javacw.car.Car;
import uk.ac.ncl.javacw.car.LargeCar;
import uk.ac.ncl.javacw.car.RegNoFactory;
import uk.ac.ncl.javacw.car.RegistrationNumber;
import uk.ac.ncl.javacw.car.SmallCar;
import uk.ac.ncl.javacw.person.DrivingLicence;
import uk.ac.ncl.javacw.person.Person;

public class CarRentalCompany extends AbstractRentalCompany {

	private static final String SMALL_CAR_IDENTIFIER = "uk.ac.ncl.javacw.car.SmallCar";
	private static final String LARGE_CAR_IDENTIFIER = "uk.ac.ncl.javacw.car.LargeCar";
	
	// Not final since a company can increase its fleet
	private static int CAR_FLEET = 30;
	private static int SMALL_CAR_FLEET = 20;
	private static int LARGE_CAR_FLEET = 10;
	
	/**
	 * Create a rental company with 20 Small Cars and 10 LargeCars
	 * with randomly generated Registration Number.
	 */
	public CarRentalCompany() {
		super(CAR_FLEET);
		
		for (int i = 0 ; i < SMALL_CAR_FLEET ; i++) {
			String randomCarPlateGeneration = "SM"+i/10+i%10+"DE"+
					Character.toString(new Random().nextInt(((int)'Z'- (int)'A') + 1) + (int)'A');
			RegistrationNumber regNo = RegNoFactory.issueRegNo(randomCarPlateGeneration);
			SmallCar smallCar = new SmallCar(regNo);
			super.addCarToFleet(smallCar);
		}
		
		for (int i = 0 ; i < LARGE_CAR_FLEET ; i++) {
			String randomCarPlateGeneration = "LA"+i/10+i%10+"DE"+
					Character.toString(new Random().nextInt(((int)'Z'- (int)'A') + 1) + (int)'A');
			RegistrationNumber regNo = RegNoFactory.issueRegNo(randomCarPlateGeneration);
			LargeCar largeCar = new LargeCar(regNo);
			super.addCarToFleet(largeCar);
		}
	}
	
	/**
	 * If typeOfCar is a SmallCar, Person must be at least 20 years old and have a DrivingLicence for at least 1 year.
	 * 
	 * If typeOfCar is a LargeCar, Person must be at least 25 years old and have a DrivingLicence for at least 5 year.
	 */
	@Override
	boolean canPersonRent(Person person, DrivingLicence drivingLicence, Car typeOfCar) {
		if (typeOfCar.getClass().getName() == this.SMALL_CAR_IDENTIFIER) {
			if ((person.getYearOfBirth() >= 20) && drivingLicence.yearsActive() >= 1)
				return true;
		} else if (typeOfCar.getClass().getName() == this.LARGE_CAR_IDENTIFIER) {
			if ((person.getYearOfBirth() >= 25) && drivingLicence.yearsActive() >= 5)
				return true;			
		} else {
			throw new IllegalArgumentException("Undefined Car Type!");
		}
		return false;
	}
}
