package com.writer;
import java.util.PriorityQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Printer{
	private PriorityQueue<Composejob> composeJobQueue;
	public Publisher() {
		composeJobQueue = new PriorityQueue<>();
    }

    public void readAndAddComposeJobsFromDataFile(String sourceFileName) {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(sourceFileName))) {
            String fline;
            while ((fline = fileReader.readLine()) != null) {
                String[] dataParts = fline.split("\t");
                String uName = dataParts[0];
                int uPriority = Integer.parseInt(dataParts[1]);
                int nPages = Integer.parseInt(dataParts[2]);
                String composeJobType = dataParts[3];

                if (composeJobType.equals("I")) {
                	composeJobQueue.add(new Composejob(uName, uPriority, nPages));
                } else if (composeJobQueue.equals("O")) {
                	composeJobQueue.add(new OutsideComposejob(uName, uPriority, nPages));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public void composeJobs() {
        while (!composeJobQueue.isEmpty()) {
            Composejob job = composeJobQueue.poll();
            job.composeJobDetails();
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Printer printer = new Printer();
        printer.readAndAddJobsFromFile("input.txt");
        printer.printJobs();
    }
}
}

