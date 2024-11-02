package src;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print(Constants.TASK_CLI);
            String line = input.nextLine();
            if (line.isEmpty())
                continue;
            if (line.equals(Constants.EXIT))
                break;

            TaskProcessor.processInputs(line);
        }
    }}
