/*
Main.java

COMP 1020 Section A03
INSTRUCTOR      Bryan Wodi
ASSIGNMENT      Assignment 3
AUTHOR          Daniel La Rocque
VERSION         March 20, 2020

PURPOSE         Operate an airline
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void readInputFile(String fileName, Airline airline) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName)); // open a buffered reader
            String line = br.readLine();
            while (line != null) { // while there is a line to read in the file
                line = line.trim(); // trim the line
                if (line.charAt(0) == '#') { // if we're reading a comment
                    processComment(line); // process the comment
                } else {
                    String[] tokens = line.split(" "); // create an array of strings containing every part of the
                                                       // command
                    try {
                        parseCommand(tokens, airline); // process the command in the given line
                    } catch (InvalidInputException iie) {
                        System.out.println(iie.getMessage());
                    }
                }
                line = br.readLine(); // read the following line in the file
            }
            br.close(); // close the bufferedreader
        } catch (FileNotFoundException fnf) { // fnf in case the file does not exist
            fnf.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        /*
         * This method reads a given text file and processes the commands or commends
         * written in the text file using the methods below. We use a loop to read the
         * file line by line while the line that were reading contains the something, we
         * then use if statements to check whether we're reading a comment or a command
         * and then process it.
         */
    }

    public static void parseCommand(String[] tokens, Airline air) throws InvalidInputException {

        // analyze all of the following commands using their methods
        // checking this by using .equals(Command name)
        if (tokens[0].equals("CREATE-FLIGHT")) {
            analyzeCreateFlight(tokens, air);
        } else if (tokens[0].equals("GET-FLIGHT")) {
            analyzeGetFlight(tokens, air);
        } else if (tokens[0].equals("CREATE-PAYLOAD")) {
            analyzeCreatePayload(tokens, air);
        } else if (tokens[0].equals("GET-PAYLOAD")) {
            analyzeGetPayload(tokens, air);
        } else if (tokens[0].equals("ASSIGN-PAYLOAD")) {
            analyzeAssignPayload(tokens, air);
        } else if (tokens[0].equals("TAKEOFF")) {
            analyzeTakeoff(tokens, air);
        } else {
            throw new InvalidInputException("No command found."); // if none of the commands are given
        }
        /*
         * This read a line where a commmand is given and performs whatever action the
         * command written is supposed to do using methods for each command. We use if
         * statements for each command to find out what command was intended to be used.
         */
    }

    public static void processComment(String data) {
        System.out.println(data.substring(2)); // print out the comment that was given, after the '#'
    }

    public static void analyzeCreateFlight(String[] tokens, Airline air) throws InvalidInputException {
        char type = tokens[1].charAt(0); // determine the type of flight to be created
        System.out.println(air.addFlight(type)); // add the flight to the airline and print out that we have done so
    }

    public static void analyzeGetFlight(String[] tokens, Airline air) throws InvalidInputException {
        try {
            int id = Integer.parseInt(tokens[1]); // turn the given ID into an integer if possible
            if (air.getFlight(id) == null) { // if the flight does not exist
                System.out.println("The Flight with ID: " + id + " does not exist on that Airline"); // tell the user
                                                                                                     // that it does not
                                                                                                     // exist
            } else { // if the flight does exist on the airline
                System.out.println(air.getFlight(id)); // print out the flight
            }
        } catch (NumberFormatException nfe) {
            throw new InvalidInputException("The ID given is incorrectly formatted."); // if the parseInt does not work
        }
    }

    public static void analyzeCreatePayload(String[] tokens, Airline air) throws InvalidInputException {
        String payloadType, weight, firstName, lastName, job; // create strings needed to create a payload
        if (tokens[1].equals("C") || tokens[1].equals("P") || tokens[1].equals("E")) {
            payloadType = tokens[1]; // assign a payload type
            if (tokens[2] != null) {
                weight = tokens[2]; // assign a weight
                if (!(payloadType.equals("C")) && tokens[3] != null && tokens[4] != null) { // if were not creating
                                                                                            // Cargo
                    firstName = tokens[3]; // assign a first name
                    lastName = tokens[4]; // assign a last name
                    if (payloadType.equals("E")) { // if we're creating an employee
                        if (tokens[5] == null) { // if we're not given their job
                            throw new InvalidInputException(
                                    "There was no job assigned to the employee in the CREATE-PAYLOAD command.");
                        } else {
                            job = tokens[5]; // assign their job
                        }
                    } else {
                        job = null; // if theyre not an employee, they dont have a job
                    }
                } else { // if they are cargo, it doesnt have a name or job
                    firstName = null;
                    lastName = null;
                    job = null;
                }
                // create the payload using Payload payloadFactory()
                Payload payloadCreated = Payload.payloadFactory(payloadType, weight, firstName, lastName, job);
                air.addPayload(payloadCreated); // add the payload we created to the airline
                System.out.println(payloadCreated); // print out the payload we added
            } else {
                throw new InvalidInputException("Parameters in the CREATE-PAYLOAD command are incorrect.");
            }
        } else {
            throw new InvalidInputException("Payload type in the CREATE-PAYLOAD command is incorrect.");
        }
        /*
         * This method analyzes the create payload command, if all the given input in
         * the tokens[] array is valid, then we create a payload using the
         * payloadFactory() with the given data in the .txt file and add it to the
         * airline, then print the data of the payload we just created. If any of the
         * data given was incorrectly formatted, we throw an exception and don't create
         * a payload.
         */
    }

    public static void analyzeGetPayload(String[] tokens, Airline air) throws InvalidInputException {
        if (tokens[1] != null) { // if there is data in the first token
            try {
                int id = Integer.parseInt(tokens[1]); // turn the id into an integer
                if (air.getPayload(id) == null) { // if the payload with the id does not exist
                    throw new InvalidInputException("There is no Payload with the ID :" + id);
                } else {
                    if (id >= 300000000 && id < 400000000) { // if id stars with a 3
                        if (!(air.getPayload(id).isCargo)) { // and is not a cargo
                            System.out.println(air.getPayload(id)); // print out the person
                        } else {
                            throw new InvalidInputException("That Person Payload does not exist on that airline.");
                        }

                    } else if (id >= 400000000 && id < 500000000) { // if id starts with a 4
                        if (air.getPayload(id).isCargo) { // and is a cargo
                            System.out.println(air.getPayload(id)); // print out the payload
                        } else {
                            throw new InvalidInputException("That Cargo Payload does not exist on that airline.");
                        }
                    }
                }
            } catch (InvalidInputException iie) {
                System.out.println(iie.getMessage());
            }
        }
        /*
         * This method is used to print out a payload with a given id when the
         * getpayload command is used in the .txt file. We check to see what kind of
         * payload it is, if the payload is valid, we print out the payload
         */
    }

    public static void analyzeAssignPayload(String[] tokens, Airline air) throws InvalidInputException {
        if (tokens[1] != null && tokens[2] != null) { // if we're given data
            try {
                int flightID = Integer.parseInt(tokens[1]); // turn the id's into ints
                int payloadID = Integer.parseInt(tokens[2]);
                if (air.getFlight(flightID) == null) // if the flight does not exist
                    throw new InvalidInputException("The Flight " + flightID + " does not exist");
                if (air.getPayload(payloadID) == null) // if the payload does not exist
                    throw new InvalidInputException("The Payload " + payloadID + " does not exist");
                try {
                    air.getFlight(flightID).book(air.getPayload(payloadID)); // try booking the payload
                } catch (InvalidBookingException ibe) {
                    System.out.println(ibe.getMessage()); // print out the booking exception if there was one
                }
            } catch (NumberFormatException nfe) {
                throw new InvalidInputException("ID's must be given as numerical values.");
            }
        } else {
            throw new InvalidInputException("Incorrect Formatting of the ASSIGN-PAYLOAD command");
        }
        /*
         * This method takes in a flight and a payload id, checks if they are both valid
         * id's and are not null, then tries to book the payload on the flight if
         * possible. If the book() method throws an exception due to the payload not
         * being valid to add to the given flight, we print out the reason into the
         * console.
         */
    }

    public static void analyzeTakeoff(String[] tokens, Airline air) throws InvalidInputException {
        try {
            int flightID = Integer.parseInt(tokens[1]); // turn the id into a int
            if (air.getFlight(flightID) == null) // if the flight with that id does not exist
                throw new InvalidInputException("The Flight " + flightID + " does not e xist");
            System.out.println(air.getFlight(flightID).doTakeoff()); // if it does exist, print of the doTakeOff string
                                                                     // returned
        } catch (NumberFormatException nfe) {
            throw new InvalidInputException("The flight ID was incorrectly formatted");
        }
        /*
         * If the flight with the given id exists, we print out the string we get from
         * the doTakeOff() method saying whether or not the flight was able to take off.
         */
    }

}