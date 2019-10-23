package tasks;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String user_name = "postgres";
        String password = "QAZedctgb123";
        String connection_url = "jdbc:postgresql://localhost:5432/file_info";
        ImageLoader imageLoader = ImageLoader.create(connection_url, user_name,password);
        imageLoader.load(
                "https://bipbap.ru/wp-content/uploads/2017/10/0_8eb56_842bba74_XL.jpg", "/Users/nowhere/Desktop");
    }

}
