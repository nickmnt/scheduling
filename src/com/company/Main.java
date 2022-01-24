package com.company;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void printStatus(PriorityQueue<Task> ready, Task running, int time) {

        System.out.println("State at t = " + time);
        System.out.println("Ready Queue: [");
        for(Task t : ready) {
            System.out.println(String.format("    Task {name: %s, type: %s, state: %s, timeWithCpu: %d, duration: %d}",
                    t.getName(), t.getType(), t.getState(), t.getTimeWithCPU(), t.getDuration()));
        }
        System.out.println("]");
        System.out.println("Running Task:");
        System.out.println(ANSI_GREEN + String.format("    Task {name: %s, type: %s, state: %s, timeWithCpu: %d, duration: %d}",
                running.getName(), running.getType(), running.getState(), running.getTimeWithCPU(), running.getDuration())
                + ANSI_RESET);
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
                task.setTimeWithCPU(task.getTimeWithCPU()+1);
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
                task.setTimeWithCPU(task.getTimeWithCPU()+1);
                printStatus(ready, task, t);
                t += 1;
            }
            task.setState(Task.TERMINATED);
        }
    }

    public static void roundRobin(Task[] tasks, int quantum) {
        int newOrder = tasks.length;

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
            int initialDuration = task.getDuration();
            int possibleRemaining = Math.max(0, initialDuration - quantum);
            while(task.getDuration() != possibleRemaining) {
                task.setDuration(task.getDuration()-1);
                task.setTimeWithCPU(task.getTimeWithCPU()+1);
                printStatus(ready, task, t);
                t += 1;
            }

            if(task.getDuration() == 0) {
                task.setState(Task.TERMINATED);
            } else {
                task.setOrder(newOrder);
                newOrder += 1;
                ready.add(task);
            }
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
//        fcfs(tasks);
        roundRobin(tasks, 1);
    }
}
