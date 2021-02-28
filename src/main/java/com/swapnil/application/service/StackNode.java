package com.swapnil.application.service;

class StackNode <E>{

    private final E data;
    private StackNode<E> next;

    public StackNode(E data, StackNode<E> next){
        this.data = data;
        this.next = next;
    }

    public E getData(){
        return this.data;
    }

    public StackNode<E> getNext(){
        return this.next;
    }

    public void setNext(StackNode<E> next){
        this.next = next;
    }
}