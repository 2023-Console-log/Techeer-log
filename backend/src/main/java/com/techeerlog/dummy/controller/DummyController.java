package com.techeerlog.dummy.controller;

import com.techeerlog.dummy.service.DummyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DummyController {

    private final DummyService dummyService;

    @GetMapping("/dummy")
    public void insertDummyData() {
        dummyService.insertDummy();
    }
}
