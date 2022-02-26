import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args){
        List<String> expressions = Arrays.asList(
                "i = 1",
                "j = 2 * ++i");
        Executor executor = new Executor();
        for(String expression : expressions){
            executor.calculate(expression);
        }
        System.out.println(executor.varsState);
    }
}