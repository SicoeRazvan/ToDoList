import login.LoginDbOperations;
import login.LoginServlet;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/tasklist")
public class ListServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");

        if (action != null && action.equals("read")) {
            read(request, response);
        } else if (action != null && action.equals("write")) {
            write(request, response);
        } else if (action != null && action.equals("delete")) {
            delete(request, response);
        } else if (action != null && action.equals("markdone")) {
            markDone(request, response);
        }else if (action != null && action.equals("readDoneTask")) {
            readDoneTask(request, response);
        }
    }

    private void read(HttpServletRequest request, HttpServletResponse response) {

        DbOperations DbOperations = new DbOperations();
        int iduser=-1;

        HttpSession s = request.getSession();
        Object o = s.getAttribute("userid");
        if(o!=null){
            iduser=(Integer)o;
        }

        JSONObject json = new JSONObject();
        try {
            json.put("task", DbOperations.getListOfTasks(iduser));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        returnJsonResponse(response, json.toString());
    }

    private void write(HttpServletRequest request, HttpServletResponse response) {
        String taskName = request.getParameter("task");
        Task task = new Task(taskName);

        int fkuser=-1;

        HttpSession s = request.getSession();
        Object o = s.getAttribute("userid");
        if(o!=null){
            fkuser=(Integer)o;
        }

        if(fkuser!=-1) {

            DbOperations dbOperations = new DbOperations();
            try {
                dbOperations.addTaskToDb(task, fkuser);
            } catch (ClassNotFoundException e) {

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        DbOperations dbOperations = new DbOperations();
        String deletedTask = request.getParameter("id");
        int taskId = Integer.parseInt(deletedTask);

        try {
            dbOperations.deleteTask(taskId);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void markDone(HttpServletRequest req, HttpServletResponse resp) {
        DbOperations dbOperations = new DbOperations();
        String id = req.getParameter("id");

        int idtask = Integer.parseInt(id);

        try {
            dbOperations.markDone(idtask);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void readDoneTask(HttpServletRequest request, HttpServletResponse response) {
        DbOperations DbOperations = new DbOperations();
        int iduser=-1;

        HttpSession s = request.getSession();
        Object o = s.getAttribute("userid");
        if(o!=null){
            iduser=(Integer)o;
        }

        JSONObject json = new JSONObject();
        try {
            json.put("donetasks", DbOperations.getListOfDoneTasks(iduser));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        returnJsonResponse(response, json.toString());
    }

    private void returnJsonResponse(HttpServletResponse response, String jsonResponse) {
        response.setContentType("application/json");
        PrintWriter pr = null;
        try {
            pr = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert pr != null;
        pr.write(jsonResponse);
        pr.close();
    }
}
