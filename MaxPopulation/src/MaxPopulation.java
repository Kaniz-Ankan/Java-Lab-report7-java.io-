import java.io.*;

class NegativeNumberException extends Exception {
    public NegativeNumberException(String message) {
        super(message);
    }
}

public class MaxPopulation {
    public static void main(String[] args) {
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            writer = new BufferedWriter(new FileWriter("MaxPopulation/output.txt"));

            String line;
            String maxCity = "";
            int maxPopulation = -1;

            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(" ");
                    String city = parts[0];
                    int population = Integer.parseInt(parts[1]);

                    if (population < 0) {
                        throw new NegativeNumberException("Negative population found for " + city);
                    }

                    if (population > maxPopulation) {
                        maxPopulation = population;
                        maxCity = city;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Invalid population format in line: " + line);
                } catch (NegativeNumberException e) {
                    System.out.println(e.getMessage());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Invalid data format for line: " + line);
                }
            }

            if (!maxCity.isEmpty()) {
                writer.write("City with Maximum Population: " + maxCity + " (" + maxPopulation + ")");
            } else {
                writer.write("No valid data found.");
            }

            System.out.println("Maximum population data written successfully.");

        } catch (FileNotFoundException e) {
            System.out.println("Input file not found.");
        } catch (IOException e) {
            System.out.println("Error reading or writing file: " + e.getMessage());
        } finally {
            try {
                if (reader != null) reader.close();
                if (writer != null) writer.close();
            } catch (IOException e) {
                System.out.println("Error closing files.");
            }
        }
    }
}
