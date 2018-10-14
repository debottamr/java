package clone;

public class CloneEx {

	public static void main(String[] args) {
		EmployeeAddress empAddress = new EmployeeAddress("22", "Avenue Street", "Dallas");
		Employee emp = new Employee("David", 32, empAddress);
		Employee empClone = null;
		try {
			empClone = (Employee) emp.clone();
			//empAddress.clone();
			System.out.println("clone constructor not called");
		} catch (CloneNotSupportedException cnse) {
			cnse.printStackTrace();
		}
		
		System.out.println(empClone.getEmpAddress()==emp.getEmpAddress());
		System.out.println("Cloned Employee Object: " + empClone);
	}
}

class Employee implements Cloneable {
	private String name;
	private Integer age;
	private EmployeeAddress empAddress;

	// Employee constructor
	public Employee(String name, Integer age, EmployeeAddress empAddress) {
		this.name = name;
		this.age = age;
		this.empAddress = empAddress;
		System.out.println("clone called");
	}
	

	// setters and getters for name, age and empAddress go here

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getAge() {
		return age;
	}


	public void setAge(Integer age) {
		this.age = age;
	}


	public EmployeeAddress getEmpAddress() {
		return empAddress;
	}


	public void setEmpAddress(EmployeeAddress empAddress) {
		this.empAddress = empAddress;
	}


	public String toString() {
		return "Employee Name:" + this.name + "  Age:" + this.age + " Address:" + empAddress;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Employee)) {
			return false;
		}
		Employee empObj = (Employee) obj;
		return this.age == empObj.age && this.name.equalsIgnoreCase(empObj.name);
	}

	
	//deep clone
	
	public Object deepclone() throws CloneNotSupportedException {
	  Employee empClone = (Employee) super.clone();
	  EmployeeAddress empAddressClone = new EmployeeAddress(this.empAddress.getHouseNo(),
	                                      this.empAddress.getStreet(),
	                                      this.empAddress.getCity());
	  empClone.setEmpAddress(empAddressClone);
	  return empClone;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

class EmployeeAddress {
	
	public String getHouseNo() {
		return houseNo;
	}



	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}



	public String getStreet() {
		return street;
	}



	public void setStreet(String street) {
		this.street = street;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}

	private String houseNo;
	private String street;
	private String city;

	// EmployeeAddress constructor
	public EmployeeAddress(String houseNo, String street, String city) {
		this.houseNo = houseNo;
		this.street = street;
		this.city = city;
	}
	
	

	// setters and getters for houseNo, street and city go here
	@Override
	public String toString() {
		return "EmployeeAddress{" + "houseNo='" + houseNo + '\'' + ", street='" + street + '\'' + ", city='" + city
				+ '\'' + '}';
	}
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}