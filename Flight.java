/*
Flight.java

COMP 1020 Section A03
INSTRUCTOR      Bryan Wodi
ASSIGNMENT      Assignment 3
AUTHOR          Daniel La Rocque
VERSION         March 20, 2020

PURPOSE         Operate an airline
*/

public abstract class Flight extends Item {

    protected final int MAX_WEIGHT = 200; // max weight of a payload
    protected final int MAX_PAYLOAD = 100;// max amount of payloads
    protected Payload[] payloads = new Payload[MAX_PAYLOAD]; // all the payloads
    protected int payloadAmount; // amount of payloads a flight currently holds
    protected boolean hasTakenOff = false;
    public boolean isCommercial;

    public Flight() { // Flight constructor
        payloadAmount = 0; // flight starts with 0 paylaods
    }

    public void book(Payload payload) throws InvalidBookingException {
        // to be overwritten in other classes
    }

    public boolean validPayload(Payload payload) throws InvalidBookingException {
        boolean validPayload = false; // payload is initially invalid
        if (payload.getWeight() < MAX_WEIGHT) { // if the payload has a valid weight
            if (payloadAmount < MAX_PAYLOAD) { // if there flight isnt full
                validPayload = true;          // the payload is valid
            } else {
                throw new InvalidBookingException("This Flight is full. Can not add any more payloads.");
            }
        } else {
            throw new InvalidBookingException("This payload is too heavy.");
        }
        return validPayload; //return whether the payload is valid or not
        /*
        This method tells us whether or not adding a payload to this fight
        is valid or not based on the conditions we need to fulfill.
        We use nested if statements with respective else statements that throw invalid
        booking exceptions.  If the conditions are met, we return that the payload
        is valid to add to the flight.
        */
    }

    public boolean canTakeoff() {
        boolean canTakeOff = false; //flight is initially unable to take off
        if (!(hasTakenOff) && payloadAmount >= 2 && hasEmployee())  //if the flight has all the conditions needed to take off
            canTakeOff = true;  //the flight can take off
        return canTakeOff;      //return whether or not the flight can take off
        /*
        This method tells us whether or not the flight can take off or not
        based on whether it matches the conditions needed for a flight to be able to take off.
        This method is used in the takeOff method to determine which string to print.
        We use the hasEmployee method to determine whether or not we have an employee
        on the flight.
        */
    }

    public boolean hasEmployee() {
        boolean hasEmployee = false; //initially does not have employee
        for (int i = 0; i < payloadAmount; i++) {   //go through all of the payloads in the flight
            if (payloads[i] instanceof Employee)    //check if that payload is an employee
                hasEmployee = true;                 //if it is, the flight has an employee
        }
        return hasEmployee;                         //return whether or not there was an employee in the flight
        /*
        This method is only used in the canTakeoff() method to determine if the 
        flight can take off, because we need to have an employee for the flight
        to be able to take off.  We use a loop to check the instanceof every payload
        to find an employee and return true if we found one.
        */
    }

    public String doTakeoff() {
        String takeOff = "Flight " + id + " can not take off";  //flight can initially not take off
        if (canTakeoff()) { //if the flight canTakeOff()
            hasTakenOff = true; //the flight hasTakenOff
            takeOff = "Flight " + id + " takes off at the speed of light!"; //Return this string indicating it has taken off
        }
        return takeOff;
        /*
        This method returns a string based on whether or not a flight can take off.
        If the flight canTakeOff, we return a string saying the flight has taken off.
        */
    }

    public String toString() {
        String data = null;
        if (id < 200000000 && id >= 100000000) // if the id starts with a 1, its commerical
            data = "Commercial flight " + id + " has a payload size of " + payloadAmount; //return a string with the flight data
        else if (id < 300000000 && id >= 200000000) { //if the id starts with a 2 its industrial
            data = "Industrial flight " + id + " has a payload size of " + payloadAmount;   //return a string with the flight data
        }
        return data;
    }

}