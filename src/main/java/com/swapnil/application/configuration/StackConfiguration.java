package com.swapnil.application.configuration;

import com.swapnil.application.service.IStack;
import com.swapnil.application.service.LinkedListStack;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StackConfiguration {
    @Bean
    IStack<Integer> stack(@Value("${stack.size}") int size){
        return new LinkedListStack<>(size);
    }
}
