package expressions;

//Notes:

//For the moment, will not consider subtract, divide and power
//Will do nothing for the methods related to power
//Will not consider the possibility of '-' in sum and '/' in product

import java.io.*;

public class Main {
	private static String prompt = ">";

	public static void printOut(String outputString) {
		System.out.println(makePromptString(outputString));
	}

	public static String makePromptString(String outputString) {
		if (outputString.charAt(0)=='(') {
			outputString = outputString.substring(1, outputString.length() - 1);
		}
		return prompt + outputString;
	}

	public static void main(final String[] args) {
		Expression exp = null;
		try {
			printOut("Welcome to Expressivo! \nEnter your expression below.\n"
					+ "Once you have done that you may start entering your commands with \'!\'.\nPress enter twice to quit");
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			String inputex = "";
			int leave = 0;
			while (true) {
				String line;
				System.out.print(prompt);
				line = bufferRead.readLine();
				
				
				if (line != null && line.length() == 0) {
					leave += 1;
					if (leave == 2) {
						printOut("Goodbye!");
						break;
					}
				} else {

					if (line != null && line.startsWith("!d/d")) {
//						System.out.println("inputex="+inputex);
						if (exp == null) {
							printOut("Please enter an expression first");
						} else {
							String var = line.replaceFirst("!d/d", "");
							try {
								if(inputex.contains(var) == false) {
									System.out.println("Sorry, I just don't understand..");
								} else {
									String diffExp = Commands.differentiate(exp, var);
									printOut(diffExp);
								}
							} catch (AssertionError ae) {
								printOut("Your variable is not in the right format.");
							}
						}
					} else if (line != null && line.startsWith("!simplify")) {
//						System.out.println("inputex="+inputex);
						if (exp == null) {
							printOut("Please enter an expression first");
						} else {
							try {
								int flag = 1;
								String vars = line.replaceFirst("!simplify\\s*", "");
								String[] varsArray = vars.split("=[0-9]+\\s*");
								for(int i=0; i<varsArray.length; i++) {
//									System.out.println("varsArray[i]: "+ varsArray[i]);
									if(inputex.contains(varsArray[i]) == false) {
										System.out.println("Sorry, I just don't understand..");
										flag = 0;
										break;
									} 
								}
								if(flag == 1) {
									String simpExp = Commands.simplify(exp, vars);
									printOut(simpExp);
								}

							} catch (AssertionError ae) {
								printOut("Your variables and values are not in the right format.");
							}
						}
					} else {
						try {
							inputex = line;
							exp = Expression.parse(line);
							printOut(exp.toString());
						} catch (ParseError pe) {
							printOut(pe.getMessage());
						}
					}

				}
			}

		} catch (IOException ex) {
			// ex.printStackTrace();
		}
	}

}
