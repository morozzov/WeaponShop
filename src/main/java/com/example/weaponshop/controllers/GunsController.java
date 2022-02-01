package com.example.weaponshop.controllers;

import com.example.weaponshop.models.DbManager;
import com.example.weaponshop.models.entities.Gun;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/guns", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class GunsController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllException(Exception exception){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("ERROR: " + exception.getMessage());
    }

    @GetMapping(value = "/getAll")
    public ArrayList<Gun> getAll() throws Exception {
        DbManager db = DbManager.getInstance();
        return db.tableGuns.getAll();
    }

    @GetMapping(value = "/getById/{id}")
    public Gun getById(@PathVariable int id) throws Exception {
        DbManager db = DbManager.getInstance();
        return db.tableGuns.getById(id);
    }

    @PostMapping(value = "/insertOne")
    public void insertOne(@RequestBody Gun gun) throws Exception {
        DbManager db = DbManager.getInstance();
        db.tableGuns.insertOne(gun);
    }

    @DeleteMapping(value = "/deleteById/{id}")
    public void deleteById(@PathVariable int id) throws Exception {
        DbManager db = DbManager.getInstance();
        db.tableGuns.getById(id);
        db.tableGuns.deleteById(id);
    }

    @PutMapping(value = "/updateById/{id}")
    public void updateById(@PathVariable int id, @RequestBody Gun gun) throws Exception {
        DbManager db = DbManager.getInstance();
        db.tableGuns.getById(id);
        db.tableGuns.updateById(id, gun);
    }
}
