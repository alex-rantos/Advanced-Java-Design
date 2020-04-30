package uk.ac.ncl.javacw.car;

public class LargeCar extends AbstractCar  {
	private static final int LARGE_CAR_TANK_CAPACITY = 60;

	// only for easier test purposes!!!
	public LargeCar() {
		super(LARGE_CAR_TANK_CAPACITY);
	}
	public LargeCar(RegistrationNumber regNo) {
		super(LARGE_CAR_TANK_CAPACITY, regNo);
	}

	@Override
	public int drive(int kilometres) {
		if (isTankEmpty()) 
			return 0;
		if (kilometres <= 50) {
			int fuelConsumed = kilometres/10;
			currentFuel -= fuelConsumed;
			return fuelConsumed;	
		} else {
			int fuelConsumed = (kilometres - 50)/15 + 50/10;
			currentFuel -= fuelConsumed;
			return fuelConsumed;
		}
	}
}
