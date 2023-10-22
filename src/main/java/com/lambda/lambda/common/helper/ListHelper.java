package com.lambda.lambda.common.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Helper class for List Operations
 */
public final class ListHelper {
    /**
     * Adds an element
     */
    public static <T> void add(List<T> list, T item) {
        list.add(item);
    }

    /**
     * Adds multiple elements
     */
    public static <T> void addAll(List<T> list, Collection<? extends T> collection) {
        list.addAll(collection);
    }

    /**
     * Iterates through the elements of a list
     */
    public static <T> void forEach(List<T> list, Consumer<T> action) {
        ListHelper.forEach(list, (Integer i, T item) -> action.accept(item));
    }

    /**
     * Iterates through the elements of a list and stops if a break point is reached
     */
    public static <T> boolean forEach(List<T> list, Predicate<T> action) {
        return ListHelper.forEach(list, (Integer i, T item) -> action.test(item));
    }

    /**
     * Iterates through the elements of a list
     */
    public static <T> void forEach(List<T> list, BiConsumer<Integer, T> action) {
        ListHelper.forEach(list, FunctionHelper.newBiPredicateFromBiConsumer(action));
    }

    /**
     * Iterates through the elements of a list and stops if a break point is reached
     */
    public static <T> boolean forEach(List<T> list, BiPredicate<Integer, T> action) {
        int index = 0;
        for (T item : list) {
            if (!action.test(index, item)) {
                return false;
            }
            index++;
        }
        return true;
    }

    /**
     * Gets an element
     */
    public static <T> T get(List<T> list, int index) {
        return list.get(index);
    }

    /**
     * Gets an element from the perspective of index backwards from the last index
     */
    public static <T> T getWithReverseIndex(List<T> list, int reverseIndex) {
        return list.get(list.size() - 1 - reverseIndex);
    }

    /**
     * Maps the values of a List
     */
    public static <T, U> List<U> map(List<T> list, Function<T, U> mapping) {
        List<U> mapped = ListHelper.newArrayList(list.size());
        ListHelper.forEach(list, (T item) -> mapped.add(mapping.apply(item)));
        return mapped;
    }

    /**
     * Creates a new ArrayList
     */
    public static <T> ArrayList<T> newArrayList() {
        return new ArrayList<>();
    }

    /**
     * Creates a new ArrayList
     */
    public static <T> ArrayList<T> newArrayList(int capacity) {
        return new ArrayList<>(capacity);
    }

    /**
     * Creates a new Linked List
     */
    public static <T> LinkedList<T> newLinkedList() {
        return new LinkedList<>();
    }

    /**
     * Removes the last elements of a List, the amount of which specified by a given length
     */
    public static <T> void removeLastItems(List<T> list, int length) {
        IterationHelper.forEach(length, () -> list.remove(list.size() - 1));
    }

    /**
     * Converts an array into an List
     */
    public static <T> List<T> toList(T[] array) {
        List<T> list = ListHelper.newArrayList();
        ArrayHelper.forEach(array, (T item) -> list.add(item));
        return list;
    }

    /**
     * Private Constructor to prevent instantiation
     */
    private ListHelper() {
        super();
    }
}
