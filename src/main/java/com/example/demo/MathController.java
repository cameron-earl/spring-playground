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

    @RequestMapping("volume/{h}/{w}/{l}")
    public String volume(@PathVariable int h, @PathVariable int w, @PathVariable int l) {
        return String.format("The volume of a %dx%dx%d rectangle is %d",h,w,l,h*w*l);
    }

    @PostMapping("area")
    public String area(
        @RequestParam String type,
        @RequestParam(value = "radius", defaultValue = "-1") int radius,
        @RequestParam(value = "width", defaultValue = "-1") int width,
        @RequestParam(value = "height", defaultValue = "-1") int height
    ) {
        if (type.equals("circle") && radius >= 0) {
            double area = Math.PI * radius * radius;
            return String.format("Area of a circle with a radius of %d is %.5f", radius, area);
        } else if (type.equals("rectangle") && width >= 0 && height >= 0) {
            int area = height * width;
            return String.format("Area of a %dx%d rectangle is %d", width, height, area);
        } else {
            return "Invalid";
        }
    }

}
