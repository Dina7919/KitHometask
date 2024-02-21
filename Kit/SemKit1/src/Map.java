import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Random;

public class Map extends JPanel { // отвечает за нашу игру
    private static final Random RANDOM = new Random();
    private static final int HUMAN_DOT = 1; // обозначают чем занята клетка (сходил игрок)
    private static final int AI_DOT = 2; // сходил компьютер
    private static final int EMPTY_DOT = 0; // пустая клетка
    private static final int PADDING = 10; // отступы

    private int gameStateType;
    private static final int STATE_GAME = 0; // идет игра
    private static final int STATE_WIN_HUMAN = 1; // победил человек
    private static final int STATE_WIN_AI = 2; // победил компьютер
    private static final int STATE_DRAW = 3; // ничья

    private static final String MSG_WIN_HUMAN = "Human won!"; // строковые значения при результате игры
    private static final String MSG_WIN_AI = "AI won!";
    private static final String MSG_DRAW = "Draw!";

    private int gameStateType1; // текущая стадия игры
    private int width, height, cellWidth, cellHeight; //ширина, длинна, кол-во ячеек по ширине, кол-во ячеек по высоте
    private int mode, fieldSizeX, fieldSizeY, winLen; //mode - переменная, которая решает кто с кем будет играть;
    // ширина ячеек + сколько ячеек нужно построить в ряд для победы
    private int[][] field; // массив с цифрами в зависимости от того кто сходил
    private boolean gameWork; // флаг, обозначающий что игра сейчас идет

    Map() {
        setBackground(Color.WHITE); // фон панели белый
        addMouseListener(new MouseAdapter() { // подключаем слушателя для мыши через объект интерфейса
            @Override
            public void mouseReleased(MouseEvent e) { // переопределили нужный метод mousereleased ( в него передается событие, которое совершила мышь)
                // там находятся координаты, где произошел щелчок и как произошел щелчок
                if (gameWork) { // самого игрового цикла в приложении нет; игра делает шаг после нажатия мыши (происходит обновление; щелчек мыши)
                    update(e); // метод, который вызывается при щелчке мыши
                }
            }
        });
    }

    private void initMap() {
        field = new int[fieldSizeY][fieldSizeX]; // инициализация массива с цифрами в зависимости от того кто сходил, обнуляя его значения
    }

    void startNewGame(int mode, int sizeX, int sizeY, int winLen) { // метод новой игры.
        this.mode = mode; // сохраняем значения, которые нам передали
        this.fieldSizeX = sizeX;
        this.fieldSizeY = sizeY;
        this.winLen = winLen;

        initMap(); // вызываем метод, который создаст нам пустой массив
        gameWork = true; // игра стартовала
        gameStateType = STATE_GAME; // меняем переменную на состояние "игра"

        repaint(); // перерисовка игрового поля (виджета)
        // после метода repaint будет вызван метод paintComponent (он сработает при первой же возможности)
    }

    private void update(MouseEvent mouseEvent) { // здесь мы принимаем объект, откуда будем извлекать информацию
        int x = mouseEvent.getX() / cellWidth; // извлекаем координаты где произошел щелчок (всю ширину поделили на ширину одной ячейки)
        int y = mouseEvent.getY() / cellHeight;
        if (!isValidCell(x, y) || !isEmptyCell(x, y)) { // проверка координатов 1- ячейка в рамках игрового поля 2-что ячейка на которую нажали пустая; если не пустая - return
            return;
        }
        field[y][x] = HUMAN_DOT; // если все нормально, то записываем результат в field как ход игрока
        if (checkEndGame(HUMAN_DOT, STATE_WIN_HUMAN)) { // проверяем если это привело к окончанию игры
            return;
        }
        aiTurn(); // если не конец игры, то ход делает компьютер
        repaint(); // идет перерисовка
        checkEndGame(AI_DOT, STATE_WIN_AI); // снова проверяем
    }

    private void testBoard(){ // тестовый метод (вывод массива в данный момент)
        for (int i = 0; i < 3; i++) {
            System.out.println(Arrays.toString(field[i]));
        }
        System.out.println();
    }

    private boolean isValidCell(int x, int y) { // проверяют, что щелчок произошел в игровое поле
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY; // x от 0 до кол-во ячеек и y от 0 до кол-ва ячеек
    }

    private boolean isEmptyCell(int x, int y) {
        return field[y][x] == EMPTY_DOT; // проверяет, что числовое поле с координатами не является пустым
    }

    private void aiTurn() { // ход противника
        int x, y;
        do { // компьютер с помощью объекта random пытается попасть в ячейку. Как только он попал в пустую ячейку ход завершен
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isEmptyCell(x, y)); // если мы получили значение (прервали цикл), сохраняем ход в массив
        field[y][x] = AI_DOT;
    }

    private boolean isMapFull() { // проверяет, что все ячейки заняты (не пустые)
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[i][j] == EMPTY_DOT) { // если хотябы одна ячейка пустая - false
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkEndGame(int dot, int gameOverType) { // метод, который проверяет закончена ли игра
        if (checkWin(dot)) { // возможно игрок своим ходом победил
            this.gameStateType = gameOverType; // если победа совершена мы меняем состояние об окончании игры
            repaint(); // делаем последнюю перерисовку и возвращаем true о том, что игра закончена
            return true;
        } else if (isMapFull()) { // если нет ходов
            this.gameStateType = STATE_DRAW; // объявляем ничью
            repaint(); // делаем перерисовку
            return true;
        }
        return false;
    }

    private boolean checkWin(int dot){ // метод проверяет линии
        for (int i = 0; i < fieldSizeX; i++) { // от ячейки dot мы начинаем пытаться построить линии в одном из направлений
            for (int j = 0; j < fieldSizeY; j++) {
                if (checkLine(i, j, 1, 0, winLen, dot)) return true;
                if (checkLine(i, j, 1, 1, winLen, dot)) return true;
                if (checkLine(i, j, 0, 1, winLen, dot)) return true;
                if (checkLine(i, j, 1, -1, winLen, dot)) return true;
            }
        }
        return false;
    }

    private boolean checkLine(int x, int y, int vx, int vy, int len, int dot){ // x, y - текущая ячейка; vx,vy - следующая ячейка
        int far_x = x + (len - 1) * vx;
        int far_y = y + (len - 1) * vy;
        if (!isValidCell(far_x, far_y)){ // проверяет не пустые ли ячейки
            return false;
        }
        for (int i = 0; i < len; i++) {
            if (field[y + i * vy][x + i * vx] != dot){ // если значение не совпадает
                return false;
            }
        }
        return true; // если выпало нужное значение
    }

    @Override
    protected void paintComponent(Graphics g) { // метод, отвечающих за отрисовку; вызывается каждый раз когда repaint
        super.paintComponent(g); // мы оставляем изначальную реализацию
        if (gameWork) { // дополнительно делаем свою отрисовку
            render(g);
        }
    }

    private void render(Graphics g) { // объект графики нам предоставляют
        width = getWidth(); // определяем ширину и высоту игрового поля
        height = getHeight();
        cellWidth = width / fieldSizeX; // определяем высоту и ширину каждой ячейки
        cellHeight = height / fieldSizeY;

        g.setColor(Color.BLACK); // устанавливаем цвет кисти в черный
        for (int h = 0; h < fieldSizeX; h++) { // начинаем рисовать по горизонтали и вертикали, создавая игровое поле
            int y = h * cellHeight;
            g.drawLine(0, y, width, y); // из одного угла (0, y), до другого (width, y)
        }
        for (int w = 0; w < fieldSizeX; w++) {
            int x = w * cellWidth;
            g.drawLine(x, 0, x, height);
        }

        for (int y = 0; y < fieldSizeY; y++) { // здесь отрисовываем внутри крестиков конкретные фигуры
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == EMPTY_DOT){ // если поле пустое, то идем дальше
                    continue;
                }
                if (field[y][x] == HUMAN_DOT) { // если ячейка не пустая - рисуем крестик
                    g.drawLine(x * cellWidth + PADDING, y * cellHeight + PADDING,
                            (x + 1) * cellWidth - PADDING, (y + 1) * cellHeight - PADDING);
                    g.drawLine(x * cellWidth + PADDING, (y + 1) * cellHeight - PADDING,
                            (x + 1) * cellWidth - PADDING, y * cellHeight + PADDING);
                } else if (field[y][x] == AI_DOT) { // если ячейка принадлежит компьютеру - рисуем круглишок
                    g.drawOval(x * cellWidth + PADDING, y * cellHeight + PADDING,
                            cellWidth - PADDING * 2, cellHeight - PADDING * 2);
                } else { // если мы будем расширять игру, то где-то может быть что-то забыто
                    throw new RuntimeException("unchecked value " + field[y][x] +
                            " in cell: x=" + x + " y=" + y);
                }
            }
        }
        if (gameStateType != STATE_GAME){ // если у нас состояние не игра (игра закончилась)
            showMessage(g); // добавляем сообщение кто победил
        }
    }

    private void showMessage(Graphics g) { // добавить текст на игровое поле в зависимости от игры
        g.setColor(Color.DARK_GRAY); // переключаем цвет кисти на серый
        g.fillRect(0, getHeight() / 2, getWidth(), 70); // прямоугольная область в которой появляется текст
        g.setColor(Color.YELLOW); // меняется цвет кисти на желтый
        g.setFont(new Font("Times new roman", Font.BOLD, 48)); // желтым добавляется текст; шрифт times new roman; жирный 48 размера
        switch (gameStateType){
            case STATE_DRAW -> g.drawString(MSG_DRAW, 180, getHeight() / 2 + 60); // координаты где именно нужно отрисовать текст
            case STATE_WIN_HUMAN -> g.drawString(MSG_WIN_HUMAN, 20, getHeight() / 2 + 60);
            case STATE_WIN_AI -> g.drawString(MSG_WIN_AI, 70, getHeight() / 2 + 60);
            default -> throw new RuntimeException("Unchecked gameOverState: " + gameStateType);
        }
        gameWork = false; // если игра закончена 
    }
}