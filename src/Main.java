package src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true) {
            String line = input.nextLine();
            if (line.isEmpty()) {
                break;
            }

            TaskProcessor.processInputs(line);
        }
    }}
