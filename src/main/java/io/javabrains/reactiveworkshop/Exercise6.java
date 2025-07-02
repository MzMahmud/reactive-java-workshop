package io.javabrains.reactiveworkshop;

import java.io.IOException;
import java.time.Duration;

public class Exercise6 {


    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.unresponsiveFlux() and ReactiveSources.unresponsiveMono()

        // Get the value from the Mono into a String variable but give up after 5 seconds
        var val = ReactiveSources.unresponsiveMono().block(Duration.ofSeconds(5));
        System.out.println(val);

        // Get the value from unresponsiveFlux into a String list but give up after 5 seconds
        // Come back and do this when you've learnt about operators!
        var list = ReactiveSources.unresponsiveFlux()
                .collectList()
                .block(Duration.ofSeconds(5));
        System.out.println(list);

        System.out.println("Press a key to end");
        System.in.read();
    }

}
