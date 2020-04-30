package uk.ac.ncl.javacw.person;

import java.util.Date;

/** 
 * Customer - 
 * change of dateOfBirth is not allowed as it is an immutable member
 *
 */
abstract class AbstractPerson implements Person{
    private Name name;
    private final Date dateOfBirth;
    
    /**
     * Create person with given name and date of birth.
     *
     * @param name the person name
     * @param dateOfBirth the person's date of birth
     * @throws NullPointerException if either <code>name</code>
     * or <code>dateOfBirth</code> is null
     */
    public AbstractPerson(Name name, Date dateOfBirth) { 
        if (name == null) 
            throw new IllegalArgumentException("name is null");
        
        if (dateOfBirth == null)
            throw new IllegalArgumentException("date of birth is null");
        
        this.name = name;
        
        this.dateOfBirth = new Date(dateOfBirth.getTime());
    }
    
    /**
     * @see uk.ac.ncl.javacw.person.Person#getDateOfBirth()
     */
    public Name getName() {
        return name;
    }

    /**
     * @see uk.ac.ncl.javacw.person.Person#getDateOfBirth()
     */
    public Date getDateOfBirth() {
        return (Date) dateOfBirth.clone();
    }
    
    /**
     * Returns person's the year of birth 
     * 
     * @return the year of birth
     */
    public int getYearOfBirth() {
		return Integer.parseInt(this.dateOfBirth.toString().split(" ")[5]);
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        
        if (!(obj instanceof Customer))
            return false;
        
        final AbstractPerson person = (AbstractPerson) obj;
        
        return name.equals(person.name) 
                && dateOfBirth.equals(person.dateOfBirth);
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hc = 17;
        
        hc = 37 * hc + name.hashCode();
        
        return 37 * hc + dateOfBirth.hashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return name + " (" + dateOfBirth + ")";
    }    
}
