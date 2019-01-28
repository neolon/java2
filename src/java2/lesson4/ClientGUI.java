package java2.lesson4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientGUI extends JFrame
        implements ActionListener, Thread.UncaughtExceptionHandler {

    private static final int WIDTH = 640;	// Ширина окна чата
    private static final int HEIGHT = 480;	// Высота окна чата
    private final JTextArea log = new JTextArea();	// Лог чата
    // Компоновщик для кнопок
    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    // Поле для IP
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    // Поле для порта
    private final JTextField tfPort = new JTextField("8189");
    // Поле галки - всегда сверху
    private final JCheckBox cbAlwaysOnTop = new JCheckBox("Always on top");
    // Поле для ника
    private final JTextField tfLogin = new JTextField("ivan_igorevich");
    // Поле для пароля
    private final JPasswordField tfPassword = new JPasswordField("123456");
    // Кнопка соединения с сервером
    private final JButton btnLogin = new JButton("Login");
    // Кнопка отсоединения от сервера
    private final JButton btnDisconnect = new JButton("Disconnect");
    // Компоновщик для кнопок - Disconnect и Send, и строки ввода сообщений
    private final JPanel panelBottom = new JPanel(new BorderLayout());
    // Поле для ввода текста сообщения
    private final JTextField tfMessage = new JTextField();
    // Кнопка отправить сообщение
    private final JButton btnSend = new JButton("Send");
    // Список пользователей
    private final JList<String> userList = new JList<>();
    // Время создания лог-файла
    private static Date logFileDate = new Date();
    // Форматирование даты для имени файла лога
    private static SimpleDateFormat logFileDateFormat = new SimpleDateFormat("yyyyMMdd-hhmmss");
    // Имя лог файла
    private static String logFileName = "chat-" + logFileDateFormat.format(logFileDate) + ".log";
    // Настройки лог-файла
    private static FileOutputStream logFile;


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {	// Создание нового потока
            @Override
            public void run() {
                new ClientGUI();					// Создание экземпляра чата
            }
        });
    }


    // Конструктор
    ClientGUI() {
        // Назначаем текущий экземпляр обработчиком исключений
        Thread.setDefaultUncaughtExceptionHandler(this);
        // Действие при нажатии на кнопку закрытия окна
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Ширина и высота окна
        setSize(WIDTH, HEIGHT);
        // Окно чата в центр экрана
        setLocationRelativeTo(null);
        // название окна чата
        setTitle("Chat Client");

        // Обработка события при установке флажка - Всегда наверху
        cbAlwaysOnTop.addActionListener(this);
        // Обработка события - отправить сообщение нажав кнопку Send
        btnSend.addActionListener(this);
        // Обработка события - отправить сообщения нажава Enter в JTextField(tfMessage)
        tfMessage.addActionListener(this);

        panelTop.add(tfIPAddress);			// Добавляем сверху поле - IP-адреса
        panelTop.add(tfPort);				// Добавляем сверху поле - порт
        panelTop.add(cbAlwaysOnTop);		// Добавляем сверху галку - всегда наверху
        panelTop.add(tfLogin);				// Добавляем сверху поле - логин
        panelTop.add(tfPassword);			// Добавляем сверху поле - пароль
        panelTop.add(btnLogin);				// Добавляем сверху кнопку - Login
        add(panelTop, BorderLayout.NORTH);	// Добавляем панель с кнопками наверх компоновщика
        // Добавляем кнопку Disconnect на нижний компоновщик
        panelBottom.add(btnDisconnect, BorderLayout.WEST);
        // Добавляем поле для ввода текста на нижний компоновщик
        panelBottom.add(tfMessage, BorderLayout.CENTER);
        // Добавляем кнопку Send на нижний компоновщик
        panelBottom.add(btnSend, BorderLayout.EAST);
        // Добавляем нижний компоновщик на нижнюю строку общего компоновщика
        add(panelBottom, BorderLayout.SOUTH);

        // Панель скроллирования списка пользователей
        JScrollPane scrollUsers = new JScrollPane(userList);
        // Установка фиксированного размера панели скроллирования панели списка пользователей
        scrollUsers.setPreferredSize(new Dimension(100, 0));
        // Добавляем список пользователей на правую сторону общего компоновщика
        add(scrollUsers, BorderLayout.EAST);
        // Болванка списка пользователей
        String[] users = {"user1", "user2", "user3", "user4", "user5",
                "user6", "user7", "user8", "user9", "user10"};
        // Наполнение списка пользователей болванкой списка
        userList.setListData(users);
        // Наполнение болванкой текста лога чата
        for (int i = 0; i < 50; i++) {
            log.append("str" + i + "\n");
        }
        // Запрет редактирования лога чата
        log.setEditable(false);
        // Панель скроллирования лога чата
        JScrollPane scrollLog = new JScrollPane(log);
        // Добавляем в центр панель скроллирования с логом чата
        add(scrollLog, BorderLayout.CENTER);
        // Устанавливаем окно видимым
        setVisible(true);

        // Создание лог-файла
        try {
            logFile = new FileOutputStream(logFileName);
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }

    }

    // Переопределение метода интерфейса ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        // Определение источника вызвавшего событие
        Object src = e.getSource();
        // Определение действий для разных источников событий
        if (src == cbAlwaysOnTop) {
            // Событие при установлении галки - Всегда наверху
            setAlwaysOnTop(cbAlwaysOnTop.isSelected());
        } else if (src == btnSend || src == tfMessage) {
            // События при нажатии кнопки Send
            if (tfMessage.getText().length() != 0) {

                StringBuilder outText = new StringBuilder();
                outText.append(tfMessage.getText() + "\n");

                try {
                    logFile.write(outText.toString().getBytes());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                log.append(tfMessage.getText() + "\n");
                tfMessage.setText(null);
            }
        } else {
            // Исключение, если источник вызвавший событие неизвестен
            throw new RuntimeException("Unknown source: " + src);
        }
    }

    // Переопределение метода интерфейса Thread.UncaughtExceptionHandler
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        // Вывод сообщений исключения
        e.printStackTrace();
        // Массив значений стека трассировки потока
        StackTraceElement[] stackTraceElements = e.getStackTrace();
        // Переменная для вывода сообщения об ошибке
        String message;
        // Определение длины сообщения
        if (stackTraceElements.length == 0) {
            // Если длина сообщения 0, то выводим сообщение ниже
            message = "Empty Stacktrace";
        } else {
            // В противном случае выводим полное сообщение об ошибке
            message = e.getClass().getCanonicalName() +
                    ": " + e.getMessage() + "\n" +
                    "\t at " + stackTraceElements[0];
        }
        // Вывод панели сообщений с сообщением об ошибке
        JOptionPane.showMessageDialog(this, message, "Exception", JOptionPane.ERROR_MESSAGE);
        // Завершение работы приложения
        System.exit(1);
    }
}