package com.company;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    public static void printStatus(PriorityQueue<Task> ready, Task running, int time) {

        System.out.println("State at t = " + time);
        System.out.println("Ready Queue: [");
        for(Task t : ready) {
            System.out.println(String.format("    Task {name: %s, type: %s, state: %s, duration: %d}",
                    t.getName(), t.getType(), t.getState(), t.getDuration()));
        }
        System.out.println("]");
        System.out.println("Running Task:");
        System.out.println(String.format("    Task {name: %s, type: %s, state: %s, duration: %d}",
                running.getName(), running.getType(), running.getState(), running.getDuration()));
        System.out.println("===");
    }

    public static void sjf(Task[] tasks) {
        PriorityQueue<Task> ready = new PriorityQueue<Task>(tasks.length, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.getDuration() - o2.getDuration();
            }
        });

        ready.addAll(Arrays.asList(tasks));

        int t = 0;

        while(!ready.isEmpty()) {
            Task task = ready.poll();
            task.setState(Task.RUNNING);
            while(task.getDuration() != 0) {
                task.setDuration(task.getDuration()-1);
                printStatus(ready, task, t);
                t += 1;
            }
            task.setState(Task.TERMINATED);
        }
    }

    public static void fcfs(Task[] tasks) {
        PriorityQueue<Task> ready = new PriorityQueue<Task>(tasks.length, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.getOrder() - o2.getOrder();
            }
        });

        ready.addAll(Arrays.asList(tasks));

        int t = 0;

        while(!ready.isEmpty()) {
            Task task = ready.poll();
            task.setState(Task.RUNNING);
            while(task.getDuration() != 0) {
                task.setDuration(task.getDuration()-1);
                printStatus(ready, task, t);
                t += 1;
            }
            task.setState(Task.TERMINATED);
        }
    }

    public static void main(String[] args) {
        System.out.println("OS Scheduling project, Student# 9822762211");
        System.out.println("How many tasks?");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int taskCount = Integer.parseInt(input);
        Task[] tasks = new Task[taskCount];
        System.out.println("Enter your tasks:");
        for(int i = 0; i < taskCount; ++i) {
//            System.out.println("Enter task " + (i+1) + ":");
            String input1 = sc.nextLine();
            String[] splittedInput = input1.split(" ");
            tasks[i] = new Task(splittedInput[0], splittedInput[1], Integer.parseInt(splittedInput[2]), i);
        }
//        sjf(tasks);
        fcfs(tasks);
    }
}
