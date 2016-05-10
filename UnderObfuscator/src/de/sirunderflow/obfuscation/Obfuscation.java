package de.sirunderflow.obfuscation;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;

public class Obfuscation {

	private UClass uclass;
	private Encryption encryption;
	
	public Obfuscation(UClass uclass, String key) {
		this.uclass = uclass;
		this.encryption = new Encryption(key);
	}

	public UClass getUclass() {
		return uclass;
	}

	public void setUclass(UClass uclass) {
		this.uclass = uclass;
	}
	
	private void addDecryptionMethod() {
		try {
			CtClass[] args = { ClassPool.getDefault().get("java.lang.String") };
			CtMethod m = new CtMethod(ClassPool.getDefault().get("java.lang.String"), "unobf", args, this.uclass.ctClass);
			m.setBody(
					"{" + 
							"java.lang.String result = \"\";" + 
							"for (int i = 0; i < $1.split(\"%\").length; i++) {" +
								"java.lang.String Snumber = $1.split(\"%\")[i];" + 
								"int number = java.lang.Integer.parseInt(Snumber);" + 
								"result = result + java.lang.Character.toChars(number);" +
							"}" + 
							"return result;" + 
					"}");
			this.uclass.ctClass.addMethod(m);
		} catch (Exception e) {
		}
	}
	
	public void encryptAllStrings() throws NotFoundException, CannotCompileException {
		this.addDecryptionMethod();
		try {
			for (CtField f : this.uclass.getFieldList()) {
				if (f.getType().getName().contains("String")) {
					String txt = (String) this.uclass.getContentFromField(f.getName());
					uclass.removeField(f.getName());
					this.encryption.setInput(txt);
					uclass.addStringField(f.getName(), "this.unobf(" + "\"" +this.encryption.getEncryptedResult() + "\"" + ")");
				}
			}
			uclass.save();
			System.out.println("compiled!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
