package tasks.Week1Task;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TawfeekShalashStreamsExample {

    public static void main(final String[] args) {

        List<Author> authors = Library.getAuthors();
        
        banner("Authors information");
        // TODO With functional interfaces declared
//        Consumer<Author> printer = System.out::println;
//        authors
//            .forEach(printer);

        // TODO With functional interfaces used directly
        authors
            .forEach(System.out::println);

        banner("Active authors");
        // TODO With functional interfaces declared
//        Consumer<Author> printer = System.out::println;
//        Predicate<Author> filter=user->user.active;
//        authors
//                .stream()
//                .filter(filter)
//                .forEach(printer);


        // TODO With functional interfaces used directly
        authors
                .stream()
                .filter(user->user.active)
                .forEach(System.out::println);

        banner("Active books for all authors");
        // TODO With functional interfaces declared
//        Function<Author,Stream<Book>>functionInsideFlatMap=author->author.getBooks().stream();
//        Predicate<Book> filter=book->book.published;
//        Consumer<Book> printer = System.out::println;
//        authors
//                .stream()
//                .flatMap(functionInsideFlatMap)
//                .filter(filter)
//                .forEach(printer);


        // TODO With functional interfaces used directly
        authors
                .stream()
                .flatMap(author->author.getBooks().stream())
                .filter(book->book.published)
                .forEach(System.out::println);

        banner("Average price for all books in the library");
        // TODO With functional interfaces declared
//        Function<Author,Stream<Book>> functionInsideFlatMap=author->author.getBooks().stream();
//        Function<Book, Integer> functionInsideMapToInt= book->book.price;
//        int result=authors.stream()
//                .flatMap(functionInsideFlatMap)
//                .mapToInt((ToIntFunction<? super Book>) functionInsideMapToInt)
//                .reduce(0,(sum,price)->(sum+price)/2);
//        System.out.println(result);
        // TODO With functional interfaces used directly

        System.out.println(authors
                .stream()
                .flatMap(author->author.getBooks().stream())
                .mapToInt(book->book.price)
                .reduce(0,(sum,price)->(sum+price)/2)); //calculate the Average price for all books in the library

        banner("Active authors that have at least one published book");
        // TODO With functional interfaces declared
//        Predicate<Author> filterBooks=author->{
//            for(Book book:author.getBooks()){
//                if(book.published)
//                    return true;
//            }
//            return false;
//        };
//        authors.stream().filter(filterBooks).forEach(System.out::println);

        // TODO With functional interfaces used directly
        authors.stream().filter(author->author.active).filter(author->{

            for(Book book:author.getBooks()){
                if(book.published)
                    return true;
            }
            return false;
        }).forEach(System.out::println);

    }

    private static void banner(final String m) {
        System.out.println("#### " + m + " ####");
    }
}


class Library {
    public static List<Author> getAuthors() {
        return Arrays.asList(
            new Author("Author A", true, Arrays.asList(
                new Book("A1", 100, true),
                new Book("A2", 200, true),
                new Book("A3", 220, true))),
            new Author("Author B", true, Arrays.asList(
                new Book("B1", 80, true),
                new Book("B2", 80, false),
                new Book("B3", 190, true),
                new Book("B4", 210, true))),
            new Author("Author C", true, Arrays.asList(
                new Book("C1", 110, true),
                new Book("C2", 120, false),
                new Book("C3", 130, true))),
            new Author("Author D", false, Arrays.asList(
                new Book("D1", 200, true),
                new Book("D2", 300, false))),
            new Author("Author X", true, Collections.emptyList()));
    }
}

class Author {
    String name;
    boolean active;
    List<Book> books;

    Author(String name, boolean active, List<Book> books) {
        this.name = name;
        this.active = active;
        this.books = books;
    }

    public List<Book> getBooks(){
        return books;
    }

    @Override
    public String toString() {
        return name + "\t| " + (active ? "Active" : "Inactive");
    }
}

class Book {
    String name;
    int price;
    boolean published;

    Book(String name, int price, boolean published) {
        this.name = name;
        this.price = price;
        this.published = published;
    }

    @Override
    public String toString() {
        return name + "\t| " + "\t| $" + price + "\t| " + (published ? "Published" : "Unpublished");
    }
}
