import java.util.*;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		Collection<Person>  myCol = new ArrayList<>();
		createTestData(myCol);
		printCollection(myCol);
		System.out.println();

		myCol = sortCollection(myCol);
		System.out.println("All sorted: ");
		printCollection(myCol);

		System.out.println();
		System.out.println("Printing using lambda:");		
		myCol.stream()
		.forEach(p -> System.out.println(p.getName()));

		System.out.println();
		System.out.println("Older than 40: ");
		myCol.stream()
		.filter(e -> (e.getAge() > 40))
		.forEach(e -> System.out.println(e.getName()));

		System.out.println();
		int totalAge = myCol
				.stream()
				.map(Person::getAge)//.map(p -> p.getAge())
  				.reduce(0, (x,y) -> x+y);
		System.out.println("Average age: " + (double)totalAge/myCol.size());
	}


	private static Collection<Person> sortCollection(Collection<Person> myCol) {
		return myCol.stream()
				.sorted(Comparator.naturalOrder())
				.collect(Collectors.toCollection(ArrayList::new));
	}

	private static void printCollection(Collection<Person> myCol) {
		for(Person p : myCol)
			System.out.println(p.getName());
	}

	private static void createTestData(Collection<Person> myCol) {
		myCol.add(new Person("Ib", 45));
		myCol.add(new Person("Kis", 54));
		myCol.add(new Person("Finn", 55));
		myCol.add(new Person("Rie", 45));
		myCol.add(new Person("Ib", 39));
		myCol.add(new Person("Mogens", 45));
		myCol.add(new Person("Ann", 32));
		myCol.add(new Person("Poul", 65));
		myCol.add(new Person("Torben", 65));
		myCol.add(new Person("Jesper", 44));
		myCol.add(new Person("Anita", 25));
		myCol.add(new Person("Istvan", 45));
		myCol.add(new Person("Henrik", 35));
		myCol.add(new Person("Nadeem", 40));
		myCol.add(new Person("Gianna", 35));
		myCol.add(new Person("Per", 45));
		myCol.add(new Person("Lars", 28));
		myCol.add(new Person("Simon", 35));
	}

}
