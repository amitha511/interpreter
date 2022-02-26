package tokens;

public abstract class AbstractTokenAnalyzer {
    public boolean isVariable(String token){
        return Character.isAlphabetic(token.charAt(0));
    }

    public boolean isInteger(String token){
        return tryParseInt(token) != Integer.MIN_VALUE;
    }

    public boolean isDouble(String token){
        return tryParseDouble(token) != -1.0;
    }

    private int tryParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return Integer.MIN_VALUE;
        }
    }

    private double tryParseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return -1.0;
        }
    }
}
