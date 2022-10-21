package domain;

import java.util.ArrayList;

public class Epic extends Task{
    private String epicName;
    private ArrayList<Integer> taskIds = new ArrayList<>();

    public void setTaskIds(ArrayList<Integer> taskIds) {
        this.taskIds = taskIds;
    }

    public ArrayList<Integer> getTaskIds() {
        return taskIds;
    }

    public Epic(int id, String description, String status, String epicName) {
        super(id,"null", description, status);
        this.epicName = epicName;
    }


    public String getEpicName() {
        return epicName;
    }

    public void setEpicName(String epicName) {
        this.epicName = epicName;


    }
}
