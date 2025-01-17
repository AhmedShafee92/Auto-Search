package search.job.gui;

import javax.swing.*;

import first.option.forsendcv.FirstSearchOpation;

import java.awt.*;
import java.awt.event.*;

public class ButtonFlipExample 
{
    public static void main(String[] args) 
    {
        // Create a JFrame
        JFrame frame = new JFrame("Button Flip Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new FlowLayout());

        // Create the "Go" button
        JButton jobSearchButtonOption1 = new JButton("Go- Search Option 1-Emails");

        // Add an ActionListener to the button
        jobSearchButtonOption1.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                // Check the current text on the button
	    	 	FirstSearchOpation searchOpation=new FirstSearchOpation();	
                if (jobSearchButtonOption1.getText().equals("Go- Search Option 1")) 
                {
                    // Change the text to "Stop"
                    jobSearchButtonOption1.setText("Go- Search Option 1");
                    // Perform action for "Go" button
                    System.out.println("Action: Start something...");
   		    	 	searchOpation.search();
                
                
                } 
                else 
                {
                    // Change the text back to "Go"
                    jobSearchButtonOption1.setText("Stop- Search Option 1");
                    // Perform action for "Stop" button
                    System.out.println("Action: Stop something...");
   		    	 	searchOpation.stopSearch();
                
                }
            }
        });

        // Add the button to the frame
        frame.add(jobSearchButtonOption1);
        // Set the frame visibility
        frame.setVisible(true);
    }
    
}
