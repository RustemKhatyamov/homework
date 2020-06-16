import java.sql.*;
import java.util.ArrayList;

public class DataBase {
    Connection conn;

    public DataBase() {
        connectDB();
    }

    public ArrayList<String> getPaths(String word) {
        ArrayList<String> paths = new ArrayList<String>();
        try {
            String query = "select filePath, words_freq from files inner join \n" +
                "(select files_fileid, words_freq from filewords inner join  words\n" +
                "where filewords.words_words_id = words.words_id and words.words_code = ?) as t1\n" +
                "where files.fileid = t1.files_fileid\n" +
                "order by words_freq desc";
            PreparedStatement state = conn.prepareStatement(query);
            state.setString(1, word);
            ResultSet result = state.executeQuery();

            while (result.next()) {
                String filePath = result.getString("filePath");
                paths.add(filePath);
            }
            state.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paths;
    }

    public void connectDB() {
        try {
            String url = "jdbc:mysql://localhost/study?allowPublicKeyRetrieval=true&serverTimezone=Europe/Moscow&useSSL=false";
            String username = "root";
            String password = "karura";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Соединение установлено!");
        }
        catch (Exception e) {
            System.out.println("Ошибка соединения!");
            System.out.println(e);
        }
    }
}
