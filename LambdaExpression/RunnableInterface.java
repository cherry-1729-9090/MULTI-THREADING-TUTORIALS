package LambdaExpression;

public class RunnableInterface {
    public static void main(String[] args) {

        // implementing runnable using the lambda functions
        Runnable runnable = () ->{
            int sum = 0;
            for(int i = 0;i <= 10;i++){
                sum += i;
            }

            System.out.println("Traditional sum : "+sum);
        };

        new Thread(runnable).start();



        // implementing Thread using lambda 
        new Thread(() -> {
            int sum = 0;
            for(int i = 0;i <= 10;i++){
                sum += i;
            }
            System.out.println("Lambda result of thread : "+ sum);
        }).start();
        
    }
}
