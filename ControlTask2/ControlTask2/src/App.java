import java.util.ArrayList;
import java.util.List;

import Controller.Controller;
import Controller.iGetModel;
import Controller.iGetView;
import Model.ModelList;
import Model.Toys;
import View.View;

public class App {
    public static void main(String[] args) throws Exception {
       // System.out.println("Hello, World!");

       List<Toys> toys = new ArrayList<Toys>();
       Toys t1 = new Toys("Bear", 10, 0.8, 1);
       Toys t2 = new Toys("Rabbit",  22, 0.6, 2);
       Toys t3 = new Toys("Ball", 13, 0.3, 3);
       Toys t4 = new Toys("skipping rope", 15, 0.5, 4);
       Toys t5 = new Toys("Car", 5, 0.9, 5);
       Toys t6 = new Toys("hopalong",  23, 0.6, 6);
       toys.add(t1);
       toys.add(t2);
       toys.add(t3);
       toys.add(t4);
       toys.add(t5);
       toys.add(t6);
       List<Toys> chosenToys = new ArrayList<Toys>();

       iGetModel model = new ModelList(toys, chosenToys);
       iGetView view = new View();

       Controller control = new Controller(model, view);
       
       control.run();

    }
}