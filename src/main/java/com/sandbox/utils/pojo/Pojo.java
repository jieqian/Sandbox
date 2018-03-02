package com.sandbox.utils.pojo;

import java.util.Date;

public class Pojo{
	   private String x;
	   private int y;
	   private Date date;
	   public Pojo(String x, int y){
		   this.x=x;
		   this.y=y;
		   this.date = new Date();
	   }

	   public Pojo(){
	   	  this.x = "default";
	   	  this.y = 0;
	      this.date = new Date();
	   }
	   
	   public String getX(){
		   return this.x;
	   }
	   
	   public int getY(){
		   return this.y;
	   }

	@Override
	public String toString() {
		return "Pojo [x=" + x + ", y=" + y + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Pojo pojo = (Pojo) o;

//		if (y != pojo.y) return false;
		return x != null ? x.equals(pojo.x) : pojo.x == null;
	}

	@Override
	public int hashCode() {
		int result = x != null ? x.hashCode() : 0;
		result = 31 * result + y;
		return result;
	}
}