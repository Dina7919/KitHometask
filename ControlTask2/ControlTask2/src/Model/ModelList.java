package Model;

import java.util.List;
import java.util.Scanner;

import Controller.iGetModel;
// класс ModelList реализует интерфейс iGetModel
public class ModelList implements iGetModel {
    private List<Toys> toys;
    private List<Toys> chosenToys;
    //конструктор 
    public ModelList(List<Toys> toys, List<Toys> chosenToys) {
        this.toys = toys;
        this.chosenToys = chosenToys;
    }

    //метод получения всх игрушек
    public List<Toys> getAllToys()
    {
        return toys;
    }

    //метод получения выбранных игрушек
    public List<Toys> getChosenToys()
    {
        return chosenToys;
    }

    //метод добавления игрушки
    public List<Toys> addToy()
    {
        ModelFile fModel2 = new ModelFile("Toys.txt");
        Scanner in = new Scanner(System.in);
        System.out.println("Введите название игрушки: ");
        String name = in.nextLine();
        System.out.println("Введите количество игрушек: ");
        int quantity = in.nextInt();
        System.out.println("Введите частоту выпадения игрушки (от 0 до 1): ");
        double weight = in.nextDouble();
        System.out.println("Введите id игрушки: ");
        int id = in.nextInt();
        Toys toy = new Toys(name, quantity, weight, id); 
        toys.add(toy);
        fModel2.saveAllToysToFile(toys);
        return toys;
    }

    //метод изменения частоты выпадения всх игрушек
    public List<Toys> changeToy(int numberOfToy)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите вес: ");
        double weight = in.nextDouble();
        toys.get(numberOfToy-1).setWeight(weight);
        return toys;
    }

    //выбор одной игрушки
    public List<Toys> chooseOneToy(int numberOfToy)
    {
        ModelFile fModel = new ModelFile("ChosenToys.txt");
        fModel.deleteDataChosenToys();
        if(toys.get(numberOfToy-1).getWeight() < 0.5)
        {
            System.out.println("Частота выпадения игрушки меньше 0.5. Выберите другую игрушку");
        }else{
            chosenToys.add(toys.get(numberOfToy-1));
            toys.remove(numberOfToy-1);
        }
        System.out.println("Список выбранных игрушек: ");
        System.out.println(chosenToys);
        fModel.saveChosenToysToFile(chosenToys);
        return chosenToys;
    }

}