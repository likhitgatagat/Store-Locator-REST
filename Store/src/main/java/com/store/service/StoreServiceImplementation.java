package com.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.dao.StoreDao;
import com.store.entity.Store;

/**
* Store service implementation
* calls underlying methods from respective DAO implementation
* uses programmatic approach for managing transactions 
* using annotation
* @author  Likhit Gatagat
* @version 1.0
* @since   2016-09-20 
*/
@Service
public class StoreServiceImplementation implements StoreService {

	private StoreDao storeDao;
	
	/**
	 * setter method to inject store DAO implementation
	 */
	@Autowired
	public void setStoreDao(StoreDao storeDao) {
		this.storeDao = storeDao;
	}

	/**
	 * This is a service method to add store
	 *  
	 * @param store object
	 * @return primary key of persisted store object
	 */
	@Override
	@Transactional
	public Long addStore(Store store) {
		return this.storeDao.addStore(store);
	}

	/**
	 * This is a service method to update store
	 *  
	 * @param store object to be updated
	 */
	@Override
	@Transactional
	public void updateStore(Store store) {
		this.storeDao.updateStore(store);
	}

	/**
	 * This is a service method to delete store
	 *  
	 * @param store object
	 */
	@Override
	@Transactional
	public void deleteStore(Store store) {
		this.storeDao.deleteStore(store);
	}

	/**
	 * This is a service method to get store
	 *  
	 * @param primary key of store
	 * @return store object
	 */
	@Override
	@Transactional
	public Store getStoreById(Long storeId) {
		return this.storeDao.getStoreById(storeId);
	}

	/**
	 * This is a service method to get all stores 
	 *
	 * @return List of store objects
	 */
	@Override
	@Transactional
	public List<Store> getAllStores() {
		return this.storeDao.getAllStores();
	}

	/**
	 * This is a service method to check existence of store 
	 *
	 * @param String store name
	 * @return Boolean to indicate existence of store
	 */
	@Override
	@Transactional
	public Boolean isStoreExist(String storeName) {
		return this.storeDao.isStoreExist(storeName);
	}

	/**
	 * This is a service method to delete all stores 
	 *
	 */
	@Override
	@Transactional
	public void deleteAllStores() {
		this.storeDao.deleteAllStores();
	}

}
