package uk.ac.ncl.javacw.car;

abstract class AbstractCar implements Car {
	private final RegistrationNumber regNo; // immutable member
	int tankCapacity,
		currentFuel;

	// only for easier test purposes!!! 
	// do not use since you cannot add registrationNumber to the car
	AbstractCar(int tankCapacity) {
		this.currentFuel = 0;
		this.tankCapacity = tankCapacity;
		this.regNo = null;
	}
	
	AbstractCar(int tankCapacity, RegistrationNumber regNo) {
		this.currentFuel = 0;
		this.tankCapacity = tankCapacity;
		this.regNo = regNo;
	}
	/**
	 * Return a new instance of the RegistrationNumber of the car
	 * since registration Number is an immutable member
	 */
	@Override
	public RegistrationNumber getRegistrationNo() {
		return new RegistrationNumber(regNo.getFirstComp(),regNo.getSecondComp());
	}

	/**
	 * @see uk.ac.ncl.javacw.car.Car#getTankCapacity()
	 */
	@Override
	public int getTankCapacity() {
		return this.tankCapacity;
	}

	/**
	 * @see uk.ac.ncl.javacw.car.Car#getCurrentFuel()
	 */
	@Override
	public int getCurrentFuel() {
		return this.currentFuel;
	}

	/**
	 * @see uk.ac.ncl.javacw.car.Car#isTankEmpty()
	 */
	@Override
	public boolean isTankEmpty() {
		return (this.currentFuel <= 0);
	}

	/**
	 * @see uk.ac.ncl.javacw.car.Car#fullTank()
	 */
	@Override
	public void fullTank() {
		this.currentFuel = this.tankCapacity;
	}

	/**
	 * @see uk.ac.ncl.javacw.car.Car#isTankFull()
	 */
	@Override
	public boolean isTankFull() {
		return (this.currentFuel == this.tankCapacity);
	}

	/**
	 * @see uk.ac.ncl.javacw.car.Car#litresToFullTank()
	 */
	@Override
	public int litresToFullTank() {
		if (this.currentFuel < 0) 
			return Math.abs(this.currentFuel) + this.tankCapacity;
		else 
			return this.tankCapacity - this.currentFuel;
	}

	/**
	 * @see uk.ac.ncl.javacw.car.Car#addFuel()
	 */
	@Override
	public int addFuel(int fuelLitres) {
		if (isTankFull())
			return 0;
		int litresToFull = this.tankCapacity - this.currentFuel;
		if (litresToFull > fuelLitres) {
			this.currentFuel += fuelLitres;
			return fuelLitres;
		} else {
			this.currentFuel = this.tankCapacity;
			return litresToFull;
		}
	}

	/**
	 * @see uk.ac.ncl.javacw.car.Car#drive()
	 */
	@Override
	public int drive(int kilometres) {
		if (isTankEmpty()) return 0;
		else {
			this.currentFuel -= kilometres;
			return kilometres;
		}
	}

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
	public boolean equals(Object obj) {
        if (this == obj)
            return true;
        
        if (!(obj instanceof RegistrationNumber))
            return false;
        
        final AbstractCar abstractCar = (AbstractCar) obj;
        
        return this.regNo.equals(abstractCar.getRegistrationNo());
		
	}

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {        
        return (this.regNo).hashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {        
        return "Car's plate : " + (this.regNo).toString();
    }

}
