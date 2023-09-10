package Controller;

import java.util.List;

import Model.Toys;
// интерфейс, содержащий методы вывода информации об игрушках
public interface iGetView {

    // Метод выводит информацию о каждой игрушке на консоль
    void printAllToys(List<Toys> toys);
    // Метод выводит информацию о каждой выбранной игрушке на консоль
    void printChosenToys(List<Toys> chosenToys);

    // Метод для запроса ввода пользователя, чтобы получить команду или данные от него
    String prompt(String msg);

    // Метод для обновления игрушек.
    Long getToysToUpdate();
    
}
