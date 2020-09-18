package aldonin.task.processor;

import aldonin.task.domain.Person;

import java.util.Arrays;

public class FirstSorter implements PersonArraySorter {

    @Override
    public void sort(Person[] people) {
        Arrays.sort(people);
    }
}
