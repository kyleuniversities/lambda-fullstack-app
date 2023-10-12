package com.lambda.lambda.common.util.wrapper;

import com.lambda.lambda.common.helper.IntegerHelper;

public final class IntegerWrapper extends Wrapper<Integer> {
    // New Instance Methods
    public static IntegerWrapper newInstance() {
        return IntegerWrapper.newInstance(0);
    }

    public static IntegerWrapper newInstance(Integer value) {
        return new IntegerWrapper(value);
    }

    // Constructor Method
    private IntegerWrapper(Integer value) {
        super(value);
    }

    // Operant Return Method
    public final int getAbsoluteValue() {
        return IntegerHelper.absoluteValue(this.getValue());
    }

    public final int getOppositeValue() {
        return -this.getValue();
    }

    public final int getNextValue() {
        return this.getValue() + 1;
    }

    public final int getPreviousValue() {
        return this.getValue() - 1;
    }

    public final int getValueThenIncrement() {
        int value = this.getValue();
        this.increment();
        return value;
    }

    public final int incrementThenGetValue() {
        this.increment();
        return this.getValue();
    }

    public final int getValueThenDecrement() {
        int value = this.getValue();
        this.decrement();
        return value;
    }

    public final int decrementThenGetValue() {
        this.decrement();
        return this.getValue();
    }

    // Operant Action Methods
    public final void increment() {
        this.increment(1);
    }

    public final void increment(int value) {
        this.setValue(this.getValue() + value);
    }

    public final void decrement() {
        this.decrement(1);
    }

    public final void decrement(int value) {
        this.increment(-value);
    }

    public final void multiply(int value) {
        this.setValue(this.getValue() * value);
    }

    public final void divide(int value) {
        this.setValue(this.getValue() / value);
    }

    public final void mod(int value) {
        this.setValue(this.getValue() % value);
    }

    // Operant Relational Methods
    public final boolean isEqualTo(int value) {
        return this.getValue() == value;
    }

    public final boolean isLessThan(int value) {
        return this.getValue() < value;
    }

    public final boolean isGreaterThan(int value) {
        return this.getValue() > value;
    }

    public final boolean isLessThanOrEqualTo(int value) {
        return this.getValue() <= value;
    }

    public final boolean isGreaterThanOrEqualTo(int value) {
        return this.getValue() >= value;
    }

    public final boolean isNotEqualTo(int value) {
        return this.getValue() != value;
    }
}
