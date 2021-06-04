import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.*;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class Runner {
	
	static JTextArea textArea;
	static JFrame frame;
	static JTextPane P;

	public static void main(String[] args) throws IOException {
		
		Methods M = new Methods();
		

		//Creates the Swing window for logging
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame = new JFrame("Log");
	    frame.setBounds(0, 0, 600, 400);
	    frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    textArea = new JTextArea();
	    frame.getContentPane().add(textArea, BorderLayout.CENTER);
	    frame.setVisible(true);
	    
	    textArea.append("Starting program...\r\n");
		
		File F = new File("input\\");
		File[] FList = F.listFiles();
		ArrayList<ArrayList<Sample>> masterlist = new ArrayList<>();
		
	    textArea.append("Getting files from input folder...\r\n");
	    
		
		for (File f : FList) {
			ArrayList<Sample> ATest = M.WKLBreak(f);
			masterlist.add(ATest);
		}

	    textArea.append("File obtained and parsed. Generating database...\r\n");
		
		ArrayList<Sample> BrokenList = M.Generate(masterlist);
		
	    textArea.append("Database Generated, writing file. \r\n");
	    
	    String name;
        name = JOptionPane.showInputDialog(null,
         "Please enter your search string WITHOUT extension e.g. 'SAL01' instead of 'SAL01.d'");
        

	    textArea.append("Searching...");
	    textArea.append("\r\n");
	    
        name = name + ".mzML";
        String output = "";
        //System.out.println(name);
	    
        for (Sample s : BrokenList) {
        	//System.out.println(s.getName());
        	if (name.equals(s.getName())) {
        		output = s.getSource();
        		break;
        	}
        }
		
		//M.WritebatchInfo(BrokenList);
		
	    textArea.append("Search Completed.");
	    textArea.append("\r\n");
	    
	    if (output.length()==0) {
		    textArea.append("Search String not found...");
	    }
	    else {
		    textArea.append("String found in : " + output);
	    }
	    
		/*
		
		for (Sample s : BrokenList) {
			System.out.print(s.getCol() + "\t" + s.getID() + "\t" + s.getName() + "\t" + s.getPlate() + "\t" + s.getBatch());
			System.out.println();
		}
		
		*/
		
		
	}
	

}
