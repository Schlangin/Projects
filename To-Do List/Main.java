import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        ToDoList toDoList = new ToDoList("toDoList");
        Scanner scan = new Scanner(System.in);


        while(true){
            System.out.println(toDoList.toString());

            System.out.println("To Do List Options");
            System.out.println("1. Add task" );
            System.out.println("2. Remove task" );
            System.out.println("3. Display ToDoList" );
            System.out.println("4. Mark Task as Complete" );
            System.out.println("0. Save and exit" );
            System.out.println("Enter option" );

            int option = scan.nextInt();

            switch(option){
                case 1:
                    System.out.println("Enter task priority 1.'High' 2.'Medium' 3.'Low' ");
                    int priority = scan.nextInt();
                    System.out.println("Enter task description ");
                    String description = scan.next();
                    toDoList.addToList(new ToDoListEntry(false,priority,description));
                    break;

                case 2:
                    System.out.println("Enter index of which task you want to remove");
                    int index = scan.nextInt();
                    toDoList.removeFromList(index);
                    Collections.sort(toDoList.entries);
                    break;

                case 3:
                    System.out.println(toDoList.toString());
                    break;
                case 4:
                    System.out.println("Enter index of task you would like to mark as complete ");
                    int temp = scan.nextInt();
                    toDoList.markAsComplete(toDoList.entries.get(temp));
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }
}
