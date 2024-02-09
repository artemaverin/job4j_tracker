package ru.job4j.tracker;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        var session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        var session = sf.openSession();
        boolean result = false;
        try {
            session.beginTransaction();
            var query = session.createQuery("UPDATE item SET name = :fName, created = :fCreated WHERE id = :fId", Item.class)
                    .setParameter("fName", item.getName())
                    .setParameter("fCreated", item.getCreated())
                    .setParameter("fId", id)
                    .executeUpdate();
            result = query > 0;
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        var session = sf.openSession();
        boolean result = false;
        try {
            session.beginTransaction();
            var query = session.createQuery("DELETE Item WHERE id = :fid", Item.class)
                    .setParameter("fId", id)
                    .executeUpdate();
            result = query > 0;
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        var session = sf.openSession();
        List<Item> itemList = new ArrayList<>();
        try {
            session.beginTransaction();
            itemList = session.createQuery("from Item", Item.class).getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return itemList;
    }

    @Override
    public List<Item> findByName(String key) {
        var session = sf.openSession();
        List<Item> itemList = new ArrayList<>();
        try {
            session.beginTransaction();
            Query<Item> query = session.createQuery(
                    "from Item as i where i.name LIKE :fKey", Item.class);
            query.setParameter("fKey",  "%" + key + "%");
            itemList = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return itemList;
    }

    @Override
    public Item findById(int id) {
        var session = sf.openSession();
        Item item = null;
        try {
            session.beginTransaction();
            var query = session.createQuery(
                    "from Item as i where i.id = :fId", Item.class)
                    .setParameter("fId", id);
            item = query.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return item;
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
