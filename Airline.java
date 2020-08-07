/*
Airline.java

COMP 1020 Section A03
INSTRUCTOR      Bryan Wodi
ASSIGNMENT      Assignment 3
AUTHOR          Daniel La Rocque
VERSION         March 20, 2020

PURPOSE         Operate an airline
*/

public class Airline {

    private Flight[] allFlights = new Flight[100];
    private Payload[] allPayloads = new Payload[100];
    private static int amountOfFlights = 0;
    private static int amountOfPayloads = 0;

    public Airline() { // airline constructor
    }

    public String addFlight(char type) throws InvalidInputException {
        String status; // String to return
        if (type == 'C') { // if we're adding a commerical flight
            allFlights[amountOfFlights++] = new Commercial(); // add the new commercial flight to array of flight at
                                                              // next pos
            status = "Commercial Flight " + allFlights[amountOfFlights - 1].id + " has a Payload size of 0"; // update
                                                                                                             // status
        } else if (type == 'I') { // same thing but for industrial flights
            allFlights[amountOfFlights++] = new Industrial();
            status = "Industrial Flight " + allFlights[amountOfFlights - 1].id + " has a Payload size of 0";
        } else { // if the type isnt 'I' or 'C' the command is incorrect
            throw new InvalidInputException("Flight creation command is incorrect");
        }
        return status; // return the status giving the data of the flight
        /*
         * This method is used to add a flight to the airline. We check what type of
         * flight we want to be added to the airline, create the respective flight and
         * then add that array to the array of flights
         */
    }

    public Flight getFlight(int id) {
        Flight flightWithID = null;
        if (id < 200000000 && id >= 100000000) { // if the id given starts with a 1,
            if (allFlights[id - 100000000].isCommercial) // we check if the flight with that id is commercial
                flightWithID = allFlights[id - 100000000]; // if it is, return that flight
        } else if (id < 300000000 && id >= 200000000) { // if the id starts with a 2
            if (!(allFlights[id - 200000000].isCommercial)) // if the flight with that id is not commercial
                flightWithID = allFlights[id - 200000000]; // return that flight
        }
        return flightWithID; // return the flight if it was valid, null if it wasnt
        /*
         * We want to return the flight with the id we were given if it exists and it is
         * the correct type of flight. If its not correct, we return null
         */
    }

    public void addPayload(Payload thePayload) {
        allPayloads[amountOfFlights + (amountOfPayloads++)] = thePayload; // add the given payload to the array of
                                                                          // paylaods
    }

    public Payload getPayload(int id) {
        int minCargoID = 400000000;
        int minPersonID = 300000000;
        Payload payloadToGet = null;
        if (id >= minPersonID && id < minCargoID) { // person
            payloadToGet = allPayloads[id - minPersonID]; // return the person
        } else if (id >= minCargoID && id < 500000000) { // cargo
            payloadToGet = allPayloads[id - minCargoID]; // return the cargo
        }
        return payloadToGet; // return the payload with the given id
        /*
         * This method returns the payload with the given id using the allPayloads array
         */
    }
}