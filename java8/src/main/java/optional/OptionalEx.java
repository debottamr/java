package optional;

import java.util.Optional;

public class OptionalEx {

	public static void main(String[] args) {
		Optional<Integer> canBeEmpty1 = Optional.of(5);
		canBeEmpty1.isPresent();                    // returns true
		System.out.println(canBeEmpty1.get());                          // returns 5
		 
		Optional<Integer> canBeEmpty2 = Optional.empty();
		System.out.println(canBeEmpty2.isPresent()); 
		
		//Do something If Optional value is present
		
		Optional<Integer> possible = Optional.ofNullable(null);
		//or
		Optional<Integer> possible1 = Optional.ofNullable(5);
		
		possible1.ifPresent(System.out::println);
		if(possible.isPresent()){
		    System.out.println(possible1.get());
		}
		
		//Default/absent values and actions
		
		//Assume this value has returned from a method
		Optional<Company> companyOptional = Optional.empty();
		 
		//Now check optional; if value is present then return it,
		//else create a new Company object and retur it
		Company company = companyOptional.orElse(new Company());
		 
		//OR you can throw an exception as well
		//Company company1 = companyOptional.orElseThrow(IllegalStateException::new);
		
		//d) Rejecting certain values using the filter method

		Optional<Company> companyOptional1 = Optional.empty();
		companyOptional1.filter(department -> "Finance".equals(department.getName()))
        .ifPresent((t) -> System.out.println("Finance is present"));
		
		Optional<Company> companyOptional2 = Optional.of(new Company("Finance"));
		companyOptional2.filter(department -> "Finance".equals(department.getName()))
        .ifPresent((t) -> System.out.println("Finance is present"));
		
		
	}
}
