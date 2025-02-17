package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   private final SessionFactory sessionFactory;

   @Autowired
   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> getListUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User",User.class);
      return query.getResultList();
   }

   @Override
   public User getListUsers(String model, int series) {
      return (User) sessionFactory.getCurrentSession()
              .createQuery("from User u where u.userCar.model=:model and u.userCar.series=:series", User.class)
              .setParameter("model", model).setParameter("series", series).uniqueResult();
   }

}
