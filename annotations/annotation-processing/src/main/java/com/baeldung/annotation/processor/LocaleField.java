package com.baeldung.annotation.processor;


import java.lang.annotation.*;
import java.lang.reflect.Array;
import java.util.EnumSet;
import java.util.List;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface LocaleField {

//    Myenum value()  default Myenum.CreditCard;
    Class<? extends Enum<?>> enumClass()  ;
    //     Class<? extends Enum<?>> =
//    String param1() default "abhishek";

}
