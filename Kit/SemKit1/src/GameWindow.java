import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame { //графическое окно
    private static final int WIDTH = 555; // переменные ширина
    private static final int HEIGHT = 507; // высота

    JButton btnStart, btnExit; // виджеты (пустые ссылки)
    SettingWindow settingWindow; //j-frame позволяющий сделать настройки игры в текущих реалиях
    Map map; // объект показывающий игровое поле

    GameWindow(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // устанавливаем то, что при закрытии окна закрывается приложение, связанное с окном
        setSize(WIDTH, HEIGHT); // устанавливаем размер
        setLocationRelativeTo(null); // объект не располагается относительно чего-либо (он сам по себе и появится по центру)

        setTitle("TicTacToe"); // установили заголовок
        setResizable(false); // менять размер нельзя
        btnStart = new JButton("New Game"); //кнопка
        btnExit = new JButton("Exit"); //кнопка
        settingWindow = new SettingWindow(this); // изначально settingwindow не виден (дефолтное св-во jframe)
        map = new Map(); // создали объект map

        btnExit.addActionListener(new ActionListener() { //кнопка выхода
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // завершаем приложение
            }
        });

        btnStart.addActionListener(new ActionListener() { //кнопка старта
            @Override
            public void actionPerformed(ActionEvent e) {
                settingWindow.setVisible(true); //включаем окно настроек
            }
        });

        JPanel panBottom = new JPanel(new GridLayout(1, 2)); // располагаем кнопки в горизонтальную линию
        // сетка из одной строчки и двух колонок (один слева, другой справа)
        panBottom.add(btnStart); //объекты добовляются на панель
        panBottom.add(btnExit);

        add(panBottom, BorderLayout.SOUTH); //панель добавляется вниз экрана
        add(map);

        setVisible(true); //чтоб был виден объект settingswindow
    }

    void startNewGame(int mode, int sizeX, int sizeY, int winLen){ // вызывается из окна настроек
        map.startNewGame(mode, sizeX, sizeY, winLen); // все значения передаем в map и вызываем метод startnewgame
    }
}