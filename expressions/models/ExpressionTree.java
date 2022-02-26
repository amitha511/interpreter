package expressions.models;

public class ExpressionTree {
    public ExpressionTree left;
    public ExpressionTree right;
    String val;

    public ExpressionTree(String val){
        this.val = val;
    }

    public String getVal(){
        return val;
    }
}
