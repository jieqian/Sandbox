package com.sandbox.beaninfo;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

import com.sandbox.utils.pojo.Pojo;

public class Sandbox {

	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException {
		// TODO Auto-generated method stub
		Pojo pojo = new Pojo();
		getBeanInfo(pojo);
	}
	
	public static void getBeanInfo(Object object) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		BeanInfo bi=Introspector.getBeanInfo(object.getClass(), Object.class);
		PropertyDescriptor[] pd=bi.getPropertyDescriptors();
		for (int i = 0; i < pd.length; i++) {
	         if(pd[i].getPropertyType().isArray()){  //getPropertyType得到属性类型。
	            //getReadMethod()得到此属性的get方法----Method对象，然后用invoke调用这个方法
	            String[] result=(String[]) pd[i].getReadMethod().invoke(object, null);
	            System.out.println(pd[i].getName()+":");//getName得到属性名字
	            for (int j = 0; j < result.length; j++) {
	               System.out.println(result[j]);
	            }
	         }else{
	            System.out.println(pd[i].getName()+":"+pd[i].getReadMethod().invoke(object, null));
	         }
	      }
	}

}
