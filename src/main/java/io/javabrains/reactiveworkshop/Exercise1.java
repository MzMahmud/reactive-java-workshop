package io.javabrains.reactiveworkshop;

import java.util.Objects;
import java.util.stream.Collectors;

public class Exercise1 {

    public static void main(String[] args) {

        // Use StreamSources.intNumbersStream() and StreamSources.userStream()

        // Print all numbers in the intNumbersStream stream
        StreamSources.intNumbersStream()
                     .forEach(System.out::println);

        // Print numbers from intNumbersStream that are less than 5
        StreamSources.intNumbersStream()
                     .filter(n -> n < 5)
                     .forEach(System.out::println);

        // Print the second and third numbers in intNumbersStream that's greater than 5
        StreamSources.intNumbersStream()
                     .filter(n -> n > 5)
                     .skip(1)
                     .limit(2)
                     .forEach(System.out::println);

        // Print the first number in intNumbersStream that's greater than 5.
        // If nothing is found, print -1
        var firstNumberGreaterThan5OrMinus1 =
                StreamSources.intNumbersStream()
                             .filter(n -> n > 5)
                             .findFirst()
                             .orElse(-1);
        System.out.println(firstNumberGreaterThan5OrMinus1);

        // Print first names of all users in userStream
        StreamSources.userStream()
                     .map(User::getFirstName)
                     .forEach(System.out::println);

        // Print first names in userStream for users that have IDs from number stream
        // flatMap
        StreamSources.intNumbersStream()
                     .flatMap(id -> StreamSources.userStream().filter(user -> Objects.equals(user.getId(), id)))
                     .map(User::getFirstName)
                     .forEach(System.out::println);

        // anyMatch
        StreamSources.userStream()
                     .filter(user -> StreamSources.intNumbersStream().anyMatch(id -> Objects.equals(id, user.getId())))
                     .map(User::getFirstName)
                     .forEach(System.out::println);

        // optimized with idSet
        var idSet = StreamSources.intNumbersStream()
                                 .collect(Collectors.toUnmodifiableSet());
        StreamSources.userStream()
                     .filter(user -> idSet.contains(user.getId()))
                     .map(User::getFirstName)
                     .forEach(System.out::println);
    }

}
