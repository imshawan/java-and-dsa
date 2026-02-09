package Language;

import java.util.*;

public class Comparison {

    public static class Car implements Comparable<Car> {
        String brand;
        int year;
        double price;

        Car(String brand, int year, double price) {
            this.brand = brand;
            this.year = year;
            this.price = price;
        }

        // 1. Comparable: Default sorting by Year (Oldest to Newest)
        @Override
        public int compareTo(Car other) {
            return this.year - other.year;
        }

        @Override
        public String toString() {
            return String.format("%-10s | %d | $%.2f", brand, year, price);
        }
    }

    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("Tesla", 2023, 50000));
        cars.add(new Car("Ford", 1965, 35000));
        cars.add(new Car("Toyota", 2015, 20000));
        cars.add(new Car("BMW", 2023, 60000));

        System.out.println("--- Original List ---");
        cars.forEach(System.out::println);

        // Uses Comparable (compareTo)
        Collections.sort(cars);
        System.out.println("\n--- Sorted by Year (Comparable) ---");
        cars.forEach(System.out::println);

        // 2. Comparator: Custom sorting by Brand (Alphabetical)
        Comparator<Car> brandComparator = new Comparator<Car>() {
            @Override
            public int compare(Car c1, Car c2) {
                return c1.brand.compareTo(c2.brand);
            }
        };

        cars.sort(brandComparator);
        System.out.println("\n--- Sorted by Brand (Comparator) ---");
        cars.forEach(System.out::println);

        // 3. Chained Comparator: Sort by Year, THEN by Price (Modern Java Syntax)
        System.out.println("\n--- Sorted by Year, then Price (Chained) ---");
        cars.sort(Comparator.comparingInt((Car c) -> c.year)
                .thenComparingDouble(c -> c.price));
        cars.forEach(System.out::println);
    }
}
