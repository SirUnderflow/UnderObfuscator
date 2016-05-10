package de.sirunderflow.obfuscation;

import javassist.CannotCompileException;
import javassist.CtField;
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
	
	public void encryptAllStrings() throws NotFoundException, CannotCompileException {
		for (CtField f : this.uclass.getFieldList()) {
			if (f.getType().getName().contains("String")) {
				String txt = (String) this.uclass.getContentFromField(f.getName());
				this.encryption.setInput(txt);
				this.uclass.ctClass.removeField(f);
			}
		}
	}
	
}
