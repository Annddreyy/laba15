import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = "::1 128.134.145.145 1.22.256.48 ::: -1.88 ::1";
        text = scanner.nextLine();
        Pattern pattern1 = Pattern.compile("" +
                "(((\\d|[a-z]){4}:){7}((\\d|[a-z]){4}))" + "|" +
                "((([1-9]|[a-z]){1,4}:){7}([1-9]|[a-z]){1,4})" + "|" +
                "::(([1-9]|[a-z]){1,4}:){1,6}(([1-9]|[a-z]){1,4})?" + "|" +
                "::(([1-9]|[a-z]){1,4})" + "|" +
                "((([1-9]|[a-z]){1,4}:):((([1-9]|[a-z]){1,4}:){0,5})(([1-9]|[a-z]){1,4})?)" + "|" +
                "((([1-9]|[a-z]){1,4}:){2}:((([1-9]|[a-z]){1,4}:){0,4})(([1-9]|[a-z]){1,4})?)" + "|" +
                "((([1-9]|[a-z]){1,4}:){3}:((([1-9]|[a-z]){1,4}:){0,3})(([1-9]|[a-z]){1,4})?)" + "|" +
                "((([1-9]|[a-z]){1,4}:){4}:((([1-9]|[a-z]){1,4}:){0,2})(([1-9]|[a-z]){1,4})?)" + "|" +
                "((([1-9]|[a-z]){1,4}:){5}:((([1-9]|[a-z]){1,4}:)?)(([1-9]|[a-z]){1,4})?)"
        );
        Pattern pattern2 = Pattern.compile("((\\d|[1-9]\\d|1\\d{2}|2[0-4][0-9]|25[0-5])\\.){3}(\\d|[1-9]\\d|1\\d{2}||2[0-4][0-9]|25[0-5])");
        int count = 0;
        try (FileWriter fw = new FileWriter("answer.txt")) {
            Matcher matcher = pattern1.matcher(text);
            while (matcher.find()){
                fw.write(matcher.group(0) + " - " + "корректный ip v6" + "\n");
                count += 1;
            }
            matcher = pattern2.matcher(text);
            while (matcher.find()){
                fw.write(matcher.group(0) + " - " + "корректный ip v4" + "\n");
                count += 1;
            }
            if (count == 0){
                fw.write("Нет верных ip адресов");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}