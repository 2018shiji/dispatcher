package com.module.dispatcher;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Scanner;

@SpringBootTest
class DispatcherApplicationTests {

    @Test
    void contextLoads() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        String[] split = str.split(",");
        int[] splitInt = new int[split.length];
        for(int i = 0; i < split.length; i++){
            splitInt[i] = Integer.parseInt(split[i]);
        }
    }

}
