package com.ezbob.shuffle.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ShuffleService {
/*
* Sattolo cycle algorithm for randomly shuffling an array in such a way that each element ends up in a new position.
* */
public List<Integer> generateList(int number){
    List<Integer> list = IntStream.range(1, number+1).boxed().collect(Collectors.toList());
    for (int i = number-1; i > 0; i--) {
        int j = ThreadLocalRandom.current().nextInt(0, i + 1);
        Integer tmp = list.get(i);
        list.set(i,list.get(j));
        list.set(j,tmp);
    }
    return list;
}

}
