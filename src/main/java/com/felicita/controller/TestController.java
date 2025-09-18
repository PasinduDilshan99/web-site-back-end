package com.felicita.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v0/api/test")
@Tag(name = "Test", description = "Test API")
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Operation(
            summary = "Test", description = "Test API",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "Success"
                    ),
                    @ApiResponse(
                            responseCode = "500", description = "Internal Server Error"
                    )
            }
    )
//    @Hidden
    @GetMapping("")
    public String test() {
        LOGGER.info("test");
        return "test";
    }

}
