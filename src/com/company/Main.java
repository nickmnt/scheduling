package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("OS Scheduling project, Student# 9822762211");
        System.out.println("How many tasks?");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int taskCount = Integer.parseInt(input);
        Task[] tasks = new Task[taskCount];
        for(int i = 0; i < taskCount; ++i) {
            String input1 = sc.nextLine();
            String[] splittedInput = input1.split(" ");
            tasks[i] = new Task(splittedInput[0], splittedInput[1], Integer.parseInt(splittedInput[2]));
        }
        
    }
}
