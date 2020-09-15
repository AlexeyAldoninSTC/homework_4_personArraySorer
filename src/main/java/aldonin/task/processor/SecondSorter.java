package aldonin.task.processor;

import aldonin.task.domain.Person;
import aldonin.task.domain.Sex;
import aldonin.task.fuction.PersonArraySorter;
import aldonin.task.util.PersonUtil;

public class SecondSorter implements PersonArraySorter {

    @Override
    public void sort(Person[] people) {
        int womenCount = countWomen(people);
        splitBySex(people);
        sortByAge(people,0, people.length - womenCount - 1);
        sortByAge(people, people.length - womenCount, people.length - 1);
        sortByName(people,0, people.length - womenCount - 1);
        sortByName(people, people.length - womenCount, people.length - 1);

    }

    private void splitBySex(Person[] people) {
        for (int i = 0; i < people.length; i++) {
            if (people[i].getSex() != Sex.MAN) {
                for (int j = i + 1; j < people.length; j++) {
                    if (people[j].getSex() == Sex.MAN) {
                        swap(people, i, j);
                        break;
                    }
                }
            }
        }
    }

    private int countWomen(Person[] people) {
        int counter = 0;
        for (Person person : people) {
            if (person.getSex() == Sex.WOMAN) {
                counter++;
            }
        }
        return counter;
    }

    private void swap(Person[] people, int i, int j) {
        Person temp = people[i];
        people[i] = people[j];
        people[j] = temp;
    }

    private void sortByAge(Person[] people, int minIndex, int maxIndex) {
        for (int i = minIndex; i <= maxIndex; i++) {
            int youngestAge = getOneWithLowestValue(people, i, maxIndex);
            for (int j = i; j <= maxIndex; j++) {
                if (people[j].getAge() == youngestAge){
                    swap(people, i, j);
                    break;
                }
            }
        }
    }

    private int getOneWithLowestValue(Person[] people, int minIndex, int maxIndex) {
        int youngestAge = 100;
        for (int i = minIndex; i <= maxIndex; i++) {
            if (people[i].getAge() < youngestAge) {
                youngestAge = people[i].getAge();
            }
        }
        return youngestAge;
    }

    private void sortByName(Person[] people, int leftIndex, int rightIndex) {
        int i = leftIndex;
        while (i <= rightIndex) {
            int ageMatchCounter = 0;
            while (i + ageMatchCounter < rightIndex
                    && people[i].getAge() == people[i + ageMatchCounter + 1].getAge()) {
                ageMatchCounter++;
            }
            if (ageMatchCounter != 0) {
                sortByNameDespiteAge(people, i, ageMatchCounter);
            }
            i += ageMatchCounter + 1;
        }
    }

    private void sortByNameDespiteAge(Person[] people, int leftIndex, int ageMatchCounter) {
        int rightIndex = leftIndex + ageMatchCounter;
        for (int i = leftIndex; i <= rightIndex; i++) {
            String minName = getNameMinValue(people, i, ageMatchCounter - (i - leftIndex));
            for (int j = i; j <= rightIndex; j++) {
                if (people[j].getName().equals(minName)) {
                    swap(people, i, j);
                    break;
                }
            }
        }
    }

    private String getNameMinValue(Person[] people, int leftIndex, int nameMatchCounter) {
        String minName = "" + PersonUtil.RIGHT_CHAR_LIMIT;
        for (int j = leftIndex; j <= leftIndex + nameMatchCounter; j++) {
            if (people[j].getName().compareTo(minName) < 0){
                minName = people[j].getName();
            }
        }
        return minName;
    }
}
