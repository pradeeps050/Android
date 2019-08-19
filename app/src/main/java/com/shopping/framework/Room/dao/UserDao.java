package com.shopping.framework.Room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.shopping.framework.model.UserEntity;

@Dao
public interface UserDao {


    @Query("SELECT * FROM user")
    UserEntity getUserDetail();

    @Insert
    void insetUserDetails(UserEntity userEntity);

    @Update
    void updateUser(UserEntity userEntity);

    @Delete
    void deleteUser(UserEntity userEntity);

}
