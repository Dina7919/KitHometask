import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingWindow extends JFrame {
    private static final int WIDTH = 230;
    private static final int HEIGHT = 350;
    private int gameMode;
    private int fieldSize;
    private int victoryLength;

    JButton btnStart;

    SettingWindow(GameWindow gameWindow) { // создаем кнопку
        btnStart = new JButton("Start new game");

        setLocationRelativeTo(gameWindow); // позволяет расположить объект относительно другого объекта
        setLocation(getX() - WIDTH/2, getY() - HEIGHT/2);// левый верхний угол окна настроек появится по центру объекта gamewindow
        setSize(WIDTH, HEIGHT); // устанавливается размер

        btnStart.addActionListener(new ActionListener() { // кнопка
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // делает окно настроек невидимым (мы настроили, нажали кнопку старт и окно настроек скрывается)
                gameWindow.startNewGame(gameMode, fieldSize, fieldSize, victoryLength);
            }
        });
        add(createMainPanel());
        add(btnStart, BorderLayout.SOUTH); // вызывается у объекта jframe. Добовляет объект
        // если нет уточнений, то объект добовляется по середине экрана, заполняя экран
    }

    public JPanel createModePanel() { //Этот код создает и возвращает панель с режимом игры для пользовательского интерфейса
        JPanel jPanel = new JPanel(new GridLayout(3, 1)); // Создается новая панель jPanel с GridLayout размером 3 строки и 1 столбец.
        JLabel jl = new JLabel("Choose game's regime"); // Создается надпись jl с текстом "Choose game's regime".
        JRadioButton rb1 = new JRadioButton("Human against AI"); // Создаются две радио кнопки rb1 и rb2 с текстом "Human against AI" и "Human against Human" соответственно.
        JRadioButton rb2 = new JRadioButton("Human against Human"); //
        ButtonGroup bg = new ButtonGroup(); // Создается группа кнопок bg, чтобы только одна из кнопок могла быть выбрана.
        rb1.setSelected(true); // По умолчанию выбрана первая радио кнопка
        rb1.addActionListener(e -> { // Добавляются слушатели событий для радио кнопок, чтобы записать соответствующее значение gameMode (0 или 1).
            gameMode = 0;
        });
        rb2.addActionListener(e -> {
            gameMode = 1;
        });
        bg.add(rb1); // Добавляем радио кнопки в группу кнопок bg
        bg.add(rb2);
        jPanel.add(jl); // Добавляем надпись и радио кнопки в панель
        jPanel.add(rb1);
        jPanel.add(rb2);
        return jPanel; // Возвращаем созданную панель
    }

    public JPanel createFieldPanel() { // создает панель с элементами для выбора размеров игрового поля
        JPanel jPanel = new JPanel(new GridLayout(3, 1)); // Внутри метода создается панель JPanel с сеткой размером 3 на 1.
        JLabel jl = new JLabel("Choose field sizes: "); //Затем создается метка JLabel с текстом "Choose field sizes:"
        JSlider jSlider = new JSlider(3, 10, 3); // Далее создается ползунок JSlider с диапазоном значений от 3 до 10 и начальным значением 3
        JLabel jl2 = new JLabel(String.format("Set field size %d", jSlider.getValue())); // Следующая метка JLabel jl2 отображает текущее значение ползунка с помощью метода String.format()
        jSlider.addChangeListener(new ChangeListener() { // Затем добавляется слушатель событий ChangeListener для ползунка jSlider.
            @Override
            public void stateChanged(ChangeEvent e) { // Внутри слушателя изменяется текст метки jl2 и значение переменной fieldSize.
                jl2.setText(String.format("Set field size %d", jSlider.getValue()));
                fieldSize = jSlider.getValue();
            }
        });
        jPanel.add(jl);
        jPanel.add(jl2);
        jPanel.add(jSlider);
        return jPanel;
    }

    public JPanel createVictoryPanel() { // Создается панель с элементами для выбора длины победы
        JPanel jPanel = new JPanel(new GridLayout(3, 1)); // Внутри метода создается панель JPanel с сеткой размером 3 на 1.
        JLabel jl = new JLabel("Choose length for the win"); // Затем создается метка JLabel с текстом "Choose length for the win"
        JSlider jSlider = new JSlider(3, 10, 3); //  Далее создается ползунок JSlider с диапазоном значений от 3 до 10 и начальным значением 3
        JLabel jl2 = new JLabel(String.format("Set length %d", jSlider.getValue())); // Следующая метка JLabel jl2 отображает текущее значение ползунка с помощью метода String.format().
        jSlider.addChangeListener(new ChangeListener() {  // Затем добавляется слушатель событий ChangeListener для ползунка jSlider
            @Override
            public void stateChanged(ChangeEvent e) { // Внутри слушателя изменяется длина победы и значение ползунка, если выбранное значение превышает размер игрового поля.
                victoryLength = jSlider.getValue();
                if (victoryLength > fieldSize) {
                    victoryLength = fieldSize;
                    jSlider.setValue(victoryLength);
                }
                jl2.setText(String.format("Set length %d", jSlider.getValue())); // Также изменяется текст метки jl2.
            }
        });
        jPanel.add(jl);
        jPanel.add(jl2);
        jPanel.add(jSlider);
        return jPanel;
    }

    public JPanel createMainPanel() { // Метод createMainPanel() создает основную панель, которая включает в себя все предыдущие созданные панели
        JPanel jPanel = new JPanel(new GridLayout(3, 1)); // Создается панель JPanel с сеткой размером 3 на 1
        jPanel.add(createModePanel()); // Затем добавляются панели createModePanel(), createFieldPanel() и createVictoryPanel().
        jPanel.add(createFieldPanel());
        jPanel.add(createVictoryPanel());
        return jPanel; // В итоге, код создает панели с элементами управления для выбора параметров игры: режима игры, размеров поля и длины победы. Эти панели могут быть использованы в пользовательском интерфейсе для настройки игры.
    }

}