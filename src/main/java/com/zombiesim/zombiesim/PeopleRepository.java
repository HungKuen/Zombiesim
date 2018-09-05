package com.zombiesim.zombiesim;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class PeopleRepository {

    @Autowired
    ZombieRepository zombieRepository;

    List<String> allHouses = new ArrayList();
    List<String> allTowns = new ArrayList();

    public List<String> addPeopleToTheHouses() {
        for (int i = 0; i < 100; i++) {
            allHouses.add("peopleInDaHouse");
        }
        return allHouses;
    }
    public boolean checkIfPeopleLeft(){
        if (zombieRepository.numberOfZombies>100) {
            return false;
        }
        return true;
    }
    public boolean createNewTown(){
        for (int j=0;j<allHouses.size();j++){
            allTowns.add(allHouses.get(j));
            allHouses.set(j,"people");
        }
        allTowns.add("betweenTowns");
        zombieRepository.setNumberOfZombiesToOne();
        return true;
    }
    public List<String> allTownsRunOver(){
        return allTowns;
    }
}
