package ru.kaznacheev.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ru.kaznacheev.restapi.dto.InformationDTO;
import ru.kaznacheev.restapi.service.Handler;
import ru.kaznacheev.restapi.util.operation.OperationType;
import ru.kaznacheev.restapi.util.error.*;

import java.sql.Timestamp;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class Controller {

    private final Handler HANDLER;

    @Autowired
    public Controller(Handler handler) {
        this.HANDLER = handler;
    }

    @PostMapping(value = "/value", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Map<String, String>> returnValue(@RequestBody InformationDTO information) {
        HANDLER.setInformation(information);
        Map<String, String> response = HANDLER.handle();
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/{operation}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Map<String, String>> returnValue(@RequestBody InformationDTO information, @PathVariable OperationType operation) {
        information.setOperation(operation);
        HANDLER.setInformation(information);
        Map<String, String> response = HANDLER.handle();
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

}
