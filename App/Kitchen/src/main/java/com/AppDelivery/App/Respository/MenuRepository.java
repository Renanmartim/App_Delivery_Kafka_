package com.AppDelivery.App.Respository;

import com.AppDelivery.App.Model.MenuModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MenuRepository extends MongoRepository<MenuModel,String> {
    Optional<String> findByName(String menuItem);
}
