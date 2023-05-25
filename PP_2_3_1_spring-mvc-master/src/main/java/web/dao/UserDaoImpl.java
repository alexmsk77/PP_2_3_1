package web.dao;

import web.model.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("uncheked")
    public List<User> allUsers() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void add(User user) {
        em.persist(user);
        em.flush();
    }

    @Override
    public void delete(int id) {
        em.remove(em.find(User.class, id));
        em.flush();
    }

    @Override
    public void edit(User updatedUser) {
        em.merge(updatedUser);
        em.flush();
    }

    @Override
    public User readById(int id) {
        return em.find(User.class, id);
    }
}
