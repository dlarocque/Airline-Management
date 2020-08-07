/*
Industrial.java

COMP 1020 Section A03
INSTRUCTOR      Bryan Wodi
ASSIGNMENT      Assignment 3
AUTHOR          Daniel La Rocque
VERSION         March 20, 2020

PURPOSE         Operate an airline
*/

public class Industrial extends Flight {
    // valid if payload is cargo or employee
    public Industrial() { // Industrial flight constructor
        // Set the flights ID
        id = 200000000 + totalItems++;
        isCommercial = false;
    }

    public void book(Payload payload) throws InvalidBookingException {
        if (validPayload(payload)) // if the paylaod is valid to book on the flight
            payloads[payloadAmount++] = payload; // add it to the array of payloads
    }

    public boolean validPayload(Payload payload) throws InvalidBookingException {
        boolean valid = true;
        if (payload.getWeight() > MAX_WEIGHT) // if the payload is too heavy
            throw new InvalidBookingException("This payload is too heavy.");
        if (payloadAmount > MAX_PAYLOAD) // if the flight is full
            throw new InvalidBookingException("This Flight is full. Can not add any more payloads.");
        if (!(payload instanceof Cargo || payload instanceof Employee)) // if the payload is cargo
            throw new InvalidBookingException("Can only book Persons for Commercial flights");
        return valid; // return true if none of the if statements were true
        /*
         * This method tells the book method whether or not the payload is valid to be
         * added to the flight based on whether or not the conditions have been passed.
         * If one of the conditions fail, we dont return anything because an exception
         * is thrown saying what condition didnt pass
         */
    }
}
