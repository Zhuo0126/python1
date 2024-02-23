package com.springboot.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

//@SpringBootApplication
public class LambdaApplication {
	public static void main(String[] args) {
		List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
		Function<String, String> toUpperCase = name -> name.toUpperCase();
		List<String> uppercasedNames = names.stream().map(toUpperCase).collect(Collectors.toList());
		System.out.println(uppercasedNames);

//		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
//		Consumer<Integer> printNumber = number -> System.out.println(number * number);
//		numbers.forEach(printNumber);

//		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
//		Predicate<Integer> isEven = number -> number % 2 == 0;
//		List<Integer> evenNumbers = numbers.stream()
//				.filter(isEven)
//				.collect(Collectors.toList());
//		System.out.println(evenNumbers);

//		List<String> words = Arrays.asList("apple", "banana", "cherry", "date");
//		Comparator<String> byLengthDesc = (word1, word2) -> Integer.compare(word2.length(), word1.length());
//		List<String> sortedWords = words.stream()
//				.sorted(byLengthDesc)
//				.collect(Collectors.toList());
//		System.out.println(sortedWords);
	}
}
