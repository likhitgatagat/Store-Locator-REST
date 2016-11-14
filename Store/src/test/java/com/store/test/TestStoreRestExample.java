package com.store.test;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;


import com.store.entity.Store;

public class TestStoreRestExample {


		public static final String SERVER_URI = "http://localhost:8080/Store/";
		
		public static void main(String args[]){
			
			testCreateStore();
			System.out.println("*****");
			testGetStore();
			System.out.println("*****");
			testGetAllStores();
			System.out.println("*****");
			testUpdateStore();
			System.out.println("*****");
			testGetAllStores();
			System.out.println("*****");
			testDeleteStore();
		}

		/* GET */
		private static void testGetAllStores() {
			RestTemplate restTemplate = new RestTemplate();
			List<LinkedHashMap<String, Object>> stores = restTemplate.getForObject(SERVER_URI+"store", List.class);
			System.out.println(stores.size());
			for(LinkedHashMap<String, Object> map : stores){
				System.out.println("Store Id="+map.get("storeId")+",Store Name="+map.get("storeName")+",Store Zipcode="+map.get("zipCode"));;
			}
		}

		/* POST */
		private static void testCreateStore() {
			System.out.println("Testing create Store API----------");
			RestTemplate restTemplate = new RestTemplate();
			Store store = new Store();
			store.setStoreName("Shashikant Provision Store");
			store.setZipCode("411091");
			System.out.println(SERVER_URI+"store");
			URI uri = restTemplate.postForLocation(SERVER_URI+"store", store, Store.class);
			System.out.println("Location : "+uri.toASCIIString());
		}

		/* PUT */
	    private static void testUpdateStore() {
	        System.out.println("Testing update Store API----------");
	        RestTemplate restTemplate = new RestTemplate();
	        Store store  = new Store();
	        store.setStoreName("D Mart");
	        store.setZipCode("411002");
	        restTemplate.put(SERVER_URI+"/store/3", store);
	        System.out.println(store);
	    }

	    /* GET */
		private static void testGetStore() {
			System.out.println("Testing Get Store API----------");
			RestTemplate restTstorelate = new RestTemplate();
			Store store = restTstorelate.getForObject(SERVER_URI+"/store/3", Store.class);
			printStoreData(store);
		}

		/* DELETE */
	    private static void testDeleteStore() {
	        System.out.println("Testing delete Store API----------");
	        RestTemplate restTemplate = new RestTemplate();
	        restTemplate.delete(SERVER_URI+"/store/5");
	    }

		public static void printStoreData(Store store){
			System.out.println("Store Name="+store.getStoreName()+",Store Zip Code="+store.getZipCode());
		}
	}