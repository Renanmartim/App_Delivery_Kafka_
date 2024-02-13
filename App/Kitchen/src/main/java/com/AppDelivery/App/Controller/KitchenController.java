package com.AppDelivery.App.Controller;

import com.AppDelivery.App.Model.MenuModel;
import com.AppDelivery.App.Service.KitchenListener;
import com.AppDelivery.App.Service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class KitchenController {

    private MenuService menuService;

    private KitchenController (MenuService menuService){
        this.menuService = menuService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<MenuModel>> getAll(){
        return menuService.getAll();
    }

    @PostMapping("/modify/{id}")
    public ResponseEntity<String> modify(@PathVariable String id, @RequestBody MenuModel menuModel){
        return menuService.modify(id,menuModel);
    }

    @PostMapping("/create")
    public ResponseEntity<MenuModel> create(@RequestBody MenuModel menuModel){
        var createdMenu = menuService.create(menuModel);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdMenu.getBody())
                .toUri();
        return ResponseEntity.created(location).body(createdMenu.getBody());
    }

    @PostMapping("/verify/{name}")
    public ResponseEntity<Boolean> modify(@PathVariable String name){
        var response = menuService.verifyMenuItem(name);
        return ResponseEntity.ok().body(response);
    }

}
