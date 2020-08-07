/*
Commercial.java

COMP 1020 Section A03
INSTRUCTOR      Bryan Wodi
ASSIGNMENT      Assignment 3
AUTHOR          Daniel La Rocque
VERSION         March 20, 2020

PURPOSE         Operate an airline
*/

public class Commercial extends Flight {
    public Commercial() { // commercial flight constructor
        id = 100000000 + totalItems++; // assign the id
        isCommercial = true;
    }

    public void book(Payload payload) throws InvalidBookingException {
        if (validPayload(payload)) // if the payload is valid to add to the flight
            payloads[payloadAmount++] = payload; // add the payload to the array of payloads the flight is holding
    }

    public boolean validPayload(Payload payload) throws InvalidBookingException {
        boolean valid = true;
        if (payload.getWeight() > MAX_WEIGHT) // if the payload is too heavy
            throw new InvalidBookingException("This payload is too heavy.");
        if (payloadAmount > MAX_PAYLOAD) // if the flight is full
            throw new InvalidBookingException("This Flight is full. Can not add any more payloads.");
        if (!(payload instanceof Person)) // if the payload isnt a person
            throw new InvalidBookingException("Can only book Persons for Commercial flights");
        return valid; // if we get to the return statement, the payload is valid
        /*
         * This method tells the book method if the payload we want to book is valid to
         * add to the flight, we use if statements to check each of the conditions, if
         * one of the if statements is true, we throw an exception, and the payload will
         * not be booked on the flight
         */
    }
}