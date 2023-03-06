package ru.job4j.tracker.store;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.tracker.Item;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.assertj.core.api.Assertions.*;

public class SqlTrackerTest {

    private static Connection connection;

    @BeforeAll
    public static void initConnection() {
        try (InputStream in = new FileInputStream("db/liquibase_test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterAll
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @AfterEach
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items")) {
            statement.execute();
        }
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId())).isEqualTo(item);
    }

    @Test
    public void whenSaveItemAndFindByGeneratedNameThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findByName(item.getName())).isEqualTo(List.of(item));
    }

    @Test
    public void whenSaveItemsAndFindAllThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item1 = new Item("item 1");
        Item item2 = new Item("item 2");
        tracker.add(item1);
        tracker.add(item2);
        assertThat(tracker.findAll()).contains(item1, item2);
    }

    @Test
    public void whenSaveItemsAndDeleteByIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item1 = new Item("item 1");
        Item item2 = new Item("item 2");
        tracker.add(item1);
        tracker.add(item2);
        assertThat(tracker.delete(item1.getId())).isTrue();
    }

    @Test
    public void whenSaveItemsAndReplaceByIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item1 = new Item("item 1");
        Item item2 = new Item("item 2");
        Item replace = new Item("replace by item 3");
        tracker.add(item1);
        tracker.add(item2);
        assertThat(tracker.replace(item1.getId(), replace)).isTrue();
    }

}