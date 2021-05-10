package ir.maktab.service.impl;

import ir.maktab.service.interfaces.GeneratorService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class Generate13DigitController {
    private final GeneratorService generatorService;

    public Generate13DigitController(@Qualifier("generate13digitServiceImpl") GeneratorService generatorService) {
        this.generatorService = generatorService;
    }

    public Long generate13Digit(){
        return generatorService.crateRandomNumber();
    }
}
