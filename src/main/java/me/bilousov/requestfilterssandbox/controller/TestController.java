package me.bilousov.requestfilterssandbox.controller;

import me.bilousov.requestfilterssandbox.model.TestModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class TestController {

    @GetMapping("/test-model-random")
    public TestModel getTestModel() {
        return TestModel.builder()
                .testModelName("The best test model ever!")
                .testModelVersion(1)
                .build();
    }

    @GetMapping("/test-model/{id}")
    public TestModel getTestModelById(@PathVariable long id) {
        if (id == 1L) {
            return TestModel.builder()
                    .testModelName("Model number 1!")
                    .testModelVersion(3)
                    .build();
        } else {
            return TestModel.builder()
                    .testModelName("Other model, just do not care...")
                    .testModelVersion(2)
                    .build();
        }
    }
}
