package ru.appline.logic;

import lombok.Getter;
import lombok.Setter;

public class Pet {
	
	/*
		{
		    "name": "Jim",
		    "type": "Beam",
		    "age": 8
		}
	*/
	
	@Getter @Setter
	private String name;
	@Getter @Setter
	private String type;
	@Getter @Setter
	private int age;
	
	public Pet() {
		super();
	}
	
	public Pet(String name, String type, int age) {
		super();
		this.name = name;
		this.type = type;
		this.age = age;
	}
	
	
	
}
