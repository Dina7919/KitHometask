package Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Model.Toys;
import Model.ModelFile;
// метод посредством которого происходит контроль процесса работы программы
public class Controller {

    private iGetModel model;
    private iGetView view;
    
    private List<Toys> toys = new ArrayList<>();
    private List<Toys> chosenToys = new ArrayList<>();

    //Конструктор контроллера

    public Controller(iGetModel model, iGetView view) {
        this.model = model;
        this.view = view;
    }

    //Проверяем наличае игрушек в массиве

    private boolean testData(List<Toys> toys)
    {
        if(toys.size()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //метод обновления игрушек

    public void getToysToUpdate()
    {
        //MVP
        ModelFile fModel1 = new ModelFile("Toys.txt");
        fModel1.deleteDataToys();
        toys = model.getAllToys();
        if(testData(toys))
        {
           view.printAllToys(toys);
        }
        else
        {
            System.out.println("Список игрушек пуст!");
        }
        fModel1.saveAllToysToFile(toys);
    }

    //Проверяем наличае выбранных пользователем игрушек в массиве

    private boolean testData1(List<Toys> chosenToys)
    {
        if(chosenToys.size()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //Обновляем список выбранных игрушек

    public void getChosenToysToUpdate()
    {
        //MVP
        chosenToys = model.getChosenToys();
        if(testData1(chosenToys))
        {
           view.printChosenToys(chosenToys);
        }
        else
        {
            System.out.println("Список игрушек пуст!");
        }
    }

    // запуск программы
    public void run()
    {
        Command com = Command.NONE;
        boolean getNewIteration = true;
        while(getNewIteration)
        {
            String command = view.prompt("Введите команду: " +  //
                    "\"\r\n" + //
                    "                    \"    NONE, " + //
                    "\"\r\n" + //
                    "                    \"    ADD, " + //
                    "\"\r\n" + //
                    "                    \"    UPDATE, " + //
                    "\"\r\n" + //
                    "                    \"    LIST, " + //
                    "\"\r\n" + //
                    "                    \"    CHOOSE, " + //
                    "\"\r\n" + //
                    "                    \"    CHANGE, " + //
                    "\"\r\n" + //
                    "                    \"    EXIT\" " + '\n');
            com = Command.valueOf(command.toUpperCase());
            switch(com)
            {
                case EXIT: //в случае выхода из программы
                    getNewIteration=false;
                    System.out.println("Выход из программы!");
                    break;
                case LIST: //выпадание списка игрушек
                    view.printAllToys(model.getAllToys());
                    break;
                case CHOOSE: //выбор одной игрушки в зависимости от частоты выпадения
                    chosenToys = model.getChosenToys();
                    Scanner in = new Scanner(System.in);
                    System.out.println("Введите номер игрушки: ");
                    int numberOfToy = in.nextInt();
                    model.chooseOneToy(numberOfToy);
                    break;
                case UPDATE: //обновление списка игрушек
                    getToysToUpdate();
                    System.out.println("Список игрушек обновлён!");
                    break;
                case ADD: //добавление новой игрушки
                    model.addToy();
                    System.out.println("Добавлена новая игрушка!");
                    break;
                case CHANGE: //изменение частоты выпадения игрушки
                    Scanner in1 = new Scanner(System.in);
                    System.out.println("Введите номер игрушки, частоту выпадения которой хотите поменять: ");
                    int numberOfToy1 = in1.nextInt();
                    model.changeToy(numberOfToy1);
                    break;
            }

        }
    }

    
}