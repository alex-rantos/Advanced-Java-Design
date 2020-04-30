package uk.ac.ncl.javacw.car;

public class SmallCar extends AbstractCar {
	private static final int SMALL_CAR_TANK_CAPACITY = 49;

	// only for easier test purposes!!!
	public SmallCar() {
		super(SMALL_CAR_TANK_CAPACITY);
	}
	public SmallCar(RegistrationNumber regNo) {
		super(SMALL_CAR_TANK_CAPACITY, regNo);
	}

	@Override
	public int drive(int kilometres) {
		if (isTankEmpty()) 
			return 0;
		
		int fuelConsumed = kilometres/20;
		currentFuel -= fuelConsumed;
		return fuelConsumed;		
	}

}
