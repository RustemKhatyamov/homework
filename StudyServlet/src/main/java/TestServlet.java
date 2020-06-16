import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;

public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //получаем путь, по которому лежит файл
        String filePath = request.getParameter("filePath");
        File f = new File(filePath);

        response.setContentType("text/plain");
        response.setHeader("Content-disposition", "attachment; filename=" + f.getName());

        //скачивание файла
        Download(f, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //получаем ключ из поля ввода
            PrintWriter writer = response.getWriter();
            String keyWord = request.getParameter("keyWord");

            //запрос к БД
            DataBase db = new DataBase();
            ArrayList<String> paths = db.getPaths(keyWord);

            //возвращение ответа клиенту
            Answer(paths, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Answer(ArrayList<String> paths, PrintWriter writer) {
        if (paths.size() != 0) {
            for (String path : paths) {
                String link = "<p><a href=\"testServlet?filePath=" + path + "\">" + path + "</a>";
                writer.println(link);
            }
        } else
            writer.println("По запросу ничего не найдено");
    }

    private void Download(File f, HttpServletResponse response) {
        if (f.exists()) {
            try (FileInputStream is = new FileInputStream(f);
                 OutputStream os = response.getOutputStream()) {
                byte[] buf = new byte[4096];
                int bytes = -1;

                while ((bytes = is.read(buf)) != -1) {
                    os.write(buf, 0, bytes);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
