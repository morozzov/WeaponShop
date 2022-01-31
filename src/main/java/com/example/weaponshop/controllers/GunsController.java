package com.example.weaponshop.controllers;

import com.example.weaponshop.models.DbManager;
import com.example.weaponshop.models.entities.Gun;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/guns", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class GunsController {

    @GetMapping(value = "/getAll")
    public ArrayList<Gun> getAll() throws Exception {
        DbManager db = DbManager.getInstance();
        return db.tableGuns.getAll();
    }
}
