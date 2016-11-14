package com.store.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.store.entity.Store;
import com.store.utility.CastClass;

/**
* The Store DAO implementation implements store's CURD 
* operation using ORM - Hibernate
*
* @author  Likhit Gatagat
* @version 1.0
* @since   2016-09-20 
*/
@Repository
public class StoreDaoImplementation implements StoreDao {

private SessionFactory sessionFactory;
	
	/**
	 * Setter method to set session factory which is used
	 * to get session to interact with database
	 */
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * This method adds new store to database
	 * @param store object to be persisted
	 * @return Long returns primary key of persisted store.
	 */
	@Override
	public Long addStore(Store store) {
		Session session = sessionFactory.getCurrentSession();
		session.save(store);
		return store.getStoreId();
	}

	/**
	 * This method updates store in database
	 * @param store object to be updated
	 */
	@Override
	public void updateStore(Store store) {
		Session session = sessionFactory.getCurrentSession();
		session.update(store);

	}

	/**
	 * This method gets store details based on store id
	 * @param store id
	 * @return store object.
	 */
	@Override
	public Store getStoreById(Long storeId) {
		Session session = sessionFactory.getCurrentSession();
		Store store = (Store) session.get(Store.class, storeId);
		return store;
	}

	/**
	 * This method gets all available stores in database
	 * @return list of store objects.
	 */
	@Override
	public List<Store> getAllStores() {
		Session session = this.sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM store s ";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(Store.class);
		List<Store> result = CastClass.castList(Store.class, query.list());
		return result;
	}

	/**
	 * This method deletes store record from database
	 * @param store object to be deleted
	 */
	@Override
	public void deleteStore(Store store) {
		Session session = sessionFactory.getCurrentSession();
		store = (Store)session.load(Store.class, store.getStoreId());
		if(store != null) {
			session.delete(store);
		}
		
	}

	/**
	 * This method deletes all stores from database
	 */
	@Override
	public void deleteAllStores() {
		Session session = this.sessionFactory.getCurrentSession();
		String sql = "DELETE FROM store s where 1=1";
		SQLQuery query = session.createSQLQuery(sql);
		query.executeUpdate();
	}

	/**
	 * This method checks whether store exists in database
	 * @param store name
	 * @return boolean value indicating whether store exists
	 * or not in database.
	 */
	@Override
	public Boolean isStoreExist(String storeName) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select 1 from store s where s.store_name = :key");
		query.setString("key", storeName);
		return (query.uniqueResult() != null);
	}

}
