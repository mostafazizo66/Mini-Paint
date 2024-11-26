public class Validations {
    public static boolean isValidDouble(String str) {
        try {
            Double.parseDouble(str);
            return !(Double.parseDouble(str) < 0);
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
