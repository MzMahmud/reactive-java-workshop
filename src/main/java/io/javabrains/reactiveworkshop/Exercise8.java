package io.javabrains.reactiveworkshop;

import reactor.core.publisher.Flux;

import java.io.IOException;

public class Exercise8 {


    public static void main(String[] args) throws IOException {
        // Use ReactiveSources.intNumbersFluxWithException()

        // Print values from intNumbersFluxWithException and print a message when error happens
        ReactiveSources
                .intNumbersFluxWithException()
                .subscribe(
                        System.out::println,
                        err -> System.out.println(err.getMessage())
                );

        // Print values from intNumbersFluxWithException and continue on errors
        ReactiveSources
                .intNumbersFluxWithException()
                .onErrorContinue((err, t) -> System.out.println("error: " + err.getMessage() + " value: " + t))
                .subscribe(System.out::println);

        // Print values from intNumbersFluxWithException and when errors
        // happen, replace with a fallback sequence of -1 and -2
        ReactiveSources
                .intNumbersFluxWithException()
                .onErrorResume((err) -> Flux.just(-1, -2))
                .doOnComplete(() -> System.out.println("Done"))
                .subscribe(System.out::println);
        System.out.println("Press a key to end");
        System.in.read();
    }

}
