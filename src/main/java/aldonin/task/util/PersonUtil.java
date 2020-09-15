package aldonin.task.util;

import aldonin.task.domain.Person;
import aldonin.task.domain.Sex;
import aldonin.task.exception.PersonInfoMatchException;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class PersonUtil {
    private static final int NAME_LENGTH = 10;
    private static final char LEFT_CHAR_LIMIT = 'а';
    public static final char RIGHT_CHAR_LIMIT = 'я';

    /**
     * Method to generate an array filled with Person instances
     * with randomly generated fields values
     * @param quantity - required quantity of Person entities
     * @return - filled array
     */
    public static Person[] generateRandomPersonArray(int quantity) {
        Person[] people = new Person[quantity];
        Random random = new Random();
        for (int i = 0; i < quantity; i++) {
            int age = random.nextInt(100);
            String name = generateRandomName();
            Sex sex = Sex.values()[random.nextInt(Sex.values().length)];
            people[i] = new Person(age, name, sex);
        }
        return people;
    }

    /**
     * Method to generate bounded names by collecting random chars generated via Random
     * @return - generated random name
     */
    private static String generateRandomName(){
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < NAME_LENGTH; i++) {
            int letter = LEFT_CHAR_LIMIT + (int)
                    (random.nextFloat() * (RIGHT_CHAR_LIMIT - LEFT_CHAR_LIMIT + 1));
            builder.append((char) letter);
        }
        return builder.toString();
    }

    /**
     * Method to check possible Age and Name matches within array of Persons
     * @param people - array to be checked
     * @throws PersonInfoMatchException - in case of match found
     */
    public static void checkAgeAndNameMatch(Person[] people) throws PersonInfoMatchException {
        Set<Person> personSet = new HashSet<>();
        for (Person person : people) {
            personSet.add(
                    new Person(person.getAge(), person.getName(), Sex.MAN));
        }
        if (personSet.size() != people.length) {
            throw new PersonInfoMatchException("Совпадение о имени и возрасту среди значений");
        }
    }
}
