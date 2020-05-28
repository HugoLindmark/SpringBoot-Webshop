package com.g09.webshopspringboot.controller;


import com.g09.webshopspringboot.domain.Record;
import com.g09.webshopspringboot.service.RecordService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("records")
public class RecordController {

    RecordService recordService;

    @Autowired
    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @CrossOrigin
    @GetMapping()
    public List<Record> getRecords(){
        return recordService.getRecords();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Record getRecord(@PathVariable Long id){
        return recordService.findById(id);
    }

    @CrossOrigin
    @GetMapping("/search/{searchWord}")
    public List<Record> getFilteredRecords(@PathVariable String searchWord){
        return recordService.getFilteredRecords(searchWord.toLowerCase());
    }
}
