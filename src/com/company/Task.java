package com.company;

public class Task {
    public static final String RUNNING = "running";
    public static final String READY = "ready";
    public static final String TERMINATED = "terminated";

    private String name;
    private String type;
    private int duration;
    private String state = READY;
    private int order;

    public Task(String name, String type, int duration) {
        this.name = name;
        this.type = type;
        this.duration = duration;
    }

    public Task(String name, String type, int duration, int order) {
        this.name = name;
        this.type = type;
        this.duration = duration;
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
