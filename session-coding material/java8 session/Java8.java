package java8;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.*;

public class Java8 {

    public static void main(String[] args) {

        List<Person> persons = getPersons();

        forEachMethodInIterableInterface(persons);

        defaultAndStaticMethodsInInterfaces();

        functionalInterfacesAndLambdaExpressions(persons);

        streamApiForBulkDataOperationsOnCollections(persons);
    }

    private static void streamApiForBulkDataOperationsOnCollections(List<Person> persons) {
        System.out.println("streamApiForBulkDataOperationsOnCollections");

        // stream operators inputs
            // Function (input and output)
            // Predicate (input and boolean output)
            // Consumer (input and no output)
            // Supplier (no input but return output)
            // BiFunction (two inputs and one output)

        // map
            // names of persons with age grater than 8
        persons
                .stream()
                .filter(person -> person.getAge() > 8)
                .map(Person::getName)
                .forEach(System.out::println);

        // names of persons with age grater than 8 with no duplicates
        persons
                .stream()
                .filter(person -> person.getAge() > 8)
                .map(Person::getName)
                .distinct()
                .forEach(System.out::println);

        // sorting
        List<Person> sorted = persons
                .stream()
                .sorted(Comparator.comparing(Person::getAge))
                .collect(Collectors.toList());

        // filter
        persons
                .stream()
                .filter(person -> person.getAge() > 8)
                .forEach(System.out::println);

        // flatMapping
            // print persons phone numbers
        persons
                .stream()
                .flatMap(person -> person.getPhones().stream())
                .forEach(System.out::println);

        // count
            // count of persons with age equal 8
        long count = persons
                .stream()
                .filter(person -> person.getAge() == 8)
                .mapToInt(Person::getAge)
                .count();

        // avg
          // the average of persons with age less than 8
        double avg = persons
                .stream()
                .filter(person -> person.getAge() < 8)
                .mapToInt(Person::getAge)
                .average()
                .orElse(0);

        // matching (any, all, no)
            // is there any person with name start with 'A' ?
        boolean match = persons
                .stream()
                .noneMatch(person -> person.getName().startsWith("A"));
        
        // reduce
        Integer sumOfAges = persons
                .stream()
                .map(Person::getAge)
                .reduce(Integer::sum)
                .orElse(0);


        Integer sumOfAgesNoOptional = persons
                .stream()
                .map(Person::getAge)
                .reduce(0, Integer::sum);

        // collecting
        // persons by age

        Map<Integer, List<Person>> personsByAge = persons
                .stream()
                .collect(Collectors.groupingBy(Person::getAge));

        // names by age
        persons
                .stream()
                .collect(Collectors.groupingBy(Person::getAge, Collectors.mapping(Person::getName, Collectors.toSet())));

        // with optional (check reduce and avg as they return Optional)

        // concat and lambda combination
        Stream<Integer> stream1 = Stream.of(1, 3, 5);
        Stream<Integer> stream2 = Stream.of(2, 4, 6);
        Stream<Integer> stream3 = Stream.of(18, 15, 36);

        Stream<Integer> resultingStream = Stream.concat(
                Stream.concat(stream1, stream2), stream3);

        resultingStream
                .forEach(System.out::println);

        // lambda combination
        Predicate<Person> personsWithAge8 = person -> person.getAge() == 8;
        Predicate<Person> startWithA = person -> person.getName().startsWith("A");
        Consumer<Person> printAge = person -> System.out.println(person.getAge());
        Consumer<Person> printName = person -> System.out.println(person.getName());

        Consumer<Person> printer = person -> {
            printAge.accept(person);
            printName.accept(person);
        };

        persons
                .stream()
                .filter(personsWithAge8.or(startWithA))
                .forEach(printer);
    }

    private static void functionalInterfacesAndLambdaExpressions(List<Person> persons) {
        System.out.println("functionalInterfacesAndLambdaExpressions");
        // anonymous class
        new Consumer<Person>() {
            @Override
            public void accept(Person o1) {
                System.out.println(o1.toString());
            }
        };
        persons.forEach( new Consumer<Person>() {
            @Override
            public void accept(Person o1) {
                System.out.println(o1.toString());
            }
        });

        // lambda
        Consumer<Person> print = person -> System.out.println(person.toString());
        persons.forEach(person -> System.out.println(person.toString()));
        persons.forEach(print);

        // method reference
         // 1. to static method (ClassName::methodName)
            persons.forEach(person -> Person.sayHello(person));
            persons.forEach(Person::sayHello);

         // 2. to instance method (ClassInstance::methodName)
        persons.forEach(Person::getName);

         // 3. to instance method of an Object of a given type (String::isEmpty)
        persons.stream()
                .map(Person::getName)
                .filter(String::isEmpty)
                .collect(Collectors.toList());

         // 4. to a constructor (ClassName::new)
        List<Integer> newAges = persons.stream()
                .map(Person::getAge)
                .map(Integer::new)
                .collect(Collectors.toList());
    }

    private static void defaultAndStaticMethodsInInterfaces() {
        System.out.println("defaultAndStaticMethodsInInterfaces");
        Comparator<Person> comparator = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return 0;
            }
        };

        // default methods
        comparator.reversed();

        // static methods
        Comparator.naturalOrder();
    }

    private static void forEachMethodInIterableInterface(List<Person> persons) {
        System.out.println("forEachMethodInIterableInterface");
        // before
        for (int index = 0; index < persons.size(); index++) {
            System.out.println(persons.get(index));
        }

        for (Person person : persons) {
            System.out.println(person);
        }

        // after
        persons.forEach(person -> System.out.println(person.toString()));
    }

    private static List<Person> getPersons() {
        List<Person> persons = new ArrayList<>();

        persons.add(new Person("Ahmed", 10));
        persons.add(new Person("Mostafa", 8, Arrays.asList("123455", "1345569")));
        persons.add(new Person("Mohammed", 5, Collections.singletonList("14552366")));
        persons.add(new Person("Osman", 8));
        persons.add(new Person("Omar", 5));
        persons.add(new Person("Zayd", 13));
        return persons;
    }

    static class Person implements Comparable<Person> {
        private final String name;
        private final int age;
        private List<String> phones = new ArrayList<>();

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Person(String name, int age, List<String> phones) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public List<String> getPhones() {
            return this.phones;
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            Person otherPerson = (Person) obj;
            System.out.println("EQUALS " + this + " === " + otherPerson);
            return otherPerson.getName().equals(this.name);
        }

        @Override
        public String toString() {
            return "User name: " + name + " and age: " + age;
        }

        @Override
        public int compareTo(Person other) {
            return this.age - other.age;
        }

        public static void sayHello(Person person) {
            System.out.println("Hello: " + person.getName());
        }
    }
}
