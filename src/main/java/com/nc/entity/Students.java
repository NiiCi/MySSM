package com.nc.entity;

public class Students {
	private int sid;
	private String name;
	private int age;
	private double score;
	private String sex;
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	
	@Override
	public String toString() {
		return "Students [sid=" + sid + ", name=" + name + ", age=" + age + ", score=" + score + ", sex=" + sex + "]";
	}
	public Students() {
		super();
	}
	public Students(String name, int age, double score) {
		super();
		this.name = name;
		this.age = age;
		this.score = score;
		
	}
	
}
