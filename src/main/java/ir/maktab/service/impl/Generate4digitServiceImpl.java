package ir.maktab.service.impl;

import ir.maktab.service.interfaces.GeneratorService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
@Service
public class Generate4digitServiceImpl implements GeneratorService {
    @Override
    public Long crateRandomNumber() {
        long ret = (long) (Math.random() * 10000L);
        return ret;
    }

}
