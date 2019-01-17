public class Task {
    private long id;
    private String task;
    private int taskStatus;

    public Task(){

    }

    public Task(String taskName){
        this.task = taskName;
        taskStatus = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }
}
