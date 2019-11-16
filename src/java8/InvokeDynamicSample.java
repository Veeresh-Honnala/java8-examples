package java8;


import java.io.PrintStream;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/*
https://www.youtube.com/watch?v=qaf8sHjeQ2g
https://github.com/headius/indy_deep_dive/blob/master/src/main/java/BasicHandles.java
*/
public class InvokeDynamicSample {
    private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();
    private static final MethodHandles.Lookup PUBLIC_LOOKUP = MethodHandles.publicLookup();

    public static void main(String[] args) throws Throwable {

        //method Invocation
        //String val = System.getProperty("java.home");
        //System.out.println("Hello, World");

        //getProperty Signature
        MethodType type1 = MethodType.methodType(String.class, String.class);
        //println Signature
        MethodType type2 = MethodType.methodType(void.class, Object.class);

        MethodHandle getPropertyMH = LOOKUP.findStatic(System.class, "getProperty", type1);
        MethodHandle printMH = LOOKUP.findVirtual(PrintStream.class, "println", type2);
        String val = (String) getPropertyMH.invoke("javas.home");
        printMH.invoke(System.out, (Object) "Hello, World");
        System.out.println(val);

        //get Field Example.
        //PrintStream out1 = System.out;
        MethodHandle systemOutMH = LOOKUP.findStaticGetter(System.class, "out", PrintStream.class);
        PrintStream out = (PrintStream) systemOutMH.invoke();
        out.println("from invoker");

        //set Field Example
        //Sample sample = new Sample()
        //sample.name = "Veeresh";
        class Sample {
            public String name;
        }

        MethodHandle setNameSample = LOOKUP.findSetter(Sample.class, "name", String.class);
        Sample sample = new Sample();
        setNameSample.invoke(sample, "Veeresh");
        System.out.println(sample.name);

        //insert Example
        MethodHandle getJavaHomeProperty = MethodHandles.insertArguments(getPropertyMH, 0, "java.home");
        MethodHandle systemOutPrintMH = MethodHandles.insertArguments(printMH, 0, System.out);
        getJavaHomeProperty.invokeWithArguments();
        systemOutPrintMH.invokeWithArguments("invokeWithArguments");

        //pointer to UpperClassifier toUpperCase

        MethodHandle toUpperCaseMH = LOOKUP.findVirtual(String.class, "toUpperCase", MethodType.methodType(String.class));
        MethodHandle objectToUpperCaseMH = toUpperCaseMH.asType(MethodType.methodType(Object.class, Object.class));
        MethodHandle upperCasePrintlnMH = MethodHandles.filterArguments(systemOutPrintMH, 0, objectToUpperCaseMH);
        upperCasePrintlnMH.invokeWithArguments("print in uppercase");
    }
}