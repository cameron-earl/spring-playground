package com.example.demo;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("math")
public class MathController {

    @GetMapping("pi")
    public String getPI() {
        return "3.141592653589793";
    }

    @GetMapping("calculate")
    public String calculate(
            @RequestParam(value = "operation", defaultValue = "add") String operation,
            @RequestParam() int x,
            @RequestParam() int y
    ) {
        return MathService.calculate(operation, x, y);
    }

    @PostMapping("sum")
    public String sum(@RequestParam MultiValueMap<String, String> querystring) {

        return MathService.sumStringList(querystring.get("n"));
    }

}
