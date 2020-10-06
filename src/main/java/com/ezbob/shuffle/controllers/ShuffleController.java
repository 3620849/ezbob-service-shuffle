package com.ezbob.shuffle.controllers;

import com.ezbob.shuffle.model.ShuffledListResponse;
import com.ezbob.shuffle.services.ShuffleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@Validated
public class ShuffleController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ShuffleService shuffleService;


    @RequestMapping(value = "/shuffle", method = RequestMethod.POST)
    public ResponseEntity<ShuffledListResponse> shuffle(@RequestParam
                                                 @Min(value = 1, message = "Number should not be less than 1")
                                                 @Max(value = 1000, message = "Number should not be greater than 1000")
                                                         int number) {

        ShuffledListResponse obj = new ShuffledListResponse();
        obj.setList(shuffleService.generateList(number));
        return new ResponseEntity(obj, HttpStatus.OK);
    }
}
