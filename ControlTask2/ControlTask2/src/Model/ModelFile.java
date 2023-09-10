package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// класс ModelFile реализует интерфейс iGetModel
import Controller.iGetModel;
//ModelFile представляет модель файла.
public class ModelFile  implements iGetModel {
    private String fileName;

    public ModelFile(String fileName) {
        this.fileName = fileName;

        try(FileWriter fw = new FileWriter(fileName, true))
        {
            fw.flush();    
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //метод получения всх игрушек 
    @Override
    public List<Toys> getAllToys() {
        List<Toys> toys  = new ArrayList<Toys>();
        try
        {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while(line!=null)
            {
                String[] param = line.split(" ");
                String first = param[0];
                Toys toy = new Toys(first, Integer.parseInt(param[1]), Double.parseDouble(param[2]), Integer.parseInt(param[3]));
                toys.add(toy);
                line = reader.readLine();
            }

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

        return toys;
    }

    //метод удаления игрушек с файла
    public void deleteDataToys()
    {
        try {
            FileWriter fwOb = new FileWriter("Toys.java", false); 
            PrintWriter pwOb = new PrintWriter(fwOb, false);
            pwOb.flush();
            pwOb.close();
            fwOb.close();
        } catch (Exception e) {
            System.err.println("Ошибка удаления данных с файла: " + e.getMessage());}
    }

    ////метод удаления выбранных игрушек с файла
    public void deleteDataChosenToys()
    {
        try {
        FileWriter fwOb = new FileWriter("ChosenToys.java", false); 
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        fwOb.close();
        } catch (Exception e) {
            System.err.println("Ошибка удаления данных с файла: " + e.getMessage());
        }
    }

    //метод сохранения всх игрушек в файл
    public void saveAllToysToFile(List<Toys> toys)
    {
        try(FileWriter fw = new FileWriter(fileName, true))
        {
            for(Toys toy : toys)
            {
                fw.write("Toy - " + toy.getName()+", quantity - "+toy.getQuantity()+", weight - "+toy.getWeight() + " id - " + toy.getId());
                fw.append('\n');
            }
            fw.flush();    
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //Получение всех выбранных игрушек
    @Override
    public List<Toys> getChosenToys() {
        List<Toys> chosenToys  = new ArrayList<Toys>();
        try
        {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while(line!=null)
            {
                String[] param = line.split(" ");
                String first = param[0];
                Toys toy = new Toys(first, Integer.parseInt(param[1]), Double.parseDouble(param[2]), Integer.parseInt(param[3]));
                chosenToys.add(toy);
                line = reader.readLine();
            }

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

        return chosenToys;
    }
    
    //сохранение выбранных игрушек в файл
    public void saveChosenToysToFile(List<Toys> chosenToys)
    {
        try(FileWriter fw = new FileWriter(fileName, true))
        {
            for(Toys toy : chosenToys)
            {
                fw.write("Chosen toy - " + toy.getName()+", quantity - "+toy.getQuantity()+", weight - "+toy.getWeight() + " id - " + toy.getId());
                fw.append('\n');
            }
            fw.flush();    
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public List<Toys> addToy() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addToy'");
    }

    @Override
    public List<Toys> chooseOneToy(int numberOfToy) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'chooseOneToy'");
    }

    @Override
    public List<Toys> changeToy(int numberOfToy) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'changeToy'");
    }

    
}