
public class Sample {
	
	String FName, Col, ID, Source;
	Integer batch, Plate;
	
	public Sample() {
		
	}

	public void setName(String s) {
		this.FName=s;
	}
	
	public void setSource(String s) {
		this.Source=s;
	}
	
	public void setCol(String s) {
		this.Col=s;
	}
	
	public void setID(String s) {
		this.ID=s;
	}
	
	public void setPlate(int i) {
		this.Plate=i;
	}
	
	public void setbatch(int i) {
		this.batch=i;
	}
	
	public int getPlate() {
		return this.Plate;
	}
	
	public int getBatch() {
		return this.batch;
	}
	
	public String getName() {
		return this.FName;
	}
	
	public int getCol() {
		return Integer.parseInt(this.Col.substring(1,2));
	}
	
	public String getID() {
		return this.ID;
	}
	
	public String getSource() {
		return this.Source;
	}
	
}
