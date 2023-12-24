package com.writer;

public class Printjob implements Comparable<Composejob> {
    private String uName;
    private int uPriority;
    protected int nPages;

    public Printjob(String uName, int uPriority, int nPages) {
        this.uName = uName;
        this.uPriority = uPriority;
        this.nPages = nPages;
    }

    public int getComposePriority() {
        return uPriority * nPages;
    }

    @Override
    public int compareTo(Composejob other) {
        return Integer.compare(this.getComposePriority(), other.getComposePriority());
    }

    public void composeJobDetails() {
        System.out.println("User Name<" + uName+">");
        System.out.println("User Priority<" + uPriority+">");
        System.out.println("Number of Pages<" + nPages+">");
    }
}

