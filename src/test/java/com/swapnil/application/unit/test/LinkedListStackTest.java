package com.swapnil.application.unit.test;

import com.swapnil.application.exception.StackEmptyException;
import com.swapnil.application.exception.StackOverflowException;
import com.swapnil.application.service.IStack;
import com.swapnil.application.service.LinkedListStack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LinkedListStackTest {

    @Test
    public void testPeekEmptyStackThrowsException(){
        IStack<Integer> stack = new LinkedListStack<>(1);
        Assertions.assertThrows(StackEmptyException.class, () -> {
            stack.peek();
        });
    }

    @Test
    public void testPeekReturnsElement() throws StackOverflowException, StackEmptyException {
        IStack<Integer> stack = new LinkedListStack<>(1);
        stack.push(1);
        assert(stack.peek() == 1);
    }

    @Test
    public void testPopEmptyStackThrowsException(){
        IStack<Integer> stack = new LinkedListStack<>(1);
        Assertions.assertThrows(StackEmptyException.class, () -> {
            stack.pop();
        });
    }

    @Test
    public void testPopReturnsElement() throws StackOverflowException, StackEmptyException {
        IStack<Integer> stack = new LinkedListStack<>(1);
        stack.push(1);
        Integer element = stack.pop();
        assert(element == 1);
        assert(stack.isEmpty());
    }

    @Test
    public void testPushOverflowStackThrowsException() throws StackOverflowException {
        IStack<Integer> stack = new LinkedListStack<>(1);
        stack.push(1);
        Assertions.assertThrows(StackOverflowException.class, () -> {
            stack.push(2);
        });
    }

    @Test
    public void testPushElement() throws StackOverflowException, StackEmptyException {
        IStack<Integer> stack = new LinkedListStack<>(1);
        stack.push(1);
        assert(stack.peek() == 1);
        assert(stack.size() == 1);
    }

    @Test
    public void testIsEmpty() throws StackOverflowException, StackEmptyException {
        IStack<Integer> stack = new LinkedListStack<>(1);
        stack.push(1);
        assert (stack.isEmpty() == false);
        stack.pop();
        assert(stack.isEmpty());
    }
}
