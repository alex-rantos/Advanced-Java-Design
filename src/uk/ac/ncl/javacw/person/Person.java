package uk.ac.ncl.javacw.person;

import java.util.Date;

/** 
 * Person - interface for person 
 */

public interface Person {
    /**
     * Get the name.
     *
     * @return the name of the person
     */
    public Name getName();
    
    /**
     * Get the date of birth.
     *
     * @return the date of birth of the person
     */
    public Date getDateOfBirth();
    
    /**
     * Get the year of birth.
     *
     * @return the year of birth of the person
     */
    public int getYearOfBirth();
}
