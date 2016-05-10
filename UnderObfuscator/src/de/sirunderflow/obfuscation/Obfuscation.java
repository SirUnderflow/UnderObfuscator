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
	
	private void addDecryptionMethods() {
		try {
			// Strings
			
			CtClass[] type_String = { ClassPool.getDefault().get("java.lang.String") };
			CtMethod m_String = new CtMethod(ClassPool.getDefault().get("java.lang.String"), "DE_String", type_String, this.uclass.ctClass);
			m_String.setBody(
					"{" + 
							"java.lang.String result = \"\";" + 
							"for (int i = 0; i < $1.split(\"%\").length; i++) {" +
								"java.lang.String Snumber = $1.split(\"%\")[i];" + 
								"int number = java.lang.Integer.parseInt(Snumber);" + 
								"result = result + java.lang.Character.toChars(number);" +
							"}" + 
							"return result;" + 
					"}");
			this.uclass.ctClass.addMethod(m_String);
			
			// Integers
			
			CtClass[] type_Integer = { ClassPool.getDefault().get("java.lang.String") };
			CtMethod m_Integer = new CtMethod(CtClass.intType, "DE_Integer", type_Integer, this.uclass.ctClass);
			m_Integer.setBody(
					"{" + 
							"return $1.toCharArray().length;" + 
					"}");
			
			this.uclass.ctClass.addMethod(m_Integer);
			
			// TODO: Weitermachen
		} catch (Exception e) {
		}
	}
	
	public void encryptAllVariables() throws NotFoundException, CannotCompileException {
		this.addDecryptionMethods();
		try {
			for (CtField f : this.uclass.getFieldList()) {
				System.out.println(f.getType().getName());
				if (f.getType().getName().contains("String")) {
					String txt = (String) this.uclass.getContentFromField(f.getName());
					uclass.removeField(f.getName());
					this.encryption.setInput(txt);
					uclass.addStringField(f.getName(), "this.DE_String(" + "\"" +this.encryption.getEncryptedResult_String() + "\"" + ")");
				}
				else if (f.getType().getName().contains("int")) {
					int number = (int) this.uclass.getContentFromField(f.getName());
					uclass.removeField(f.getName());
					this.encryption.setInput(number);
					uclass.addIntegerField(f.getName(), "this.DE_Integer(" + "\"" +this.encryption.getEncryptedResult_Integer() + "\"" + ")");
				}
			}
			uclass.save();
			System.out.println("compiled!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
