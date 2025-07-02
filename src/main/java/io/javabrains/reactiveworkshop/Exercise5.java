package io.javabrains.reactiveworkshop;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

import java.io.IOException;

public class Exercise5 {

    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumberMono() and ReactiveSources.userMono()

        // Subscribe to a flux using the error and completion hooks
        ReactiveSources.intNumberMono()
                .subscribe(
                        System.out::println,
                        (err) -> System.err.println(err.getMessage()),
                        () -> System.out.println("Done")
                );
        // Subscribe to a flux using an implementation of BaseSubscriber
        ReactiveSources.intNumberMono()
                .subscribe(new ConsolePrinter<>());

        System.out.println("Press a key to end");
        System.in.read();
    }

    static class ConsolePrinter<T> extends BaseSubscriber<T> {
        @Override
        protected void hookOnSubscribe(Subscription subscription) {
            System.out.println("Subscribed!");
            request(1);
        }

        @Override
        protected void hookOnNext(T value) {
            System.out.println("Got value:" + value);
            request(1);
        }

        @Override
        protected void hookOnComplete() {
            System.out.println("Done!");
        }

        @Override
        protected void hookOnError(Throwable throwable) {
            System.out.println("Error: " + throwable.getMessage());
        }
    }
}