package com.baeldung.annotation;

import com.baeldung.annotation.processor.LocaleField;
import com.baeldung.annotation.processor.PayModes;
import com.baeldung.annotation.processor.MessageEnum;

public class Person {

    @LocaleField(enumClass= PayModes.class)
    private int displayName;

    @LocaleField(enumClass= MessageEnum.class)
    private String message;


    public int getDisplayName() {
        return displayName;
    }

    public void setDisplayName(int displayName) {
        this.displayName = displayName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
