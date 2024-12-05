package Day1;

import java.util.*;
import java.io.*;

public class DistanceCalculator {

    public static void main(String[] args) throws IOException {
        String filePath = "Day1\\input.txt";

        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                leftList.add(Integer.parseInt(parts[0]));
                rightList.add(Integer.parseInt(parts[1]));
            }
        }

        int totalDistance = calculateTotalDistance(leftList, rightList);
        System.out.println("Total distance: " + totalDistance);

        int totalMultiplies = multiplyRepetitions(leftList, rightList);
        System.out.println("Result of multiplies: " + totalMultiplies);
    }

    private static int calculateTotalDistance(List<Integer> left, List<Integer> right) {
        Collections.sort(left);
        Collections.sort(right);

        int totalDistance = 0;
        for (int i = 0; i < left.size(); i++) {
            totalDistance += Math.abs(left.get(i) - right.get(i));
        }
        return totalDistance;
    }

    private static int multiplyRepetitions(List<Integer> left, List<Integer> right) {
        int result = 0;
        for (int i = 0; i < left.size(); i++) {
            int currentValue = left.get(i);
            long repetitions = right.stream()
                    .filter(n -> n == currentValue)
                    .count();
            result += currentValue * repetitions;
        }
        return result;
    }

}