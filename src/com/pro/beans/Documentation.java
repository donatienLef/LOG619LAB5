package com.pro.beans;

import java.util.Date;

public class Documentation {
	private Long id_documentation;
	private String type;
	private String description;
	private String nom_documentation;
	private String path_documentation;
	private String maya;
	private String validation;
	Date date_document;
	private Integer size;
	private Long id_service;
	private Long id_application;
	
	//ACCESSEUR
	
	public Long getId_documentation(){
		return this.id_documentation;
	}
	public Long getId_service(){
		return this.id_service;
	}
	public Long getId_application(){
		return this.id_application;
	}
	public String getType(){
		return this.type;
	}	
	
	public String getDescription(){
		return this.description;
	}
	public String getNom_documentation(){
		return this.nom_documentation;
	}
	public String getPath_documentation(){
		return this.path_documentation;
	}
	public String getMaya(){
		return this.maya;
	}
	public String getValidation(){
		return this.validation;
	}
	public Integer getSize(){
		return this.size;
	}
	
	//MUTTATEUR
	public void setId_documentation(Long id_doc){
		this.id_documentation=id_doc;
	}
	public void setId_service(Long id_service){
		this.id_service=id_service;
	}
	public void setId_application(Long id_app){
		this.id_application=id_app;
	}
	public void setType(String type){
		this.type=type;
	}	
	
	public void setDescription(String description){
		this.description=description;
	}
	public void setNom_documentation(String nomDoc){
		this.nom_documentation=nomDoc;
	}
	public void setPath_documentation(String pathDoc){
		this.path_documentation=pathDoc;
	}
	public void setMaya(String maya){
		this.maya=maya;
	}
	public void setValidation(String validation){
		this.validation=validation;
	}
	public void setSize(Integer size){
		this.size=size;
	}
	

}
