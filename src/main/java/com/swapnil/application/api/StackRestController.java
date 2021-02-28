package com.swapnil.application.api;

import com.swapnil.application.exception.StackEmptyException;
import com.swapnil.application.exception.StackOverflowException;
import com.swapnil.application.model.StackRequestObject;
import com.swapnil.application.model.StackResponseObject;
import com.swapnil.application.service.IStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/stack")
public class StackRestController<E> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StackRestController.class);

    @Autowired
    private IStack<E> stack;

    @ResponseBody
    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public StackResponseObject<E> peek() throws StackEmptyException {
        LOGGER.debug("Received Request for peek()");
        StackResponseObject<E> response = new StackResponseObject.Builder()
                                            .setData(stack.peek().toString())
                                            .build();
        return response;
    }

    @ResponseBody
    @DeleteMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public StackResponseObject<E> pop() throws StackEmptyException {
        LOGGER.debug("Received Request for pop()");
        StackResponseObject<E> response = new StackResponseObject.Builder()
                .setData(stack.pop().toString())
                .build();
        return response;
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public StackResponseObject<E> push(@RequestBody StackRequestObject<E> requestObject) throws StackOverflowException {
        LOGGER.debug(String.format("Received Request for push() : {%s}", requestObject.getData().toString()));
        stack.push(requestObject.getData());
        System.out.println(stack.capacity());
        return new StackResponseObject.Builder().setResponseCode("CREATED").setData(requestObject.getData()).build();
    }
}
