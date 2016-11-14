package com.store.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
* Store POJO representing store table
* 
* @author  Likhit Gatagat
* @version 1.0
* @since   2016-09-20 
*/
@Entity
@Table(name="store")
public class Store implements Serializable {
	
	private static final long serialVersionUID = -7788619177798333712L;

	/**
	 * store table's primary key
	 * using auto increment strategy for primary key
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="store_id")
	private Long storeId;

	/**
	 * store name
	 */
	@Column(name="store_name")
	private String storeName;

	/**
	 * store zip code
	 */
	@Column(name="store_zip_code")
	private String zipCode;
	
	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
		
}
