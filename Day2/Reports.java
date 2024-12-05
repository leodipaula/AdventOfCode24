package Day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reports {
    public static void main(String[] args) {
        try {
            var lines = getInput(Paths.get("input.txt"));
            var reports = listOfReports(lines);
            safeReports(reports);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    private static List<String> getInput(Path input) throws IOException {
        return Files.readAllLines(input);
    }

    private static List<List<Integer>> listOfReports(List<String> lines) {
        List<List<Integer>> result = new ArrayList<>();

        for (var line : lines) {
            List<Integer> numbers = Arrays.stream(line.trim().split("\\s+"))
                    .map(Integer::parseInt)
                    .toList();
            result.add(numbers);
        }

        return result;
    }

    public static boolean isSafeReport(List<Integer> report) {
        boolean isIncreasing = report.get(1) > report.get(0);

        for (int i = 1; i < report.size(); i++) {
            int diff = report.get(i) - report.get(i - 1);

            if (Math.abs(diff) < 1 || Math.abs(diff) > 3)
                return false;

            if ((isIncreasing && diff < 0) || (!isIncreasing && diff > 0))
                return false;
        }

        return true;
    }

    private static boolean isSafeReportPart2(List<Integer> report) {
        if (isSafeReport(report))
            return true;

        for (int i = 0; i < report.size(); i++) {
            List<Integer> modifiedReport = new ArrayList<>(report);
            modifiedReport.remove(i);

            if (isSafeReport(modifiedReport))
                return true;
        }

        return false;
    }

    private static void safeReports(List<List<Integer>> reports) {
        int result = 0;
        int result2 = 0;
        for (var report : reports) {
            if (isSafeReport(report))
                result++;
            if (isSafeReportPart2(new ArrayList<>(report)))
                result2++;
        }
        System.out.println("part1: " + result + "\npart2: " + result2);
    }
}