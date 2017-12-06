package com.pro.beans;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class Configurations {
	private String mdpPattern = ".{3,}" ;
	private Integer nbeTentativeMax = 0;
	private Integer nbeMinutesEntreTentative = 1;
	private Boolean blocageIsPossible = false;
	private Boolean changePasswordAfterNTentative = false;
	private Boolean changePasswordAfterForget = false;
	
	
	public String getMdpPattern(){
		return this.mdpPattern;
	}
	public void setMdpPattern(String pattern){
		this.mdpPattern = pattern;
	}
	public Integer getNbeTentativeMax(){
		return this.nbeTentativeMax;
	}
	public void setNbeTentativeMax(Integer tentativeMax){
		this.nbeTentativeMax = tentativeMax;
	}
	public Integer getNbeMinutesEntreTentative(){
		return this.nbeMinutesEntreTentative;
	}
	public void setNbeMinutesEntreTentative(Integer NbeMinutesEntreTentative){
		this.nbeMinutesEntreTentative = NbeMinutesEntreTentative;
	}
	public Boolean getChangePasswordAfterNTentative(){
		return this.changePasswordAfterNTentative;
	}
	public void setChangePasswordAfterNTentative(Boolean changePasswordAfterNTentative){
		this.changePasswordAfterNTentative = changePasswordAfterNTentative;
	}
	public Boolean getBlocageIsPossible(){
		return this.blocageIsPossible;
	}
	public void setBlocageIsPossible(Boolean blocageIsPossible){
		this.blocageIsPossible= blocageIsPossible;
	}
	public Boolean getChangePasswordAfterForget(){
		return this.changePasswordAfterForget;
	}
	public void setChangePasswordAfterForget(Boolean changePasswordAfterForget){
		this.changePasswordAfterForget= changePasswordAfterForget;
	}
}
