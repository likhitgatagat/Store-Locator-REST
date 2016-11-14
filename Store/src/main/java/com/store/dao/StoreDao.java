package com.store.dao;

import java.util.List;

import com.store.entity.Store;

public interface StoreDao {
	public Long addStore(Store store);
	public void updateStore(Store store);
	public void deleteStore(Store store);
	public void deleteAllStores();
	public Store getStoreById(Long storeId);
	public List<Store> getAllStores();
	public Boolean isStoreExist(String storeName);
}
