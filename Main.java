import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args){
        List<String> commands = Arrays.asList(
                "i = -1",
                "j = 2 * ++i");
        Executor executor = new Executor();
        for(String command : commands){
            executor.execute(command);
        }
        System.out.println(executor.state);
    }
}