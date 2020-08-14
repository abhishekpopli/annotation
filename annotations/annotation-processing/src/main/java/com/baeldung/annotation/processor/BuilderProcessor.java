package com.baeldung.annotation.processor;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

import com.google.auto.service.AutoService;

@SupportedAnnotationTypes("com.baeldung.annotation.processor.LocaleField")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class BuilderProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        List<String> activitiesWithPackage = new ArrayList<>();
        List<String> enumvalues = new ArrayList<>();
        MyValueVisitor valueVisitor = new MyValueVisitor();

        for (TypeElement typeElement : annotations) {
//        for (Element typeElement : roundEnv.getElementsAnnotatedWith(LocaleField.class)) {
//
//            List<? extends AnnotationMirror> annotationMirrors = typeElement.getAnnotationMirrors();
//            for (AnnotationMirror annotationMirror : annotationMirrors) {
//                Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues
//                        = annotationMirror.getElementValues();
//                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry
//                        : elementValues.entrySet()) {
//                   entry.getValue().accept(valueVisitor, null);
//                }
//            }
            for (Element element : roundEnv.getElementsAnnotatedWith(typeElement)) {
                if (element.getKind() == ElementKind.FIELD) {
                    String fullTypeClassName = element.getSimpleName().toString();
//                    activitiesWithPackage.add(fullTypeClassName);
                    LocaleField anno = element.getAnnotation(LocaleField.class);


                    try {
                        anno.enumClass().getName();
                    } catch (MirroredTypeException e) {
                        TypeMirror typeMirror = e.getTypeMirror();
                        Types TypeUtils = this.processingEnv.getTypeUtils();
                        TypeElement finalele = (TypeElement) TypeUtils.asElement(typeMirror);
                        finalele.getEnclosingElement().getSimpleName().toString();
                        List<Element> a = finalele.getEnclosedElements().stream()
                                .filter(v -> v.getKind().equals(ElementKind.ENUM_CONSTANT))
                                .collect(Collectors.toList());
                        activitiesWithPackage.add(a.stream().map(Object::toString).collect(Collectors.joining(",")));
                    }
                }
            }
        }
//
////                    enumvalues=Arrays.stream(
////                            anno.value().getClass().getEnumConstants()).map(v->v.name())
////                            .collect(Collectors.toList());
////                    activitiesWithPackage.addAll(enumvalues);
//                }

//                    TypeMirror value = null;
//                    if (anno != null) {
//                        try {
//                            anno.enumClass();
//                        } catch (MirroredTypeException mte) {
//                            value = mte.getTypeMirror();
//                        }
//                    }
////                    for (Enum e : value.getAnnotation(Myenum.class).enumClass().getEnumConstants()) {
//                    for (Enum e : value.g) {
//                          for(Object obj: myclass.getEnumConstants()) {
//                    for(Myenum myenum: anno.arr()){
//                        activitiesWithPackage.add(myenum.toString());
//                    }





        try {
            writeBuilderFile(activitiesWithPackage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void writeBuilderFile(List<String> activitiesWithPackage ) throws IOException {

        String packageName = "com.baeldung.annotation";
        String builderClassName = "PersonBuilder";

        ObjectOutputStream printWriter =  new ObjectOutputStream(new FileOutputStream("output.txt",true));
        PrintWriter printWriter2 =  new PrintWriter(new FileOutputStream("output2.txt", true));

        printWriter.writeObject(activitiesWithPackage);
        for(String s: activitiesWithPackage)
        {printWriter2.println(s);}
        printWriter.close();
        printWriter2.close();

        ArrayList<String> newList = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream("output.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            newList = (ArrayList<String>) in.readObject();
            for (String name : newList) {
                printWriter2.println(name);
            }
            in.close();
            fileIn.close();
            printWriter2.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return;
        }
//        JavaFileObject builderFile = processingEnv.getFiler().createSourceFile(builderClassName);
//        try (PrintWriter out = new PrintWriter(builderFile.openWriter())) {
//
//            if (packageName != null) {
//                out.print("package ");
//                out.print(packageName);
//                out.println(";");
//                out.println();
//            }
//
//            out.print("public class ");
//            out.print(builderClassName);
//            out.println(" {");
//            out.println();
//
//            for (String str : activitiesWithPackage) {
//                out.print( "//");
//                out.println(str);
//                out.println();
//            }
//
//            out.println("}");
//
//        }
    }

}
