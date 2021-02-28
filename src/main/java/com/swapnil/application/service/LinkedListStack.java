package com.swapnil.application.service;

import com.swapnil.application.exception.StackEmptyException;
import com.swapnil.application.exception.StackOverflowException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LinkedListStack<E> implements IStack<E> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LinkedListStack.class);
    private StackNode<E> root;
    private int count;
    private final int capacity;

    public LinkedListStack(int capacity){
        this.count = 0;
        this.capacity = capacity;
        this.root = null;
    }

    @Override
    public E peek() throws StackEmptyException {
        if(count == 0) {
            StackEmptyException e = new StackEmptyException("Stack is Empty");
            LOGGER.error("Stack is Empty",e);
            throw e;
        }
        LOGGER.debug("peek() is complete");
        return root.getData();
    }

    @Override
    public E pop() throws StackEmptyException {
        if(count == 0) {
            StackEmptyException e = new StackEmptyException("Stack is Empty");
            LOGGER.error("Stack is Empty",e);
            throw e;
        }
        StackNode<E> popElement;
        synchronized (this){
            if(count == 0) {
                StackEmptyException e = new StackEmptyException("Stack is Empty");
                LOGGER.error("Stack is Empty",e);
                throw e;
            }
            popElement = root;
            root = root.getNext();
            count--;
            LOGGER.debug("pop() is complete");
        }
        return popElement.getData();
    }

    @Override
    public void push(E element) throws StackOverflowException {
        if(count == capacity()) {
            StackOverflowException e = new StackOverflowException("Stack is Full");
            LOGGER.error("Stack is Full",e);
            throw e;
        }
        StackNode<E> newNode = new StackNode<>(element,null);
        synchronized (this){
            if(count == capacity()) {
                StackOverflowException e = new StackOverflowException("Stack is Full");
                LOGGER.error("Stack is Full",e);
                throw e;
            }
            if(root == null) root = newNode;
            else {
                StackNode<E> prev = root;
                root = newNode;
                newNode.setNext(prev);
            }
            count++;
        }
        LOGGER.debug("push() is complete");
    }

    @Override
    public int capacity() {
        return this.capacity;
    }

    @Override
    public boolean isEmpty(){
        return this.count == 0 ? true : false;
    }

    @Override
    public int size(){
        return this.count;
    }
}
