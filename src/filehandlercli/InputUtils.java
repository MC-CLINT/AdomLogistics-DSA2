package filehandlercli;

public class InputUtils {
  // Checks if string is a valid integer
  public static boolean isValidInteger(String input) {
    try {
      Integer.parseInt(input);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  // Checks if string is a valid double
  public static boolean isValidDouble(String input) {
    try {
      Double.parseDouble(input);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  // Checks if string is not empty
  public static boolean isNotEmpty(String input) {
    return input != null && !input.trim().isEmpty();
  }
}