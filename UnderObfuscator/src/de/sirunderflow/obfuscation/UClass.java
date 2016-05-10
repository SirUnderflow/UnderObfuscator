package de.sirunderflow.obfuscation;

import java.lang.reflect.Field;
import java.util.ArrayList;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;

public class UClass {
	
	public ClassPool classPool = ClassPool.getDefault();
	public CtClass ctClass = null;
	
	private String classPath;
	public String getClassPath() {
		return this.classPath;
	}
	
	private ArrayList<CtField> FieldList = new ArrayList<CtField>();
	public ArrayList<CtField> getFieldList() {
		return this.FieldList;
	}
	
	private ArrayList<CtMethod> MethodList = new ArrayList<CtMethod>();
	public ArrayList<CtMethod> getMethodList() {
		return this.MethodList;
	}
	
	public Object getContentFromField(String name) {
		try {
			Class<?> classObj = Class.forName(this.classPath);
			Object o = classObj.newInstance();
			for (Field f : classObj.getDeclaredFields()) {
				f.setAccessible(true);
				if (f.getName().equals(name)) {
					return f.get(o);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public UClass(String className) throws NotFoundException {
		this.classPath = className;
		this.ctClass = this.classPool.get(className);
		for (CtMethod m : this.ctClass.getDeclaredMethods()) {
			this.getMethodList().add(m);				
		}
		for (CtField f : this.ctClass.getDeclaredFields()) {
			this.getFieldList().add(f);
		}
	}

	public CtMethod getMethod(String name) {
		for (CtMethod m : this.getMethodList()) {
			if (m.getName().equals(name)) {
				return m;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		String fields = "";
		for (CtField f : this.getFieldList()) {
			try {
				String[] typeArray = f.getType().getName().split("\\.");
				try {
					f.visibleFrom(this.ctClass);
					fields += "public ";
					String type = typeArray[typeArray.length - 1];
					if (type.equalsIgnoreCase("String")) {
						fields +=  type + " " + f.getName() + " = " + "\"" + this.getContentFromField(f.getName()) + "\"" + "\n";						
					}
					else
					{
						fields +=  type + " " + f.getName() + " = " + this.getContentFromField(f.getName()) + "\n";	
					}
				} catch (Exception e) {
				}
			} catch (Exception e) {
			}
		}
		return "Information about this class: "+ "\n" + 
		"----------------------------------" + "\n"  + 
		"Class: " + this.ctClass.getName() + "\n\n" + 
		fields +
		"----------------------------------";
	}
	
}
