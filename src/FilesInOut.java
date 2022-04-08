import java.io.*;
import java.util.*;
import java.io.FileNotFoundException;

/**
 * CSCU9T4
 * Student id 2932358
 */
public class FilesInOut {

    /**
     * Based on the value of capitalize, this method decides whether to capitalize a given text
     * @param text
     * @param capitalize
     * @return
     */
    public static String capitalization(String text, boolean capitalize) {
        if (capitalize) {
            // capitalize all
            return text.toUpperCase();
        } else {
            // capitalize just first letter
            return text.substring(0, 1).toUpperCase() + text.substring(1);
        }
    }

    public static void main(String[] args) {

        File input;
        PrintWriter writingOutput;
        boolean capitalize;

        try {
            // selecting input files
            System.out.println("Please supply filename for input:");
            Scanner userInput = new Scanner(System.in);
            String inputFileName = userInput.nextLine();
            // selecting output files
            System.out.println("Please supply filename for output:");
            String outputFileName = userInput.nextLine();
            // close scanner
            userInput.close();

            try {
                // use user inputs to find the correspondent files
                File inputFile = new File(inputFileName);
                Scanner inFile = new Scanner(inputFile);
                File outputFile = new File(outputFileName);
                Scanner outFile = new Scanner(outputFile);
                // close scanner
                inFile.close();
                outFile.close();
            }
            catch (IOException e) {
                System.err.println("IOException: " + e.getMessage()
                        + " not found");
            }

            // argument selection
            for (int i = 0; i < args.length; i++) {
                input = new File(inputFileName);
                writingOutput = new PrintWriter(outputFileName);

                // look if one of the arguments is starts with -u, like "-u input.txt"
                if (args[i].equalsIgnoreCase("-u "+ inputFileName)) {
                    // based on the -u flag, we decide whether to capitalize all the text or not
                    capitalize = true;
                }
                else {
                    capitalize = false;
                }
                // scanner the lines in the input file
                Scanner sc = new Scanner(input);
                while (sc.hasNextLine()) {
                    String lines = sc.nextLine();
                    String space = " ";
                    // print the lines in the terminal
                    System.out.println(lines);
                    // splits lines into tokens. Saves them in an array
                    String[] tokens = lines.split(space);

                    // look at all the tokens
                    for (int j = 0; j < tokens.length; j++) {
                        // if we have name, surname and date (3 tokens)
                        if (tokens.length == 3) {
                            // capitalize first letter name and surname
                            if (j == 0 || j == 1) {
                                tokens[j] = capitalization(tokens[j], capitalize);
                            } else {
                                // format data
                                tokens[j] = tokens[j].substring(0, 2) + "/" + tokens[j].substring(2, 4) + "/" + tokens[j].substring(4);
                            }
                        }
                        // if we have also the middle name initial, we have 4 tokens
                        else if (tokens.length == 4) {
                            // capitalize first letter name and surname
                            if (j == 0 || j == 2) {
                                tokens[j] = capitalization(tokens[j], capitalize);
                            }
                            // capitalize middle name initial and add dot
                            else if (j == 1) {
                                tokens[j] = tokens[j].toUpperCase() + ".";
                            } else {
                                // format data
                                tokens[j] = tokens[j].substring(0, 2) + "/" + tokens[j].substring(2, 4) + "/" + tokens[j].substring(4);
                            }
                        }
                    }
                    String outputLine = "";
                    if (tokens.length == 3) {
                        outputLine = outputLine.concat(tokens[0] + "\t\t" + tokens[1] + "\t" + tokens[2]);
                    } else if (tokens.length == 4) {
                        outputLine = outputLine.concat(tokens[0] + "\t" + tokens[1] + "\t" + tokens[2] + "\t" + tokens[3]);
                    }
                    // write the text in the output file
                    writingOutput.println(outputLine);
                }
                writingOutput.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }// main
}// FilesInOut
