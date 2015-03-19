package fr.upem.m2.android.andodab.beans;

public class Final_bean {

	
	private Integer final_id;
	private String final_val;
	private Integer final_primitif_id;
	
	public Final_bean() {
		// TODO Auto-generated constructor stub
	}
	
	
 
	
	public Final_bean(Integer final_id, String final_val,Integer final_primitif_id) {
		 
		this.final_id = final_id;
		this.final_val = final_val;
		this.final_primitif_id = final_primitif_id;
	}
 



	public Final_bean(Integer final_id) {
		super();
		this.final_id = final_id;
	}

	
<<<<<<< HEAD
=======
	


>>>>>>> 619642a79a50ca3cca7bf752878bd4cf1ba32b60


	public Integer getFinal_primitif_id() {
		return final_primitif_id;
	}
	
	public void setFinal_primitif_id(Integer final_primitif_id) {
		this.final_primitif_id = final_primitif_id;
	}
	
public String getFinal_val() {
	return final_val;
}

public void setFinal_val(String final_val) {
	this.final_val = final_val;
}

	public Integer getFinal_id() {
		return final_id;
	}
	
	public void setFinal_id(Integer final_id) {
		this.final_id = final_id;
	}
}
