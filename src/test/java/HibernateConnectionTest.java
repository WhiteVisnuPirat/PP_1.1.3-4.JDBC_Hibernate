import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class HibernateConnectionTest {
    private final UserDao userDao = new UserDaoHibernateImpl();

    @Test
    public void testConnectionAndCRUD() {
        try {
            // 1. Проверка создания таблицы
            userDao.createUsersTable();
            System.out.println("Таблица users создана");

            // 2. Тест сохранения пользователя
            User testUser = new User("Ivan", "Ivanov", (byte) 25);
            userDao.saveUser(testUser.getName(), testUser.getLastName(), testUser.getAge());
            System.out.println("Пользователь Ivan добавлен");

            // 3. Проверка получения всех пользователей
            List<User> users = userDao.getAllUsers();
            assertFalse("Список пользователей пуст", users.isEmpty());
            assertEquals("Неверное количество пользователей", 1, users.size());
            System.out.println("Получены пользователи: " + users);

            // 4. Проверка данных пользователя
            User savedUser = users.get(0);
            assertEquals("Неверное имя", "Ivan", savedUser.getName());
            assertEquals("Неверная фамилия", "Ivanov", savedUser.getLastName());
            assertEquals("Неверный возраст", (long) 25, (long) savedUser.getAge());


            // 5. Очистка таблицы (дополнительная проверка)
            userDao.cleanUsersTable();
            users = userDao.getAllUsers();
            assertTrue("Таблица не очищена", users.isEmpty());
            System.out.println("Таблица users очищена");

            // 6. Удаление таблицы (финальная проверка)
            userDao.dropUsersTable();
            System.out.println("Таблица users удалена");

        } catch (Exception e) {
            fail("Ошибка тестирования: " + e.getMessage());
            e.printStackTrace();
        }
    }
}