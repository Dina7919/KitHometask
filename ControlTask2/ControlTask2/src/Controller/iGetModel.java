package Controller;

import java.util.List;

import Model.Toys;
import Model.Toys;
// интерфейс, который содержет в себе методы работы с игрушками
public interface iGetModel {
    //метод получения всех игрушек
    public List<Toys> getAllToys(); 
    //метод получения всех выбранных игрушек
    public List<Toys> getChosenToys(); 
    //метод выбора игрушки, принимающий номер игрушки, который вводит пользователь
    public List<Toys> chooseOneToy(int numberOfToy); 
    //метод добавления новой игрушки
    public List<Toys> addToy();
    //метод изменения выбадения веса игрушки
    public List<Toys> changeToy(int numberOfToy);
}