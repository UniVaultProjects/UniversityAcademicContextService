package com.service.src.Controller;

import com.service.src.Service.courseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class courseController {
    @Autowired
    private courseService service;

}
