package com.echain.web.cmcc.beans;

import com.fasterxml.jackson.annotation.JsonAlias;

public class NumAreaResponse {

	@JsonAlias("prov_cd")
	private String provCd;
	
	@JsonAlias("id_area_cd")
	private String idAreaCd;
	
	@JsonAlias("id_name_cd")
	private String idNameCd;
	
	@JsonAlias("num_type")
	private String numType;

	public String getProvCd() {
		return provCd;
	}

	public void setProvCd(String provCd) {
		this.provCd = provCd;
	}

	public String getIdAreaCd() {
		return idAreaCd;
	}

	public void setIdAreaCd(String idAreaCd) {
		this.idAreaCd = idAreaCd;
	}

	public String getIdNameCd() {
		return idNameCd;
	}

	public void setIdNameCd(String idNameCd) {
		this.idNameCd = idNameCd;
	}

	public String getNumType() {
		return numType;
	}

	public void setNumType(String numType) {
		this.numType = numType;
	}
}
