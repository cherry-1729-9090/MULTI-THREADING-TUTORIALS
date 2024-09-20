package LambdaExpression;

public class LambdaFile {
    
    public static void main(String[] args) {
        HelloWorldInterface hw = () ->{
            return "Hello World";
        };

        System.out.println(hw.sayHello());
        

        // No need to provide any data type as java complier will automatically converts the data type.
        IncrementByFiveLambda icf = (n) ->{
            return n + 5;
        };


        System.out.println(icf.incrementByFive(2));


    }
}
