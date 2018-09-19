package prototype;

import java.util.HashMap;

//Prototype.java
interface Prototype extends Cloneable{
	public Prototype clone() throws CloneNotSupportedException;
}

//UMLClass.java
class UMLClass implements Prototype {
	private String className = "Default Prototype Class Name";

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassName() {
		return this.className;
	}

	@Override
	public UMLClass clone() throws CloneNotSupportedException {
		System.out.println("Creating clone of UMLClass");
		return (UMLClass) super.clone();
	}
}

//UMLAttribute.java
class UMLAttribute implements Prototype {
	private String attributeName = "Default Prototype Attribute Name";

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeName() {
		return this.attributeName;
	}

	@Override
	public UMLAttribute clone() throws CloneNotSupportedException {
		System.out.println("Creating clone of UMLAttribute");
		return (UMLAttribute) super.clone();
	}
}

//UMLAssociation.java
class UMLAssociation implements Prototype {
	private String associationName = "Default Prototype Association Name";

	public void setAssociationName(String associationName) {
		this.associationName = associationName;
	}

	public String getAssociationName() {
		return this.associationName;
	}

	@Override
	public UMLAssociation clone() throws CloneNotSupportedException {
		System.out.println("Creating clone of UMLAssociation");
		return (UMLAssociation) super.clone();
	}
}

//PrototypeFactory.java

class PrototypeFactory {
	private static HashMap<String, Prototype> cloneMap = new HashMap<String, Prototype>();
	static {
		cloneMap.put("Class", new UMLClass());
		cloneMap.put("Attribute", new UMLAttribute());
		cloneMap.put("Association", new UMLAssociation());
	}

	public Prototype getPrototype(String identifier) throws CloneNotSupportedException {
		switch (identifier) {
		case "Class":
			return cloneMap.get("Class").clone();
		case "Attribute":
			return cloneMap.get("Attribute").clone();
		case "Association":
			return cloneMap.get("Association").clone();
		}
		return null;
	}
}

//UMLTool.java
public class UMLTool {
	public static void main(String args[]) {
		try {
			// Creating a Prototype of UMLClass
			UMLClass umlClass = (UMLClass) new PrototypeFactory().getPrototype("Class");
			System.out.println("Name in umlClass instance:" + umlClass.getClassName());
			// Creating a Prototype of UMLAttribute
			UMLAttribute umlAttrb = (UMLAttribute) new PrototypeFactory().getPrototype("Attribute");
			System.out.println("Name in umlAttrb instance:" + umlAttrb.getAttributeName());
			// Creating a Prototype of UMLAssociation
			UMLAssociation umlAssoc = (UMLAssociation) new PrototypeFactory().getPrototype("Association");
			System.out.println("Name in umlAssoc instance:" + umlAssoc.getAssociationName());
		} catch (CloneNotSupportedException cloningException) {
			System.out.println("error in cloning:");
			cloningException.printStackTrace();
		}
	}
}