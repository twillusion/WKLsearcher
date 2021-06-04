import java.util.*;
import java.io.*;
import java.nio.*;


public class Methods {

	public Methods() {
		
	}
	
	public ArrayList<Sample> Generate(ArrayList<ArrayList<Sample>> A) {
		ArrayList<Sample> output = new ArrayList<>();
		
		for (ArrayList<Sample> AL : A) {
			for (Sample s : AL) {
				if (!s.getID().equals("blank") && !s.getID().contains("#")) {
					output.add(s);
				}
			}
		}
		return output;
	}
	
	public void WritebatchInfo(ArrayList<Sample> A) throws IOException {
		FileWriter fw = new FileWriter("batchinfo_SP2.txt");
		//System.out.println("Writing file...");
		fw.write("file\tbatch\ttype\r\n");
		for (Sample s : A) {
			String T = "";
			if (s.getID().contains("pool")) {
				T="BQC";
			}
			else {
				T="Sample";
			}
			fw.write(s.getName()+"\t"+s.getBatch()+"\t"+T+"\r\n");
		}
		fw.flush();
		//System.out.println("Writing Done");
		
	}
	
	public void Sorter(ArrayList<Sample> A) {
		Collections.sort(A, new ScoreComp());
	}
	
	public ArrayList<Sample> WKLBreak(File F) throws FileNotFoundException {
		ArrayList<Sample> output = new ArrayList<>();
		Scanner sc = new Scanner(F);
		while (sc.hasNextLine()) {
			String Line = sc.nextLine();
			if (Line.contains("<SampleInfo>")) {
				Sample S = new Sample();
				Line = sc.nextLine();
				String Name = "";
				String Colname = "";
				String FileN="";
				while (!Line.contains("</SampleInfo>")) {
					Line = sc.nextLine();
					if (Line.contains("<Name>")) {
						Name = Line.substring(Line.indexOf(">")+1,Line.lastIndexOf("<"));
					}
					if (Line.contains("<AcqMethod>")) {
						Colname = Line.substring(Line.indexOf(".")-2,Line.indexOf("."));
					}
					if (Line.contains("<DataFileName>")) {
						FileN = Line.substring(Line.lastIndexOf("\\")+1,Line.indexOf(".")+1)+"mzML";
					}
				}
				S.setCol(Colname);
				S.setID(Name);
				S.setName(FileN);
				S.setSource(F.getName());
				output.add(S);
			}
			
		}
		return output;
	}
	


    class ScoreComp implements Comparator<Sample> {
        @Override
        public int compare(Sample s1, Sample s2) {
            return Integer.compare(s1.getBatch(), s2.getBatch());
        }
    }
	
}
