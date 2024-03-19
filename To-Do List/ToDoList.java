import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Manages main methods for toDoList storing entries in a ArrayList sorted by priority high, medium , low.
 * This class handles loading and saving toDoList entries from a file. Also provides functionalities
 * for displaying adding and removing from toDoList
 */
public class ToDoList {
    String filePath;
    List<ToDoListEntry> entries;
    public ToDoList(String filePath){
        this.filePath = filePath;
        entries = new ArrayList<>();
        loadList();
    }
    public void addToList(ToDoListEntry entry){
        entries.add(entry);
        saveList();
    }
    public void removeFromList(int index){
        if(index >= 0 && index < entries.size()){
            entries.remove(index);
        }
    }
    private void loadList(){
        File file = new File(filePath);
        boolean temp = false;
        try (Scanner scanner = new Scanner(file)) {
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if(parts.length == 3){
                    if(parts[0].equalsIgnoreCase("incomplete")){
                        temp = false;
                    } else if (parts[0].equalsIgnoreCase("complete")) {
                        temp = true;
                    }

                    int priority;

                    if(parts[1].trim().equalsIgnoreCase("high")){
                        priority = 1;
                    }
                    else if (parts[1].trim().equalsIgnoreCase("medium")) {
                        priority = 2;
                    }
                    else {
                        priority = 3;
                    }

                    String description = parts[2].trim();
                    entries.add(new ToDoListEntry(temp,priority,description));
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found new File will be created ");
        }
    }
    private void saveList(){
        try(PrintWriter out = new PrintWriter(filePath)){
            for (int i = 0; i < entries.size(); i++) {
                ToDoListEntry entry = entries.get(i);
                out.println(entry.isCompleteTask() + "," + entry.getPriority() + "," + entry.getDescription());
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
    public void markAsComplete(ToDoListEntry entry){
        entry.setCompleteTask(true);
    }
    public String toString(){
        String output = "";
        for (int i = 0; i < entries.size(); i++) {
            output += (i + ". " + entries.get(i).toString() + "\n");
        }
        return output;
    }
}
