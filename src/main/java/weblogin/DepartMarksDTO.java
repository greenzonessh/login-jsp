package weblogin;

public class DepartMarksDTO {

	private String departName;
	private int rowspan;
	private double pass;
	
	public DepartMarksDTO(String departName, int rowspan, double pass) {
		super();
		this.departName = departName;
		this.rowspan = rowspan;
		this.pass = pass;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public int getRowspan() {
		return rowspan;
	}

	public void setRowspan(int rowspan) {
		this.rowspan = rowspan;
	}

	public double getPass() {
		return pass;
	}

	public void setPass(double pass) {
		this.pass = pass;
	}
	
}
