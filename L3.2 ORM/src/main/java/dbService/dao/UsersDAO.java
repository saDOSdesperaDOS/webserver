package dbService.dao;

import dbService.dataSets.UsersDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


public class UsersDAO {

    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public UsersDataSet get(long id) throws HibernateException {
        return (UsersDataSet) session.get(UsersDataSet.class, id);
    }

    //вернет -1 если такого пользователя нет в базе
    public long getUserId(String login) {
        try {
            Criteria criteria = session.createCriteria(UsersDataSet.class);
            return ((UsersDataSet) criteria.add(Restrictions.eq("login", login)).uniqueResult()).getId();
        } catch (NullPointerException e) {
            return new UsersDataSet(login).getId();
        }
        }

    public long insertUser(String login, String password) throws HibernateException {
        return (long) session.save(new UsersDataSet(login, password));
    }
}
