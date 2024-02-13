package com.AppDelivery.App.Service;

import com.AppDelivery.App.Model.MenuModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MenuService {

    public ResponseEntity<MenuModel> create(MenuModel menuModel);

    public ResponseEntity<String> modify(String id, MenuModel menuModel);

    public ResponseEntity<List<MenuModel>> getAll();

    public Boolean verifyMenuItem(String menuItem);
}
