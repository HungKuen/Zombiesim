package com.zombiesim.zombiesim;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class ZombieRepository {

    List<Integer> zombieRandom = new ArrayList<>();
    public int numberOfZombies = 1;

    public List<String> zombiesOnTheMove(List<String> allHouses) {

        for (int j = 0; j < 100; j++) {
            if (allHouses.get(j).contains("zombie")) {
                allHouses.set(j, "emptyHouse");
            }
        }
        return allHouses;
    }
    public List<Integer> checkWhereTheZombiesWalked() {
        for (int j = 0; j < numberOfZombies; j++) {
            Random rand = new Random();
            int minRange = 0, maxRange = 100;
            int value = rand.nextInt(maxRange - minRange) + minRange;
            zombieRandom.add(value);
        }
        return zombieRandom;
    }
    public List<String> checkIfZombesFoundPeople(List<Integer> zombieRandom, List<String> allHouses) {
        for (int n = 0; n < zombieRandom.size(); n++) {
            if (allHouses.get(zombieRandom.get(n)).contains("people")) {
                numberOfZombies += 1;
                allHouses.set(zombieRandom.get(n), "zombie");
            }
        }
        return allHouses;
    }
    public List<String> checkIfZombesFoundAnEmptyHouse(List<Integer> zombieRandom, List<String> allHouses) {
        for (int k = 0; k < zombieRandom.size(); k++) {
            if (allHouses.get(zombieRandom.get(k)).contains("House")) {
                allHouses.set(zombieRandom.get(k), "zombie");
            }
        }
        return allHouses;
    }
    public void setNumberOfZombiesToOne(){
        numberOfZombies = 1;
    }
}