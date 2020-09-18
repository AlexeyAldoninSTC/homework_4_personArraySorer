package aldonin.task.processor;

import aldonin.task.domain.Person;

public interface PersonArraySorter {

    /**
     * Method to sort curtain array of Persons
     * @param people - array to be sorted
     */
    void sort(Person[] people);
}
