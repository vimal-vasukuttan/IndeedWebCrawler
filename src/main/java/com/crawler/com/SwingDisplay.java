package com.crawler.com;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SwingDisplay {
	
	public static void display(List<JobDetails> jobDetails) {
		  int size = jobDetails.size();
		  Object [][] rowData = new Object[size][5];
		  for(int i = 0 ; i < size ; i++) {
			  rowData[i][0] = jobDetails.get(i).getJobTitle();
			  rowData[i][1] = jobDetails.get(i).getCompanyName();
			  rowData[i][2] = jobDetails.get(i).getLocation();
			  rowData[i][3] = jobDetails.get(i).getDateOfPostingSince();
			  rowData[i][4] = jobDetails.get(i).getDetailsLink();
		  }
	    	JFrame myFrame = new JFrame ( "Indeed Data retriver " ) ;   
	    	myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            Object columnNames[] = { "Job Title", "Company Name", "Location", "Available", "Link"};
	            JTable table = new JTable(rowData, columnNames);
	            JScrollPane scrollPane = new JScrollPane(table);
	            myFrame.add(scrollPane, BorderLayout.CENTER);
	            myFrame.setSize ( 500 , 500 ) ; // We show our frame 
	            myFrame.setVisible ( true ) ; 
	}

}
