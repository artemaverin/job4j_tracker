package ru.job4j.tracker.store;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.Store;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {

    private Connection cn;

    public SqlTracker() {
        init();
    }

    public SqlTracker(Connection cn) {
        this.cn = cn;
    }

    private void init() {
        try (InputStream in = new FileInputStream("db/liquibase.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws SQLException {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement statement = cn.prepareStatement("insert into items(name, created) values (?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            statement.execute();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    item.setId(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;

    }

    @Override
    public boolean replace(int id, Item item) {
        boolean res = true;
        try (PreparedStatement statement = cn.prepareStatement("update items set name = ?, created = ? where id = ?")) {
            statement.setString(1, item.getName());
            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            statement.setInt(3, id);
            res = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public boolean delete(int id) {
        boolean res = false;
        try (PreparedStatement statement = cn.prepareStatement("delete from items where id = ?")) {
            statement.setInt(1, id);
            res = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement = cn.prepareStatement("select * from items")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    items.add(new Item(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement = cn.prepareStatement("select * from items where name = ?")) {
            statement.setString(1, key);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                items.add(new Item(resultSet.getInt("id"),
                        resultSet.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public Item findById(int id) {
        Item item = null;
        try (PreparedStatement statement = cn.prepareStatement("select * from items where id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                item = new Item(resultSet.getInt("id"),
                        resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    public boolean deleteAll() {
        boolean res = true;
        try (PreparedStatement statement = cn.prepareStatement("delete from items; "
                + "ALTER SEQUENCE items_id_seq RESTART WITH 1")) {
            res = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        SqlTracker sqlTracker = new SqlTracker();
        Item item1 = new Item("one");
        sqlTracker.add(item1);
        Item item2 = new Item("two");
        sqlTracker.add(item2);
        Item item3 = new Item("dos");
        sqlTracker.replace(1, item3);
        System.out.println(sqlTracker.delete(4));
        System.out.println(sqlTracker.findAll());
    }
}