package de.sirunderflow.obfuscation;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;

public class UClass {
	
	public ClassPool classPool = ClassPool.getDefault();
	public CtClass ctClass = null;
	
	private String classPath;
	
	private ArrayList<String> classPahtList = new ArrayList<String>();
	public ArrayList<String> getClassPathList() {
		return this.classPahtList;
	}
	
	public void addClassPath(String path) {
		try {
			this.classPool.insertClassPath(path);
			this.getClassPathList().add(path);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}
	
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
	
	
	public void save() throws NotFoundException, IOException, CannotCompileException {
		this.ctClass.writeFile();
	}
	
	public void addStringField(String name, String content) throws CannotCompileException, NotFoundException {
		CtField f = new CtField(ClassPool.getDefault().get("java.lang.String"), name, this.ctClass);
		f.setAttribute("value", content.getBytes());
		this.ctClass.addField(f);
		this.ctClass.getConstructors()[0].insertAfter("{" + "this." + name + " = " + content + ";" + "}");
	}
	
	public void addIntegerField(String name, String content) throws CannotCompileException, NotFoundException {
		CtField f = new CtField(CtClass.intType, name, this.ctClass);
		f.setAttribute("value", content.getBytes());
		this.ctClass.addField(f);
		this.ctClass.getConstructors()[0].insertAfter("{" + "this." + name + " = "  + content  + ";" + "}");
	}

	public void addDoubleField(String name, String content) throws CannotCompileException, NotFoundException {
		CtField f = new CtField(CtClass.doubleType, name, this.ctClass);
		f.setAttribute("value", content.getBytes());
		this.ctClass.addField(f);
		this.ctClass.getConstructors()[0].insertAfter("{" + "this." + name + " = "  + content  + ";" + "}");
	}

	public void addFloatField(String name, String content) throws CannotCompileException, NotFoundException {
		CtField f = new CtField(CtClass.floatType, name, this.ctClass);
		f.setAttribute("value", content.getBytes());
		this.ctClass.addField(f);
		this.ctClass.getConstructors()[0].insertAfter("{" + "this." + name + " = "  + content  + ";" + "}");
	}

	public void addByteField(String name, String content) throws CannotCompileException, NotFoundException {
		CtField f = new CtField(CtClass.byteType, name, this.ctClass);
		f.setAttribute("value", content.getBytes());
		this.ctClass.addField(f);
		this.ctClass.getConstructors()[0].insertAfter("{" + "this." + name + " = "  + content  + ";" + "}");
	}

	public void addObjeftField(String type, String name, String content)
			throws CannotCompileException, NotFoundException {
		CtField f = new CtField(ClassPool.getDefault().get(type), name, this.ctClass);
		f.setAttribute("value", content.getBytes());
		this.ctClass.addField(f);
		this.ctClass.getConstructors()[0].insertAfter("{" + "this." + name + " = "  + content  + ";" + "}");
	}
	
	public void addStringMethod(String name, String content) throws CannotCompileException, NotFoundException {
		CtClass[] args = {};
		CtMethod m = new CtMethod(ClassPool.getDefault().get("java.lang.String"), name, args, this.ctClass);
		m.setBody("return \"" + content + "\";");
		this.ctClass.addMethod(m);
	}
	
	public void addIntegerMethod(String name, String content) throws CannotCompileException, NotFoundException {
		CtClass[] args = {};
		CtMethod m = new CtMethod(CtClass.intType, name, args, this.ctClass);
		m.setBody("return " + content + ";");
		this.ctClass.addMethod(m);
	}
	
	public void addDoubleMethod(String name, String content) throws CannotCompileException, NotFoundException {
		CtClass[] args = {};
		CtMethod m = new CtMethod(CtClass.doubleType, name, args, this.ctClass);
		m.setBody("return " + content + ";");
		this.ctClass.addMethod(m);
	}
	
	public void addFloatMethod(String name, String content) throws CannotCompileException, NotFoundException {
		CtClass[] args = {};
		CtMethod m = new CtMethod(CtClass.floatType, name, args, this.ctClass);
		m.setBody("return " + content + ";");
		this.ctClass.addMethod(m);
	}
	
	public void addByteMethod(String name, String content) throws CannotCompileException, NotFoundException {
		CtClass[] args = {};
		CtMethod m = new CtMethod(CtClass.byteType, name, args, this.ctClass);
		m.setBody("return " + content + ";");
		this.ctClass.addMethod(m);
	}
	
	public void addObjectMethod(String Object, String name, String content) throws CannotCompileException, NotFoundException {
		CtClass[] args = {};
		CtMethod m = new CtMethod(ClassPool.getDefault().get(Object), name, args, this.ctClass);
		m.setBody("return " + content + ";");
		this.ctClass.addMethod(m);
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
	
	public CtField getField(String name) {
		for (CtField f : this.getFieldList()) {
			if (f.getName().equals(name)) {
				return f;
			}
		}
		return null;
	}
	
	public void removeField(String name) {
		for (CtField f : this.getFieldList()) {
			if (f.getName().equals(name)) {
				try {
					this.ctClass.removeField(f);
				} catch (NotFoundException e) {
					e.printStackTrace();
				}
			}
		}
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
