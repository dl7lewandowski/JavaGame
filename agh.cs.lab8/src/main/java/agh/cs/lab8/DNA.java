package agh.cs.lab8;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class DNA {
    public static Integer dnaCommon(List<Integer> value) {
        Map<Integer, Integer> world = new HashMap<>();
        for (Integer a : value) {
            Integer v = world.get(a);
            world.put(a, v == null ? 1 : v + 1);
        }
        Map.Entry<Integer, Integer> max = null;
        for (Map.Entry<Integer, Integer> x : world.entrySet()) {
            if (max == null || x.getValue() > max.getValue())
                max = x;
        }
        assert max != null;
        return value.indexOf(max.getKey());
    }


    public static List<Integer> createDNA() {
        List<Integer> dna = new ArrayList<>();
        for(int i = 0; i<Setting.dnaLen; i++) {
            dna.add(ThreadLocalRandom.current().nextInt(0,8));
        }
        createdDNA(dna);
        return dna;
    }

    public static List<Integer> createdDNA(List<Integer> dnaAnimal) {
        Collections.sort(dnaAnimal);
        for(int i = 0; i<8; i++) {
            if(!dnaAnimal.contains(i)) {
                dnaAnimal.set(dnaCommon(dnaAnimal), i);
            }
        }
        Collections.sort(dnaAnimal);
        return dnaAnimal;
    }

    public static List<Integer> recreateDNA(List<Integer> dnaAnimal1, List<Integer> dnaAnimal2) {
        Collections.sort(dnaAnimal1);
        Collections.sort(dnaAnimal2);
        int x = ThreadLocalRandom.current().nextInt(0,Setting.dnaLen-1);
        int y = ThreadLocalRandom.current().nextInt(x,Setting.dnaLen+1);
        List<Integer> dnaAnimal = new ArrayList<>();
        for(int i = 0; i<x; i++) {
            dnaAnimal.add(dnaAnimal1.get(i));
        }
        for(int i = x; i<y; i++) {
            dnaAnimal.add(dnaAnimal2.get(i));
        }
        for(int i = y; i<Setting.dnaLen; i++) {
            dnaAnimal.add(dnaAnimal1.get(i));
        }
        createdDNA(dnaAnimal);
        return dnaAnimal;
    }
}
