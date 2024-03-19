import java.util.Arrays;
import java.util.List;

/**
 * This class holds entries for the toDolist containing task status, priority of task and description of task and
 * additional functionalities such as setting and getting these inputs
 */
public class ToDoListEntry implements Comparable<ToDoListEntry>{
    boolean completeTask;
    private String description;
    private int priority;
    ToDoListEntry(boolean completeTask,int priority,String description){
        this.completeTask = completeTask;
        this.description = description;
        this.priority = priority;
    }
    public String getDescription(){
        return description;
    }
    public int getPriority(){
        return priority;
    }
    public boolean isCompleteTask(){
        return completeTask;
    }
    public void setCompleteTask(boolean completeTask){
        this.completeTask = completeTask;
    }
    public String toString(){
        if(completeTask == false && priority == 3){
            return "Incomplete, High, " + description;
        }
        else if (completeTask == false && priority == 2) {
           return "Incomplete, Medium, " + description;
        }
        else if (completeTask == false && priority == 1) {
            return"Incomplete, Low, " + description;
        }
        if(completeTask == true && priority == 3){
            return"Complete, High, " + description;
        }
        else if (completeTask == true && priority == 2) {
            return "Complete, Medium, " + description;
        }
        else if (completeTask == true && priority == 1) {
            return "Complete, Low, " + description;
        }
       return "Unknown status, Unknown priority " + description;
    }

    @Override
    public int compareTo(ToDoListEntry other) {
        if(this.priority > other.priority){
            return 1;
        } else if (this.priority < other.priority) {
            return -1;
        }
        return 0;
    }
}
