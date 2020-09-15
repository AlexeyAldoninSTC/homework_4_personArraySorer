package aldonin.task.domain;

import aldonin.task.exception.PersonInfoMatchException;

import java.util.Objects;

public class Person implements Comparable{
    private final int age;
    private final String name;
    private final Sex sex;

    public Person(int age, String name, Sex sex) {
        this.age = age;
        this.name = name;
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public Sex getSex() {
        return sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name) &&
                sex == person.sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name, sex);
    }

    @Override
    public String toString() {
        return "{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Person that = (Person) o;
        if (this.getName().equals(that.getName()) && this.age == that.age) {
            try {
                throw new PersonInfoMatchException("Совпадение имён и возраста:" + this.toString() + ", " + that.toString());
            } catch (PersonInfoMatchException e) {
                System.out.println(e.getMessage());
            }
        }
        if (this.getSex() != that.getSex()){
            return this.getSex().ordinal() - that.getSex().ordinal();
        }
        if (this.getAge() != that.getAge()) {
            return this.getAge() - that.getAge();
        }
        return this.getName().compareTo(that.getName());
    }
}
