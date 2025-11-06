import java.io.*;

class NegativeNumberException extends Exception {
    public NegativeNumberException(String message) {
        super(message);
    }
}

public class FootballResult {
    public static void main(String[] args) {
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            writer = new BufferedWriter(new FileWriter("FootballResult/output.txt"));

            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(" ");
                    String team1 = parts[0];
                    int score1 = Integer.parseInt(parts[1]);
                    String team2 = parts[2];
                    int score2 = Integer.parseInt(parts[3]);

                    if (score1 < 0 || score2 < 0) {
                        throw new NegativeNumberException("Negative score found in match: " + line);
                    }

                    if (score1 > score2) {
                        writer.write("Winner: " + team1 + " | Goals: " + score1);
                    } else if (score2 > score1) {
                        writer.write("Winner: " + team2 + " | Goals: " + score2);
                    } else {
                        writer.write("Match Drawn");
                    }
                    writer.newLine();

                } catch (NumberFormatException e) {
                    System.out.println("Invalid score format in line: " + line);
                } catch (NegativeNumberException e) {
                    System.out.println(e.getMessage());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Invalid data format for line: " + line);
                }
            }

            System.out.println("Match result file created successfully.");

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
