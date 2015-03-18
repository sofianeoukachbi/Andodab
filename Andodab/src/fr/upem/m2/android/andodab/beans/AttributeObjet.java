package fr.upem.m2.android.andodab.beans;

public class AttributeObjet {

	private Integer attribute_id;
	private Integer valeur_id;
	private String attribut_name;
	private String attribut_val;
	private String attribut_type;
	
	public AttributeObjet() {
		// TODO Auto-generated constructor stub
	}

	
	
	public AttributeObjet(Integer attribute_id, Integer valeur_id,
			String attribut_name, String attribut_val, String attribut_type) {
		super();
		this.attribute_id = attribute_id;
		this.valeur_id = valeur_id;
		this.attribut_name = attribut_name;
		this.attribut_val = attribut_val;
		this.attribut_type = attribut_type;
	}



	public Integer getAttribute_id() {
		return attribute_id;
	}

	public void setAttribute_id(Integer attribute_id) {
		this.attribute_id = attribute_id;
	}

	public Integer getValeur_id() {
		return valeur_id;
	}

	public void setValeur_id(Integer valeur_id) {
		this.valeur_id = valeur_id;
	}

	public String getAttribut_name() {
		return attribut_name;
	}

	public void setAttribut_name(String attribut_name) {
		this.attribut_name = attribut_name;
	}

	public String getAttribut_val() {
		return attribut_val;
	}

	public void setAttribut_val(String attribut_val) {
		this.attribut_val = attribut_val;
	}

	public String getAttribut_type() {
		return attribut_type;
	}

	public void setAttribut_type(String attribut_type) {
		this.attribut_type = attribut_type;
	}
	
	
	
}
