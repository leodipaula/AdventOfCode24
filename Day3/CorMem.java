package Day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CorMem {
    public static void main(String[] args) throws IOException {
        var lines = getInput(Paths.get("Day3\\input.txt"));
        var numbers = sumOfProducts(lines);
        System.out.println("Part1: " + numbers);
        var numbersEnables = sumOfEnabledProducts(lines);
        System.out.println("Part2: " + numbersEnables);
    }

    private static List<String> getInput(Path input) throws IOException {
        return Files.readAllLines(input);
    }

    private static int sumOfProducts(List<String> lines) {
        int totalSum = 0;

        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");

        for (var line : lines) {
            Matcher matcher = pattern.matcher(line);

            while (matcher.find()) {
                int n1 = Integer.parseInt(matcher.group(1));
                int n2 = Integer.parseInt(matcher.group(2));

                totalSum += n1 * n2;
            }
        }

        return totalSum;
    }

    private static int sumOfEnabledProducts(List<String> lines) {
        int totalSum = 0;
        boolean isEnabled = true;

        Pattern instructionPattern = Pattern.compile("do\\(\\)|don't\\(\\)|mul\\((\\d+),(\\d+)\\)");

        for (var line : lines) {
            Matcher matcher = instructionPattern.matcher(line);

            while (matcher.find()) {
                if (matcher.group(0).equals("do()")) {
                    isEnabled = true;
                } else if (matcher.group(0).equals("don't()")) {
                    isEnabled = false;
                } else if (isEnabled && matcher.group(1) != null && matcher.group(2) != null) {
                    int n1 = Integer.parseInt(matcher.group(1));
                    int n2 = Integer.parseInt(matcher.group(2));
                    totalSum += n1 * n2;
                }
            }
        }

        return totalSum;
    }
}
