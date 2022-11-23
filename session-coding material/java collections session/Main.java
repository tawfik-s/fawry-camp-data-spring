import java.util.*;

public class Main {
    public static void main(String[] args) {
        Collection<User> persons = new TreeSet<>();

        User ahmed = new User("Ahmed", 10);

        persons.add(ahmed);
        persons.add(new User("Ali", 8));
        persons.add(new User("Mohammed", 5));
        persons.add(new User("Ali", 8));
        persons.add(new User("Osama", 5));
        persons.add(ahmed);

        for(User user : persons) {
            System.out.println(user);
        }

//        Collections.sort(persons, new UserAgeDescComparator());

//        System.out.println(":::::::::::: AFTER SORT");
//
//        for(User user : persons) {
//            System.out.println(user);
//        }

        LinkedList<String> list = new LinkedList<>();
        list.offer("A");
        list.offer("B");
        list.offer("C");
        list.offer("D");
        list.offer("E");

        System.out.println(list.pollFirst());
        System.out.println(list.pollLast());


        System.out.println("===========");

        Map<String, User> phoneDirectory = new TreeMap<>();

        phoneDirectory.put("010", new User("Ahmed", 10));
        phoneDirectory.put("011", new User("Mohammed", 10));
        phoneDirectory.put("012", new User("Ali", 10));
        phoneDirectory.put("013", new User("Osama", 10));

        System.out.println(phoneDirectory.get("011"));


        for(String key : phoneDirectory.keySet()) {
            System.out.println(key + " : " + phoneDirectory.get(key));
        }

        for(Map.Entry entry : phoneDirectory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

    }


    static class UserAgeAscComparator implements Comparator<User> {

        @Override
        public int compare(User o1, User o2) {
            return o1.age - o2.age;
        }
    }

    static class UserAgeDescComparator implements Comparator<User> {

        @Override
        public int compare(User o1, User o2) {
            return o2.age - o1.age;
        }
    }


    static class User implements Comparable<User> {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            User otherUser = (User) obj;
            System.out.println("EQUALS " + this + " === " + otherUser);
            return otherUser.getName().equals(this.name);
        }

        @Override
        public String toString() {
            return "User name: " + name + " and age: " + age;
        }

        @Override
        public int compareTo(User other) {
            return this.age - other.age;
        }
    }
}