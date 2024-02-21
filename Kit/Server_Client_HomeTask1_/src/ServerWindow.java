import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ServerWindow extends JFrame { //графическое окно
    public static final int WIDTH = 400; // переменные ширина
    public static final int HEIGHT = 300; // высота
    public static final String LOG_PATH = "src/server/log.txt"; // путь для сохранения истории переписки

    List<ClientGUI> clientGUIList; // список клиентов

    JButton btnStart, btnStop; // виджеты
    JTextArea log; // переменная log - текстовое поле в графическом пользовательском интерфейсе
    boolean work; // флаг

    public ServerWindow(){
        clientGUIList = new ArrayList<>(); // иниуиализация

        // стартовые настройки:
        setDefaultCloseOperation(EXIT_ON_CLOSE); // при закрытии окна приложение должно быть завершено
        setSize(WIDTH, HEIGHT); // устанавливаем размер
        setResizable(false); // нельзя изменить в размере
        setTitle("Chat server"); //  заголовком "Chat server"
        setLocationRelativeTo(null);

        createPanel(); // будет создан и отображен panel (панель) окна

        setVisible(true); // показывает окно пользователям
    }

    public boolean connectUser(ClientGUI clientGUI){ // метод для подключения пользователя
        if (!work){ // если мы включены
            return false;
        }
        clientGUIList.add(clientGUI); // пользователь добавляется в общий список пользователей
        return true;
    }

    public String getLog() { // метод для получения историй переписки ( с прошлого раза)
        return readLog();
    }

    public void disconnectUser(ClientGUI clientGUI){ // метод, который отключает пользователя (выбирается пользователь)
        clientGUIList.remove(clientGUI); // удаляем его из списка
        if (clientGUI != null){
            clientGUI.disconnectFromServer(); // у клиента тоже вызываем, чтоб он понял, что его тоже отключили
        }
    }

    public void message(String text){ //  им пользуются клиенты, чтоб отправить сообщение на сервер (передается отправляемый текст)
        if (!work){ // проверка флага
            return;
        }
        appendLog(text); // метод добавления текста в центральное окно
        answerAll(text); // метод, который перебирает всех клиентов и передает им то же самое сообщение
        saveInLog(text); // метод, который сохраняет историю переписки
    }

    private void answerAll(String text){ // метод, который перебирает всех клиентов и передает им то же самое сообщение
        for (ClientGUI clientGUI: clientGUIList){
            clientGUI.answer(text);
        }
    }

    private void saveInLog(String text){ // метод, который сохраняет историю переписки
        try (FileWriter writer = new FileWriter(LOG_PATH, true)){ // через флаг true мы добавляем новую строчку
            writer.write(text);
            writer.write("\n");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private String readLog(){ // метод чтения файла
        StringBuilder stringBuilder = new StringBuilder(); // с его помощью все собираем и передаем информацию
        try (FileReader reader = new FileReader(LOG_PATH);){
            int c;
            while ((c = reader.read()) != -1){ // идет чтение по-символьно
                stringBuilder.append((char) c);
            }
            stringBuilder.delete(stringBuilder.length()-1, stringBuilder.length()); // удаляет последний символ переноса строки
            return stringBuilder.toString();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private void appendLog(String text){ // метод добавления текста в центральное окно
        log.append(text + "\n");
    }

    private void createPanel() {
        log = new JTextArea(); // центральное текстовое поле
        add(log);
        add(createButtons(), BorderLayout.SOUTH); // кнопки включить и выключить
    }

    private Component createButtons() { // создаем панель
        JPanel panel = new JPanel(new GridLayout(1, 2)); // сетка 1 на 2
        btnStart = new JButton("Start"); // создали и добавили кнопки на панель
        btnStop = new JButton("Stop");

        btnStart.addActionListener(new ActionListener() { // добавили слушатели нажатий к кнопкам
            @Override
            public void actionPerformed(ActionEvent e) {
                if (work){
                    appendLog("Server has already been started"); // при нажатии на кнопку старт
                } else {
                    work = true;
                    appendLog("Server is started!");
                }
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!work){
                    appendLog("Server has already been stoped"); // при нажатии на кнопку стоп
                } else {
                    work = false;
                    while (!clientGUIList.isEmpty()){ // отключение пользователей
                        disconnectUser(clientGUIList.get(clientGUIList.size()-1)); // удаляет клиента из списка клиентов
                    }
                    appendLog("Server is stoped!");
                }
            }
        });

        panel.add(btnStart); // добовляем кнопки на паннель
        panel.add(btnStop);
        return panel;
    }
}