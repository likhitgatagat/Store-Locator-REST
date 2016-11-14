package com.store.controller;

import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.store.entity.Store;
import com.store.service.StoreService;

/**
* REST controller for CRUD operations for Store
* @author  Likhit Gatagat
* @version 1.0
* @since   2016-09-20 
*/
@RestController
public class StoreRestController {

	/**
	 * Service which will do all data retrieval/manipulation work
	 */
    @Autowired
    StoreService storeService;

    /**
     * Retrieve All Stores
     * 
     * @return Response entity holding list of store objects
     */
    @RequestMapping(value = "/store", method = RequestMethod.GET)
    public ResponseEntity<List<Store>> listAllStores() {
        List<Store> stores = storeService.getAllStores();
        if(stores.isEmpty()){
            return new ResponseEntity<List<Store>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Store>>(stores, HttpStatus.OK);
    }

    /**
     * Retrieve Single Store
     * @param id, primary key of store
     * @return Response entity holding store object
     */
    @RequestMapping(value = "/store/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Store> getStore(@PathVariable("id") long id) {
        System.out.println("Fetching Store with id " + id);
        Store store = storeService.getStoreById(id);
        if (store == null) {
            System.out.println("Store with id " + id + " not found");
            return new ResponseEntity<Store>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Store>(store, HttpStatus.OK);
    }

    /**
     * Create a Store
     * @param store
     * @param ucBuilder
     * @return Response entity holding URI of created store object
     */
    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public ResponseEntity<Void> createStore(@RequestBody Store store,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Store " + store.getStoreName());
 
        if (storeService.isStoreExist(store.getStoreName())) {
            System.out.println("A Store with name " + store.getStoreName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        storeService.addStore(store);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/store/{id}").buildAndExpand(store.getStoreId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    /**
     * Update a Store
     * 
     * @param id, primary key of a store to be updated
     * @param store object to update store object
     * represented by primary key
     * @return Response entity holding updated store object
     */
    @RequestMapping(value = "/store/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Store> updateStore(@PathVariable("id") long id, @RequestBody Store store) {
        System.out.println("Updating Store " + id);

        Store currentStore = storeService.getStoreById(id);

        if (currentStore==null) {
            System.out.println("Store with id " + id + " not found");
            return new ResponseEntity<Store>(HttpStatus.NOT_FOUND);
        }

        currentStore.setStoreName(store.getStoreName());
        currentStore.setZipCode(store.getZipCode());

        storeService.updateStore(currentStore);
        return new ResponseEntity<Store>(currentStore, HttpStatus.OK);
    }

    /**
     * Delete a Store
     * 
     * @param id, primary key of a store to be deleted
     */
    @RequestMapping(value = "/store/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Store> deleteStore(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Store with id " + id);

        Store store = storeService.getStoreById(id);
        if (store == null) {
            System.out.println("Unable to delete. Store with id " + id + " not found");
            return new ResponseEntity<Store>(HttpStatus.NOT_FOUND);
        }

        storeService.deleteStore(store);
        return new ResponseEntity<Store>(HttpStatus.NO_CONTENT);
    } 

    /**
     * Delete All Stores
     */
    @RequestMapping(value = "/store", method = RequestMethod.DELETE)
    public ResponseEntity<Store> deleteAllStores() {
        System.out.println("Deleting All Stores");
 
        storeService.deleteAllStores();
        return new ResponseEntity<Store>(HttpStatus.NO_CONTENT);
    }

}