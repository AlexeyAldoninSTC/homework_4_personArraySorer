package aldonin.task.app;

import aldonin.task.domain.Person;
import aldonin.task.exception.PersonInfoMatchException;
import aldonin.task.processor.PersonArraySorter;
import aldonin.task.processor.FirstSorter;
import aldonin.task.processor.SecondSorter;
import aldonin.task.util.PersonUtil;

public class Main {
    public static void main(String[] args) {

        Person[] people = PersonUtil.generateRandomPersonArray(1000000);
        try {
            PersonUtil.checkAgeAndNameMatch(people);
        } catch (PersonInfoMatchException e) {
            System.out.println(e.getMessage());
        }
        long timeBefore = System.currentTimeMillis();
        PersonArraySorter sorter = new FirstSorter();
        sorter.sort(people);
        long timeAfter = System.currentTimeMillis();
        try {
            PersonUtil.checkAgeAndNameMatch(people);
        } catch (PersonInfoMatchException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("FirstSorter  = " + (timeAfter - timeBefore));

        Person[] people1 = PersonUtil.generateRandomPersonArray(1000000);

        long timeBefore1 = System.currentTimeMillis();
        PersonArraySorter sorter1 = new SecondSorter();
        sorter.sort(people1);
        long timeAfter1 = System.currentTimeMillis();
        System.out.println("SecondSorter = " + (timeAfter1 - timeBefore1));
        try {
            PersonUtil.checkAgeAndNameMatch(people1);
        } catch (PersonInfoMatchException e) {
            e.printStackTrace();
        }
    }
}
