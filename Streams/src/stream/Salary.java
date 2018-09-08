package stream;

public class Salary {
	 private int total = 0;
	    
	    public void doProcess(Employee emp){
	        addSalary(emp.getSalary());
	    }
	    
	    public void addSalary(int salary){
	        total = total + salary;
	    }
	    public int getTotalSalary(){
	        return total;
	    }
}
