package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // Создаем экземпляр сервиса
        UserService userService = new UserServiceImpl();

        // 1. Создаем таблицу пользователей
        userService.createUsersTable();
        System.out.println("Таблица пользователей создана");

        // 2. Добавляем 4 пользователей
        userService.saveUser("Иван", "Иванов", (byte) 20);
        userService.saveUser("Петр", "Петров", (byte) 25);
        userService.saveUser("Сидор", "Сидоров", (byte) 30);
        userService.saveUser("Алексей", "Алексеев", (byte) 35);

        // 3. Получаем всех пользователей и выводим в консоль
        System.out.println("\nСписок всех пользователей:");
        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }

        // 4. Очищаем таблицу
        userService.cleanUsersTable();
        System.out.println("\nТаблица пользователей очищена");

        // 5. Удаляем таблицу
        userService.dropUsersTable();
        System.out.println("Таблица пользователей удалена");
    }
}