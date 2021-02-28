package com.swapnil.application.unit.test;

import com.swapnil.application.exception.StackEmptyException;
import com.swapnil.application.exception.StackOverflowException;
import com.swapnil.application.service.IStack;
import com.swapnil.application.service.LinkedListStack;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class LinkedListStackThreadTest {

    static class StackOperationCallable implements Callable<Boolean>{
        final IStack<Integer> stack;
        final StackOperation operation;
        enum StackOperation{
            PUSH,
            POP,
            PEEK
        }
        public StackOperationCallable(IStack stack, StackOperation operation){
            this.stack = stack;
            this.operation = operation;
        }
        @Override
        public Boolean call(){
            switch (operation){
                case PUSH:
                    return callPush();
                case POP:
                    return callPop();
                case PEEK:
                    return callPeek();
                default:
                    throw new RuntimeException("Invalid Operation");
            }
        }

        private Boolean callPush(){
            try {
                stack.push(1);
            } catch (StackOverflowException e) {
                return false;
            }
            return true;
        }

        private Boolean callPop(){
            try {
                stack.pop();
            } catch (StackEmptyException e) {
                return false;
            }
            return true;
        }

        private Boolean callPeek(){
            try {
                stack.peek();
            } catch (StackEmptyException e) {
                return false;
            }
            return true;
        }
    }

    @Test
    public void threadPushOperationTest() throws InterruptedException, ExecutionException {
        IStack<Integer> stack = new LinkedListStack<>(1);
        ExecutorService service = Executors.newFixedThreadPool(2);
        List<Callable<Boolean>> callableList = new ArrayList<>();
        callableList.add(new StackOperationCallable(stack, StackOperationCallable.StackOperation.PUSH));
        callableList.add(new StackOperationCallable(stack, StackOperationCallable.StackOperation.PUSH));
        List<Future<Boolean>> futures = service.invokeAll(callableList);
        assert ((futures.get(0).get() && futures.get(1).get()) == false);
    }

    @Test
    public void threadPopOperationTest() throws InterruptedException, ExecutionException, StackOverflowException {
        IStack<Integer> stack = new LinkedListStack<>(1);
        stack.push(1);
        ExecutorService service = Executors.newFixedThreadPool(2);
        List<Callable<Boolean>> callableList = new ArrayList<>();
        callableList.add(new StackOperationCallable(stack, StackOperationCallable.StackOperation.POP));
        callableList.add(new StackOperationCallable(stack, StackOperationCallable.StackOperation.POP));
        List<Future<Boolean>> futures = service.invokeAll(callableList);
        assert ((futures.get(0).get() && futures.get(1).get()) == false);
    }

    @Test
    public void threadPeekOperationTest() throws InterruptedException, ExecutionException, StackOverflowException {
        IStack<Integer> stack = new LinkedListStack<>(1);
        stack.push(1);
        ExecutorService service = Executors.newFixedThreadPool(2);
        List<Callable<Boolean>> callableList = new ArrayList<>();
        callableList.add(new StackOperationCallable(stack, StackOperationCallable.StackOperation.PEEK));
        callableList.add(new StackOperationCallable(stack, StackOperationCallable.StackOperation.PEEK));
        List<Future<Boolean>> futures = service.invokeAll(callableList);
        assert ((futures.get(0).get() && futures.get(1).get()) == true);
    }
}
