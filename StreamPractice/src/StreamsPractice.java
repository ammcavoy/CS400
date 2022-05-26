import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.*;
import java.util.Optional;

// cs400 StreamsPractice examples 
// Author: Andy Kuemmel
// Semester: Spring 2019
public class StreamsPractice {

        public static void main(String[] args) {

        List<String> words = Arrays.asList("the", "Quick", "Brown", "the", "THE",
 "fox", "jumped", "jUmped", "over", "the", "lAzy", "dog", "FREE");

        // 1.) collector is a list
        List<String> result = words.stream()
                                .map(thing -> thing.toLowerCase())
                                .sorted()
                                .distinct()
                                .filter(word -> word.length() > 3) // keep
                                //.limit(2)
                                .collect(Collectors.toList()); //System.out.println("lowercase, length > 3, sorted, distinct: " + result + "\n");


        // 2.) collector is a for/each iterator
        System.out.println("words that contain an e, with only e's showing" );
        words.stream()
                        .map(x ->x.toLowerCase())
                        .distinct()
                        .filter(n -> n.contains("e"))
                        .forEach(thing -> {
                                        for (int i=0; i < thing.length(); i++){
                                                if (thing.charAt(i)=='e') System.out.print("e ");
                                                else System.out.print("_ ");
                                        }// for
                                        System.out.println();
                                }
                        );//forEach collector



         // 3.) collector is a Long

        long counter = words.stream()
                                .map(x ->x.toLowerCase())
                                .filter(x -> x.contains("e"))
                                .count();
         System.out.println(counter + " items contain e\n");


        // the next few examples use this List<Student>  
        // Student and Level are declared at the end of this file 

        List<Student> students = Arrays.asList(
                         new Student("Stevie",  10, Level.K12),
                         new Student("Meghan",  21, Level.UNDERGRAD),
                         new Student("Josh",    18, Level.UNDERGRAD),
                         new Student("Pratham", 25, Level.GRADUATE),
                         new Student("Alice",   28, Level.CAPSTONE),
                         new Student("Sam",     12, Level.K12),
                         new Student("Andy",    25, Level.GRADUATE),
                         new Student("Sam",     12, Level.K12)  // duplicate
                         );


    // 4.) A list of the ages, with no duplicates
    List<Integer> distinctAges = students.stream()
                    .map(Student::getAge)
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
    System.out.println("Students distinct ages: " + distinctAges);


    // 5.) count how many students are less than 20
    long count = students.stream()
                    .filter(s -> s.getAge() < 20)
                    .count();
    System.out.println(count + " students with age < 20\n");


    // 6.)  use Collectors to group students by Level
    Map <Level, List<Student>> stuGroupings = students.stream()
                    .collect(Collectors.groupingBy(s -> s.getLevel()));
    System.out.println("A map of students, by level:\n\t" + stuGroupings + "\n");



// Streams of ints
        // 7.) IntStream can produce a stream of ints
        int sum = IntStream.range(1,100)
                        .filter(n -> n % 3 == 0)
                        .sum();
        System.out.println("sum of all multiples of 3 between 1 and 100: " + sum);

        // 8.) this reduce operation finds the product the ints from 1 to 9     
        int product = IntStream.range(1,10)
                            .reduce(1, (a,b) -> a * b );
        System.out.println("Product of ints from 1 to 10 using reduce: " + product);

        // 9.) an infinite stream may not produce an aggregate answer
        // so the answer type must be declared Optional
        // see the JavaDoc for Optional for details
        double low = 0.25; double high = 0.26;
        Optional<Double> answer = Stream.generate(Math::random)
                                        .filter(x -> (x > low && x < high))
                                        .findFirst(); // terminator
        System.out.println("Used .generate to findFirst random between " + low + " and " + high + " : " + answer);



        // 10.) you can use the iterate method to generate your own infinite sequence of ints
        System.out.print("\nAn example of using Stream.iterate to generate a sequence: ");
        Stream.iterate(10, x -> 3*x-5)
                        .limit(7)
                        .forEach(number -> System.out.print(number + ", "));
        System.out.println("\n");


         // 11.) turn a file into a stream
        String fileName = "StreamsPractice.java";
        String targetWord = "Stream".toLowerCase();

        // an example of try-with-resoucres
        // the resource is closed at the end of the try/catch block
        try ( Stream<String> streamOfLines = Files.lines(Paths.get(fileName))){
            long countWord = streamOfLines
                                .map(String::trim)
                                .map(String::toLowerCase)
                                .filter(line -> line.contains(targetWord))
                                .count();
            System.out.println("In the file " + fileName + " " + countWord + " lines contain " + targetWord);
        } catch (IOException e) {
            e.printStackTrace();
        }



        }// main

        enum Level {K12, UNDERGRAD, CAPSTONE, GRADUATE};
        // A class to store information about one Student
        // demonstrates the features of Streams
        static class Student {
                private String name;
                private int age;
                private Level level;

                Student(String name, int age, Level level){
                        this.name = name;
                        this.age = age;
                        this.level = level;
                }
                public String getName() { return name;}
                public int getAge() { return age;}
                public Level getLevel() { return level;}
                public String toString() { return this.name + "-" + this.age + "-" + this.level;}
                public boolean equals(Student other) {
                        return (this.getName().equals(other.getName())) &&
                                        (this.getAge() == other.getAge() )&&
                                        (this.getLevel().equals(other.getLevel()));
                }
        }


}

