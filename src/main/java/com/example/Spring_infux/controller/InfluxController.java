package com.example.Spring_infux.controller;


import com.example.Spring_infux.service.InfluxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfluxController {

    @Autowired
    private InfluxService influxService;

    @PostMapping
    @RequestMapping(value="/write")
    public void writeData() throws Exception{
           influxService.writeData();
    }

    @GetMapping
    @RequestMapping(value="/write")
    public void readData() throws Exception{
        influxService.writeData();
    }

}
