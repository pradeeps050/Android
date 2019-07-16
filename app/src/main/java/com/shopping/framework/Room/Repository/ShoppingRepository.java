package com.shopping.framework.Room.Repository;

import android.arch.lifecycle.MediatorLiveData;
import android.arch.persistence.room.Transaction;

import com.shopping.framework.Room.Database.ShoppingRoomDatabase;



public class ShoppingRepository {
    private static ShoppingRepository sInstance;
    private final ShoppingRoomDatabase mDatabase;


    private ShoppingRepository(final ShoppingRoomDatabase database) {
        mDatabase = database;


    }

    public static ShoppingRepository getInstance(final ShoppingRoomDatabase database) {
        synchronized (ShoppingRepository.class) {
            if (sInstance == null) {
                sInstance = new ShoppingRepository(database);
            }
        }

        return sInstance;
    }






}
