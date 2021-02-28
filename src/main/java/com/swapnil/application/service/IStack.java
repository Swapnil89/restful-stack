package com.swapnil.application.service;

import com.swapnil.application.exception.StackEmptyException;
import com.swapnil.application.exception.StackOverflowException;

public interface IStack<E> {
    E peek() throws StackEmptyException;
    E pop() throws StackEmptyException;
    void push(E element) throws StackOverflowException;
    int capacity();
    boolean isEmpty();
    int size();
}
