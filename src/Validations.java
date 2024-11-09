public class Validations {
    public static boolean isValidDouble(String str) {
        try {
            Double.parseDouble(str);
            if(Double.parseDouble(str) < 0)
                return false;
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean isValidLength(String str) {
        try {
            Double.parseDouble(str);
            return !(Double.parseDouble(str) <= 0);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
