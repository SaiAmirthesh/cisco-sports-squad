package input;

import java.util.Scanner;

public final class SelectionReader {

    private SelectionReader() {
    }

    public static int[] readSelection(Scanner scanner, int expectedLength) {
        if (!scanner.hasNextLine()) {
            throw new IllegalArgumentException(
                    "Please enter exactly " + expectedLength + " values."
            );
        }

        String line = scanner.nextLine();
        Scanner lineScanner = new Scanner(line);
        int[] selection = new int[expectedLength];

        for (int i = 0; i < selection.length; i++) {
            if (!lineScanner.hasNext()) {
                lineScanner.close();
                throw new IllegalArgumentException(
                        "Please enter exactly " + expectedLength + " values."
                );
            }

            int value;
            String token = lineScanner.next();

            try {
                value = Integer.parseInt(token);
            } catch (NumberFormatException ex) {
                lineScanner.close();
                throw new IllegalArgumentException(
                        "Selection must contain only integer values."
                );
            }

            if (value != 0 && value != 1) {
                lineScanner.close();
                throw new IllegalArgumentException(
                        "Selection values must be 0 or 1."
                );
            }

            selection[i] = value;
        }

        if (lineScanner.hasNext()) {
            lineScanner.close();
            throw new IllegalArgumentException(
                    "Selection must contain exactly " + expectedLength + " values."
            );
        }

        lineScanner.close();
        return selection;
    }
}
