package org.example;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
public class InclusionExclusionCalculator {
    private static final int LIMIT = 100000;
    private static final String LOG_FILE_PATH = "equation.log";
    public static void main(String[] args) {
        List<Double> values = new ArrayList<>();
        System.out.printf("Enter up to %d double values separated by comma (e.g. 2.0,4.0,6.0): %n", LIMIT);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String input = reader.readLine();
            // Parse input values into an arraylist of doubles, limiting to 100,000
            for (String value : input.split(",")) {
                if (values.size() < LIMIT) {
                    values.add(Double.parseDouble(value));
                } else {
                    System.out.printf("The number of input is more than %d.%n", LIMIT);
                    return;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading input: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid double values.");
        }

            // Ensure the input has at least one value
        if (values.isEmpty()) {
            System.out.println("No input values provided.");
            return;
        }

        // Build and calculate the equation
        StringBuilder equationBuilder = new StringBuilder();
        BigDecimal result = calculateInclusionExclusion(values, equationBuilder);

        // Print the full equation and result
        System.out.println("Calculated Result: " + String.format("%.1f", result));
        logToFile(reorderEquation(equationBuilder.toString()), LOG_FILE_PATH);
    }

    /**
     * Calculates the inclusion-exclusion equation and builds the full equation string.
     *
     * @param values List of input double values
     * @param equationBuilder StringBuilder to store the expanded equation
     * @return The calculated result of the equation
     */
    private static BigDecimal calculateInclusionExclusion(List<Double> values, StringBuilder equationBuilder) {
        BigDecimal result = BigDecimal.ZERO;
        int n = values.size();

        // Iterate over all non-empty subsets using bitmasking for subset generation
        // which avoids recursion and ensures good performance.
        for (int mask = 1; mask < (1 << n); mask++) {
            List<Double> subset = new ArrayList<>();
            BigDecimal product = BigDecimal.ONE;

            // Build the subset and calculate the product of its elements
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    subset.add(values.get(i));
                    product = product.multiply(new BigDecimal(values.get(i)));
                }
            }

            // Determine the sign based on the subset size (odd: +, even: -)
            boolean isOddSubset = subset.size() % 2 != 0;
            result = result.add(isOddSubset ? product : product.negate()); // Add or subtract product

            // Build the equation part for the current subset
            if (equationBuilder.length() > 0) {
                equationBuilder.append(" ");
            }
            equationBuilder.append(isOddSubset ? "+ (" : "- (");
            equationBuilder.append(subset.stream()
                            .map(Object::toString)
                            .collect(Collectors.joining(" × ")))
                    .append(")");
        }
        return result;
    }

    /**
     * Reorders the equation parts from smallest to largest based on their number of elements multiplied.
     *
     * @param equation The original equation string
     * @return The reordered equation string
     */
    private static String reorderEquation(String equation) {
        //remove the first two redundant char
        equation = equation.substring(2);
        // Split the equation into parts by ')'
        String[] parts = equation.split("\\)");
        List<String> equationParts = new ArrayList<>();

        // Trim each part and add the closing parenthesis back
        for (String part : parts) {
            if (!part.trim().isEmpty()) {
                equationParts.add(part.trim() + ")");
            }
        }
        // Sort parts based on the number of factors
        equationParts.sort(Comparator.comparingInt(part -> part.split("×").length));
        // Reconstruct the equation
        return String.join(" ", equationParts);
    }

    private static void logToFile(String reorderedEquation, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(reorderedEquation);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}