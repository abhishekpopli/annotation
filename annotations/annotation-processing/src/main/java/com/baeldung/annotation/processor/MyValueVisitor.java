package com.baeldung.annotation.processor;

import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor8;

public class MyValueVisitor extends SimpleAnnotationValueVisitor8<Void, Void> {

    @Override
    public Void visitType(TypeMirror t, Void p) {
        System.out.printf(">> classValue: %s\n", t.toString());
        return p;
    }

}