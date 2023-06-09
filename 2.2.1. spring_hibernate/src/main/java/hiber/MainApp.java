package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);


        userService.add(new User(
                "User1",
                "Lastname1",
                "user1@mail.ru",
                new Car("Nissan", 123)));

        userService.add(new User(
                "User2",
                "Lastname2",
                "user2@mail.ru",
                new Car("Lada", 456)));

        userService.add(new User(
                "User3",
                "Lastname3",
                "user3@mail.ru",
                new Car("Audi", 789)));

        userService.add(new User(
                "User4",
                "Lastname4",
                "user4@mail.ru",
                new Car("Vaz", 1666)));

        List<User> users = userService.getListUsers();

        for (User user : users) {
            System.out.println(
                    "Id = " + user.getId() +
                            "\nFirst Name = " + user.getFirstName() +
                            "\nLast Name = " + user.getLastName() +
                            "\nEmail = " + user.getEmail() +
                            "\nCar = " + user.getUserCar().getModel() +
                            "\nCar series = " + user.getUserCar().getSeries());

        }

        System.out.println("Get user by car model and series.\n");

        User user = userService.getUserByCar("Vaz", 1666);

        System.out.println();
        System.out.println(user);

        context.close();
    }
}
