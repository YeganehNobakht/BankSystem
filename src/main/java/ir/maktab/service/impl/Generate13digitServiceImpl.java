package ir.maktab.service.impl;

import ir.maktab.service.interfaces.GeneratorService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
@Service
public class Generate13digitServiceImpl implements GeneratorService {
    @Override
    public Long crateRandomNumber() {
        Set<Long> used = new HashSet<Long>();
        Long ret = null;
        do {
            ret =  (long) (Math.random() * 10000000000000L);
        } while (!used.add(ret));
        return ret;
    }

}
