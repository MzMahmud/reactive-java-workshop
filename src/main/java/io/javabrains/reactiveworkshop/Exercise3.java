package io.javabrains.reactiveworkshop;

import java.io.IOException;
import java.util.stream.Collectors;

public class Exercise3 {

    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumbersFlux()

        // Get all numbers in the ReactiveSources.intNumbersFlux stream
        // into a List and print the list and its size
        ReactiveSources.intNumbersFlux()
                       .collect(Collectors.toUnmodifiableList())
                       .subscribe(integers -> {
                           System.out.println(integers);
                           System.out.println(integers.size());
                       });
        System.out.println("Press a key to end");
        System.in.read();
    }

}
