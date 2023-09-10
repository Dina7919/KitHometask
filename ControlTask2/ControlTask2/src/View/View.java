//печатает все игрушки

package View;

import java.util.List;
import java.util.Scanner;

import Controller.iGetView;
import Model.Toys;
import Model.ModelFile;


public class View implements iGetView {

    public void printAllToys(List<Toys> toys)
    {
        ModelFile fModel = new ModelFile("Toys.txt");
        fModel.deleteDataToys();
        System.out.println("------список игрушек--------");
        for(Toys toy: toys)
        {
            System.out.println(toy);
        }
        System.out.println("===============================");
        fModel.saveAllToysToFile(toys);
    }
    public void printChosenToys(List<Toys> chosenToys)
    {
        System.out.println("------список выбранных игрушек--------");
        for(Toys toy: chosenToys)
        {
            System.out.println(toy);
        }
        System.out.println("===============================");
    }

    @Override
    public String prompt(String msg) {
       Scanner in = new Scanner(System.in);
       System.out.print(msg);
       return in.nextLine();
    }

    @Override
    public Long getToysToUpdate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getToysToUpdate'");
    }



    
}