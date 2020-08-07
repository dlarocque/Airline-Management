/*
Payload.java

COMP 1020 Section A03
INSTRUCTOR      Bryan Wodi
ASSIGNMENT      Assignment 3
AUTHOR          Daniel La Rocque
VERSION         March 20, 2020

PURPOSE         Operate an airline
*/

public abstract class Payload extends Item {

	protected boolean isCargo;

	protected double payloadWeight = 0;

	public Payload() { //Payload constructor
	}

	public Payload(double wgt) {  //Payload constructor
		payloadWeight = wgt;		//Assign the payload weight
	}

	public double getWeight() {
		return payloadWeight;	//return the weight of this payload
	}

	public String toString() {
		String data = "ID: " + id + ", Weight: ";
		String weight = Integer.toString((int) payloadWeight);
		for (int i = 0; i < 4 - weight.length(); i++) {
			data += " ";
		}
		data += weight + " kg";

		return data;
		/*
		Returns the data of the payload in a readable string 
		so that it can be printed in the console for the user
		to be able to understand.
		*/
	}

	public static Payload payloadFactory(String payloadType, String weight, String firstName, String lastName,
			String job) throws InvalidInputException {
		Payload newPayload = null;	//Payload to create
		double payloadWeight = 0;
		try {
			payloadWeight = Double.parseDouble(weight); //turn the weight string into an integer
		} catch (NumberFormatException nfe) {			//catch exception in case it isnt an integer
			throw new InvalidInputException("The Weight given was incorrectly formatted.");
		}
		if (payloadType.equals("P")) {	//if person
			newPayload = new Person(firstName, lastName, 300000000 + totalItems++, payloadWeight); //create person
		} else if (payloadType.equals("E")) {	//if employee
			newPayload = new Employee(firstName, lastName, job, 300000000 + totalItems++, payloadWeight); //create employee
		} else if (payloadType.equals("C")) {	//if cargo
			newPayload = new Cargo(payloadWeight);	//create cargo
		} else {	//if none of the above, the input is incorrect.
			throw new InvalidInputException("Create Payload Command is Incorrect");
		}
		return newPayload;	//return the payload we created, or null if there was a problem
		/*
		This method is used to create a payload given Strings that are given
		the data we want to give to the new payload we're creating.  We use if statements
		to determine what kind of payload we want to create, and then we create the payload
		using the correct constructors and passing in the strings we were given to the constructors.
		We return an invalidinputexception if the data we were passed was incorrect
		*/
	}

}
