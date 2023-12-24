package com.writer;
public class OutsidePrintjob extends Printjob {
    public OutsidePrintjob(String uName, int uPriority, int nPages) {
        super(uName, uPriority, nPages);
    }

    @Override
    public void composeJobDetails() {
        super.composeJobDetails();
        double cost = nPages * 0.10; // 10 cents per page
        System.out.println("Total Cost< $" + cost+">");
    }
}