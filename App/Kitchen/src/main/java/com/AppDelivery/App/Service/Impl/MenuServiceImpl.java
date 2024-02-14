package com.AppDelivery.App.Service.Impl;

import com.AppDelivery.App.Exceptions.MenuIncorrectException;
import com.AppDelivery.App.Model.MenuModel;
import com.AppDelivery.App.Respository.MenuRepository;
import com.AppDelivery.App.Service.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    private MenuServiceImpl(MenuRepository menuRepository){
        this.menuRepository = menuRepository;
    }

    @Override
    public ResponseEntity<MenuModel> create(MenuModel menuModel) {
        var create = menuRepository.save(menuModel);
        return ResponseEntity.ok().body(create);
    }

    @Override
    public ResponseEntity<MenuModel> modify(String id, MenuModel menuModel) {
        var identify = menuRepository.findById(id);
        if(identify.isPresent()){
            var identifyClass = identify.get();

            identifyClass.setName(menuModel.getName());
            identifyClass.setPrice(menuModel.getPrice());

            var identifyNew= menuRepository.save(identifyClass);

            return ResponseEntity.ok().body(identifyNew);
        }
        throw new MenuIncorrectException("Id Is Not Currect!");
    }

    @Override
    public ResponseEntity<List<MenuModel>> getAll() {
        var listOffId = menuRepository.findAll();
        return ResponseEntity.ok().body(listOffId);
    }

    @Override
    public Boolean verifyMenuItem(String menuItem) {
        var verify = menuRepository.findByName(menuItem);
        return verify.isPresent();
    }
}
