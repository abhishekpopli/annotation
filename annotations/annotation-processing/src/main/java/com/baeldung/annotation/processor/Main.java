package com.baeldung.annotation.processor;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            Class aClass = Class.forName("com.baeldung.annotation.processor.BuilderProperty");
            System.out.println(Arrays.toString(aClass.getEnumConstants()));
        }catch (Exception e){

        }
    }
}
