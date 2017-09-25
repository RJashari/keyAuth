package com.bpbbank;

public class KeyReport {

	private int id;
	private String name;
	
//	public KeyReport() {
//	}

	public KeyReport(int id, String name) {

		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void speak() {
		System.out.println("Hello");
	}

	@Override
	public String toString() {
		return "KeyReport [id=" + id + ", name=" + name + "]";
	}

}
