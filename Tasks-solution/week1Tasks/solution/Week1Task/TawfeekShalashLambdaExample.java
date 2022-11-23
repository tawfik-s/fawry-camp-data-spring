package tasks.Week1Task;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class TawfeekShalashLambdaExample {

    public static void main(final String[] args) {

        final UsersRepository repository = new UsersRepository();

        banner("Listing all users");
        repository.select(null, null);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        banner("Listing users with age > 5 sorted by name anonymous objects");
        repository.select(new Predicate<User>() {
            @Override
            public boolean test(User user) {
                return user.age > 5;
            }
        }, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
               return o1.name.compareTo(o2.name);
            }
        });
        banner("Listing users with age > 5 sorted by name lambda expressions");
        Comparator<User> comp=(user1,user2)-> user1.name.compareTo(user2.name);
        Predicate<User> filter=user->user.age>5;
        repository.select(filter,comp);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        banner("Listing users with age < 10 sorted by age anonymous objects");
        repository.select(new Predicate<User>() {
            @Override
            public boolean test(User user) {
                return user.age < 10;
            }
        }, new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                if(user1.age>user2.age)
                    return 1;
                else if(user1.age<user2.age)
                    return -1;
                else
                    return 0;
            }
        });

        banner("Listing users with age < 10 sorted by age lambda expressions");
        comp=(user1,user2)-> {
            if(user1.age>user2.age)
                return 1;
            else if(user1.age<user2.age)
                    return -1;
            else
                return 0;
        };
        filter=user->user.age<10;
        repository.select(filter,comp);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        banner("Listing active users sorted by name anonymous objects");
        repository.select(new Predicate<User>() {
            @Override
            public boolean test(User user) {
                return user.active;
            }
        }, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.name.compareTo(o2.name);
            }
        });


        banner("Listing active users sorted by name lambda expressions");
        comp=(user1,user2)-> user1.name.compareTo(user2.name);
        filter=user->user.active;
        repository.select(filter,comp);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        banner("Listing active users with age > 8 sorted by name anonymous objects");
        repository.select(new Predicate<User>() {
            @Override
            public boolean test(User user) {
                return user.age > 8&&user.active;
            }
        }, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.name.compareTo(o2.name);
            }
        });



        banner("Listing active users with age > 8 sorted by name lambda expressions");
        comp=(user1,user2)-> user1.name.compareTo(user2.name);
        filter=user->user.active&&user.age>8;
        repository.select(filter,comp);

    }

    private static void banner(final String m) {
        System.out.println("#### " + m + " ####");
    }
    
}

class UsersRepository {
    List<User> users;

    UsersRepository() {
        users = Arrays.asList(
            new User("Seven", 7, true),
            new User("Four", 4, false),
            new User("Eleven", 11, true),
            new User("Three", 3, true),
            new User("Nine", 9, false),
            new User("One", 1, true),
            new User("Twelve", 12, true));
    }

    void select(final Predicate<User> filter, final Comparator<User> order) {
        Stream<User> usersStream = users.stream();

        if (filter != null) {
            usersStream = usersStream.filter(filter);
        }
        if (order != null) {
            usersStream = usersStream.sorted(order);
        }
        usersStream.forEach(System.out::println);
    }
}

class User {
    String name;
    int age;
    boolean active;

    User(final String name, final int age, boolean active) {
        this.name = name;
        this.age = age;
        this.active = active;
    }

    @Override
    public String toString() {
        return name + "\t| " + age;
    }
}