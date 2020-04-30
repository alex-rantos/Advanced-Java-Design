package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import uk.ac.ncl.javacw.car.*;
import uk.ac.ncl.javacw.person.Customer;
import uk.ac.ncl.javacw.person.Name;
import uk.ac.ncl.javacw.person.Person;
import uk.ac.ncl.javacw.rental.co.*;

class RentalCompanyPackageTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}
	
	
    /**
     * Test method for {@link uk.ac.ncl.javacw.rental.co.CarRentalCompany#CarRentalCompany()}.
     */
	@Test
	void CarRentalCompanyTest() {
		CarRentalCompany carCo = new CarRentalCompany();
		assertNull(carCo.getRentedCars());
		
		assertEquals(carCo.availableCars(CarPackageTest.getInstanceSmallCar()),20);
		assertEquals(carCo.availableCars(CarPackageTest.getInstanceLargeCar()),10);
		
		assertNull(carCo.getRentedCars());
		assertNull(carCo.getCar(null));
		
		assertNull(carCo.getCar(PersonPackageTest.getInstanceCustomer()));
	}
	
    /**
     * Test method for {@link uk.ac.ncl.javacw.rental.co.AbstractRentalCompany#availableCars()}.
     */
	@Test
	void availableCarsTest() {
		CarRentalCompany carCo = new CarRentalCompany();
		assertEquals(carCo.availableCars(CarPackageTest.getInstanceSmallCar()), 20);
		assertEquals(carCo.availableCars(CarPackageTest.getInstanceLargeCar()), 10);
	}
	
    /**
     * Test method for {@link uk.ac.ncl.javacw.rental.co.AbstractRentalCompany#getRentedCars()}.
     */
	@Test
	void getRentedCarsTest() {
		CarRentalCompany carCo = new CarRentalCompany();
		Customer customer = PersonPackageTest.getInstanceCustomer();
		ArrayList<Car> cars = carCo.getAllCars();

		boolean isRented = carCo.issueCar(customer, customer.getDrivingLicence(),cars.get(0));
		
		assertEquals(isRented,true);
		assertEquals(carCo.getRentedCars().get(0),cars.get(0));
	}
	
    /**
     * Test method for {@link uk.ac.ncl.javacw.rental.co.AbstractRentalCompany#getCar()}.
     */
	@Test
	void getCarTest() {
		CarRentalCompany carCo = new CarRentalCompany();
		Customer customer = PersonPackageTest.getInstanceCustomer();
		ArrayList<Car> cars = carCo.getAllCars();

		boolean isRented = carCo.issueCar(customer, customer.getDrivingLicence(),cars.get(0));
		
		assertEquals(isRented,true);
		assertEquals(carCo.getCar(customer),cars.get(0));
	}
	
    /**
     * Test method for {@link uk.ac.ncl.javacw.rental.co.AbstractRentalCompany#issueCar()}.
     */
	@Test
	void issueCarTest() {
		CarRentalCompany carCo = new CarRentalCompany();
		Customer customer = PersonPackageTest.getInstanceCustomer();
		ArrayList<Car> cars = carCo.getAllCars();

		boolean isRented = carCo.issueCar(customer, customer.getDrivingLicence(),cars.get(0));
		
		assertEquals(isRented,true);
	}
	
    /**
     * Test method for {@link uk.ac.ncl.javacw.rental.co.AbstractRentalCompany#terminateRental(Person)}.
     */
	@Test
	void terminateRentalTest() {
		CarRentalCompany carCo = new CarRentalCompany();
		Customer customer = PersonPackageTest.getInstanceCustomer();

		ArrayList<Car> cars = carCo.getAllCars();
		
		boolean isRented = carCo.issueCar(customer, customer.getDrivingLicence(), cars.get(0));
		assertEquals(isRented,true);
		assertEquals(carCo.terminateRental(customer), 0);
		assertEquals(carCo.terminateRental(null), -1);
		
	}
	
}
