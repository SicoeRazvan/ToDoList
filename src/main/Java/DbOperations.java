import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbOperations {
    private final String URL = "jdbc:postgresql://localhost:5432/todolist";
    private final String USERNAME = "postgres";
    private final String PASSWORD = "12345";

    public List getListOfTasks(int fkuser) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");

        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        PreparedStatement pSt = conn.prepareStatement("SELECT id,task FROM lista WHERE task_status=0 and fkuser="+ fkuser);
        ResultSet rs = pSt.executeQuery();

        List<Task> tasksList = new ArrayList<>();
        while (rs.next()) {
            Task t = new Task();

            t.setId(rs.getLong("id"));
            t.setTask(rs.getString("task"));
            tasksList.add(t);
        }

        pSt.close();
        conn.close();

        return tasksList;
    }

    public void addTaskToDb(Task t, int fkuser) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        int status = 0;
        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        PreparedStatement pSt = conn.prepareStatement("INSERT INTO lista (task,task_status,fkuser) VALUES (?,?,?)");
        pSt.setString(1, t.getTask());
        pSt.setInt(2, t.getTaskStatus());
        pSt.setInt(3, fkuser);


        int rowsInserted = pSt.executeUpdate();

        pSt.close();

        conn.close();
    }

    public void deleteTask(int id) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        PreparedStatement pSt = conn.prepareStatement("DELETE FROM lista WHERE id=?");
        pSt.setInt(1, id);

        int rowsInserted = pSt.executeUpdate();

        pSt.close();

        conn.close();
    }

    public void markDone(int id) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        PreparedStatement pSt = conn.prepareStatement("UPDATE lista SET task_status=1 WHERE id=?");
        pSt.setInt(1, id);


        int rowsInserted = pSt.executeUpdate();

        pSt.close();

        conn.close();
    }

    public List getListOfDoneTasks(int fkuser) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");

        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        PreparedStatement pSt = conn.prepareStatement("SELECT id,task FROM lista WHERE task_status='1' AND fkuser="+ fkuser );
        ResultSet rs = pSt.executeQuery();

        List<Task> tasksList = new ArrayList<>();
        while (rs.next()) {
            Task t = new Task();

            t.setId(rs.getLong("id"));
            t.setTask(rs.getString("task"));
            tasksList.add(t);
        }

        pSt.close();
        conn.close();

        return tasksList;
    }
}