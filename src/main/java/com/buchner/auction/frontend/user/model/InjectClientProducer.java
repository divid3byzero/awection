package com.buchner.auction.frontend.user.model;

import javax.enterprise.inject.Produces;

public class InjectClientProducer {

    public InjectClientProducer() {

    }

    @Produces
    public String produceString() {

        return "I'm an injected string.";
    }
}
