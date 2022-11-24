package model;

import java.io.Serializable;

public class ModelTelefone implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String numero;
	
	private ModelLogin usuario_pai_id; //objeto e não campos
	private ModelLogin usuario_cad_id;
	
	//gets e sets
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public ModelLogin getUsuario_pai_id() {
		return usuario_pai_id;
	}
	public void setUsuario_pai_id(ModelLogin usuario_pai_id) {
		this.usuario_pai_id = usuario_pai_id;
	}
	public ModelLogin getUsuario_cad_id() {
		return usuario_cad_id;
	}
	public void setUsuario_cad_id(ModelLogin usuario_cad_id) {
		this.usuario_cad_id = usuario_cad_id;
	}
	
	//equal e hscode do id
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelTelefone other = (ModelTelefone) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	

}
