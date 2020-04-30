package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import uk.ac.ncl.javacw.car.*;

class CarPackageTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("Test for CarPackage is starting now");
	}

	static SmallCar getInstanceSmallCar() {
		RegistrationNumber regNo = RegNoFactory.issueRegNo("RE42XXE");
		SmallCar smallCar = new SmallCar(regNo);
		return smallCar;
	}
	
	static LargeCar getInstanceLargeCar() {
		RegistrationNumber regNo = RegNoFactory.issueRegNo("RE42XEE");
		LargeCar largeCar = new LargeCar(regNo);
		return largeCar;
	}
	/****************** Registration Number Tests ******************/
	
    /**
     * Test method for {@link uk.ac.ncl.javacw.car.RegNoFactory#issueRegNo()}.
     */
	@Test
	void issueRegNoTest() {
		RegistrationNumber regNo = RegNoFactory.issueRegNo(" N E 3 3   D E F ");
		assertEquals("NE33 DEF", regNo.toString());	
	}

    /**
     * Test method for {@link uk.ac.ncl.javacw.car.RegistrationNumber#getFirstComp()}.
     */
	@Test
	void getFirstCompTest() {
		RegistrationNumber regNo = RegNoFactory.issueRegNo(" D E 33   D E F ");
		assertEquals("DE33", regNo.getFirstComp());	
	}

    /**
     * Test method for {@link uk.ac.ncl.javacw.car.RegistrationNumber#getSecondComp()}.
     */
	@Test
	void getSecondCompTest() {
		RegistrationNumber regNo = RegNoFactory.issueRegNo(" N X 3 3D X F ");
		assertEquals("DXF", regNo.getSecondComp());	
	}

    /**
     * Test method to check if same plate string gets you the same licence plate
     */
	@Test
	void RegNoFactoryDuplicateTest() {
		RegistrationNumber regNo = RegNoFactory.issueRegNo(" N E 3 4   D E F  ");
		RegistrationNumber regNoDuplicate = RegNoFactory.issueRegNo("NE34DEF");
		assertEquals(regNoDuplicate, regNo);	
	}

	/****************** Car Tests ******************/

    /**
     * Test method for {@link uk.ac.ncl.javacw.car.SmallCar#SmallCar()}.
     */
	@Test
	void smallCarTest() {
		RegistrationNumber regNo = RegNoFactory.issueRegNo(" N E 3 3   BDE ");
		Car car = new SmallCar(regNo);
		assertEquals(car.getCurrentFuel(),0);
		assertEquals(car.getTankCapacity(),49);
		assertEquals(car.getRegistrationNo(),regNo);
	}

    /**
     * Test method for {@link uk.ac.ncl.javacw.car.LargeCar#LargeCar()}.
     */
	@Test
	void largeCarTest() {
		RegistrationNumber regNo = RegNoFactory.issueRegNo(" N E 55   D EE ");
		Car car = new LargeCar(regNo);
		assertEquals(car.getCurrentFuel(),0);
		assertEquals(car.getTankCapacity(),60);
		assertEquals(car.getRegistrationNo(),regNo);
	}

    /**
     * Test method for {@link uk.ac.ncl.javacw.car.Car#getCurrentFuel()}.
     */
	@Test
	void getCurrentFuelTest() {
		RegistrationNumber regNo = RegNoFactory.issueRegNo("DS55 XEE ");
		Car car = new SmallCar(regNo);
		car.addFuel(70);
		assertEquals(car.getCurrentFuel(),49);
	}

    /**
     * Test method for {@link uk.ac.ncl.javacw.car.Car#isTankEmpty()}.
     */
	@Test
	void isTankEmptyTest() {
		RegistrationNumber regNo = RegNoFactory.issueRegNo("DS55 XEE ");
		Car car = new SmallCar(regNo);
		assertEquals(car.isTankEmpty(),true);
	}

    /**
     * Test method for {@link uk.ac.ncl.javacw.car.AbstracCartClass#isTankFull()}.
     */
	@Test
	void isTankFullTest() {
		RegistrationNumber regNo = RegNoFactory.issueRegNo("DS55 XEE ");
		Car car = new SmallCar(regNo);
		car.addFuel(49);
		assertEquals(car.isTankFull(),true);
	}

    /**
     * Test method for {@link uk.ac.ncl.javacw.car.Car#addFuel()}.
     */
	@Test
	void addFuelTest() {
		RegistrationNumber regNo = RegNoFactory.issueRegNo("DS55 XEE ");
		Car car = new SmallCar(regNo);
		car.addFuel(49);
		assertEquals(car.isTankFull(),true);
	}

    /**
     * Test method for {@link uk.ac.ncl.javacw.car.Car#getTankCapacity()}.
     */
	@Test
	void getTankCapacityTest() {
		RegistrationNumber regNo = RegNoFactory.issueRegNo("DS55 XEE ");
		Car car = new SmallCar(regNo);
		assertEquals(car.getTankCapacity(),49);
		
		regNo = RegNoFactory.issueRegNo("DX35 XEE ");
		Car largeCar = new LargeCar(regNo);
		assertEquals(largeCar.getTankCapacity(),60);
	}
	
    /**
     * Test method for {@link uk.ac.ncl.javacw.car.Car#RegistrationNo()}.
     */
	@Test
	void getRegistrationNoTest() {
		RegistrationNumber regNo = RegNoFactory.issueRegNo("DS55 XEE ");
		Car car = new SmallCar(regNo);
		assertEquals(car.getRegistrationNo(), regNo);
	}

    /**
     * Test method for {@link uk.ac.ncl.javacw.car.SmallCar#drive()}.
     */
	@Test
	void driveSmallCarTest() {
		RegistrationNumber regNo = RegNoFactory.issueRegNo("DS55 XEE ");
		Car car = new SmallCar(regNo);
		assertEquals(car.drive(50),0);
		
		car.addFuel(25);
		
		assertEquals(car.drive(2000),100);
		assertEquals(car.getCurrentFuel(),-75);
		
		// empty can't be driven
		assertEquals(car.drive(2000),0);
	}

    /**
     * Test method for {@link uk.ac.ncl.javacw.car.LargeCar#drive()}.
     */
	@Test
	void driveLargeCarTest() {
		RegistrationNumber regNo = RegNoFactory.issueRegNo("XX55 XEE ");
		Car car = new LargeCar(regNo);
		
		car.addFuel(10);
		
		assertEquals(car.drive(50),5);
		assertEquals(car.drive(100),8);
		
		assertEquals(car.getCurrentFuel(),-3);
		
		// empty can't be driven
		assertEquals(car.drive(20),0);
		
	}

}
