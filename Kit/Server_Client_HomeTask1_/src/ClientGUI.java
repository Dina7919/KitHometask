import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientGUI extends JFrame { //графическое окно
    public static final int WIDTH = 400; // переменные ширина
    public static final int HEIGHT = 300; // высота

    private ServerWindow server; // ссылка на объект сервер
    private boolean connected; // флаг, обозначающий, что мы подключены
    private String name; // сохраняем логи - то, чем мы будем подписывать сообщения
// виджеты:
    JTextArea log;
    JTextField tfIPAddress, tfPort, tfLogin, tfMessage;
    JPasswordField password;
    JButton btnLogin, btnSend;
    JPanel headerPanel;

    public ClientGUI(ServerWindow server){ // в конструкторе устанавливаем объект сервера
        this.server = server;

        setSize(WIDTH, HEIGHT); // размеры
        setResizable(false);
        setTitle("Chat client"); // заголовок
        setLocation(server.getX() - 500, server.getY()); // относительное положение (относительно серверного окна)

        createPanel(); // создание панели (визуальной части)

        setVisible(true);
    }

    public void answer(String text){ // сервер посылает нам сообщение (отправляет ответ на запрос пользователя - новое сообщение в беседе)
        appendLog(text); // отобразили на нашей панели сообщений
    }

    private void connectToServer() { // метод подключения к серверу (подключаем метод к кнопке подключения)
        if (server.connectUser(this)){ // обращаемся к методу сервера и передаем ссылку на самого себя (ссылка на текущий объект). Он нас узнает и добавляет нас в список
            appendLog("You have connected successfully!\n"); // успешное сообщение
            headerPanel.setVisible(false); // скрываем панель авторизации
            connected = true; // флаг в состоянии true
            name = tfLogin.getText(); // сохраняем имя, которое было указано в качестве логина
            String log = server.getLog(); // пытаемся получить историю переписки
            if (log != null){ // если мы ее получаем
                appendLog(log); // она отображается на окне сообщений
            }
        } else {
            appendLog("Connection is failed"); // если сервер нам отказал
        }
    }

    public void disconnectFromServer() { // метод отключения сервера (1-сервер нас сам отключил; 2-закрытие графического окна)
        if (connected) { // флаг, что мы подключены
            headerPanel.setVisible(true); // делаем видимой панель где мы можем снова залогиниться (авторизоваться)
            connected = false; // флаг в состоянии false
            server.disconnectUser(this); // мы отключаемся
            appendLog("You were disconnected from server!"); // системное сообщение
        }
    }

    public void message(){ // метод срабатывает при отправке сообщения
        if (connected){ // проверяем что мы подключены
            String text = tfMessage.getText();
            if (!text.isEmpty()){ // текст у нас не пустой
                server.message(name + ": " + text); // отправляем через метод message наше сообщение (подписываем от кого и добавляем текст через ":")
                tfMessage.setText(""); // очищаем текстовое поле после отправки сообщения
            }
        } else {
            appendLog("No connection to server"); // если мы не подключены
        }

    }

    private void appendLog(String text){ // вспомогательный метод. При получении сообщения добавляем это сообщение с переносом строки)
        log.append(text + "\n");
    }

    private void createPanel() { // визуальная часть создания панели
        add(createHeaderPanel(), BorderLayout.NORTH); // сверху
        add(createLog()); // поцентру
        add(createFooter(), BorderLayout.SOUTH); // снизу
    }

    private Component createHeaderPanel(){ // различные виджеты со значениями по умолчанию
        headerPanel = new JPanel(new GridLayout(2, 3)); // сетка 2 на 3
        tfIPAddress = new JTextField("127.0.0.1");
        tfPort = new JTextField("8189");
        tfLogin = new JTextField("Ivan Ivanovich"); // логин
        password = new JPasswordField("123456");
        btnLogin = new JButton("login");
        btnLogin.addActionListener(new ActionListener() { // вызывает метод connectToServer
            @Override
            public void actionPerformed(ActionEvent e) {
                connectToServer();
            }
        });
        // все добавляется на панель:
        headerPanel.add(tfIPAddress);
        headerPanel.add(tfPort);
        headerPanel.add(new JPanel());
        headerPanel.add(tfLogin);
        headerPanel.add(password);
        headerPanel.add(btnLogin);

        return headerPanel; // панель возвращается
    }

    private Component createLog(){ // создается центральная панель
        log = new JTextArea();
        log.setEditable(false); // данную панель нельзя изменять
        return new JScrollPane(log); // добавляем скролинг (если будет много сообщений)
    }

    private Component createFooter() { // наш подвал, где кнопочки отправляют сообщение (поле для набора сообщений)
        JPanel panel = new JPanel(new BorderLayout());
        tfMessage = new JTextField();
        tfMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) { // подключаем слушатель клавиатуры для текстового поля
                if (e.getKeyChar() == '\n'){ // если клавиша ентер
                    message(); // то вызывает метод message
                }
            }
        });
        btnSend = new JButton("send"); // тоже самое делаем с кнопкой отправки сообщений
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // если на нее кликнули
                message(); // отправляем message (извлекаем текст, отправляем на сервер и т.д.)
            }
        });
        panel.add(tfMessage);
        panel.add(btnSend, BorderLayout.EAST);
        return panel;
    }

    @Override
    protected void processWindowEvent(WindowEvent e) { // для закрытия окна (при нажатии на крестик)
        if (e.getID() == WindowEvent.WINDOW_CLOSING){ // переопределяем метод, чтоб туда добавить определенную логику
            disconnectFromServer();
        }
        super.processWindowEvent(e); // добавляем то что было изначально
    }
}