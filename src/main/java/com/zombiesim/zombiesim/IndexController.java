package com.zombiesim.zombiesim;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    List<Integer> zombieRandom = new ArrayList<>();
    private List<String> allHouses = new ArrayList();
    private List<String> allTowns = new ArrayList();
    private int numbersOfRoundsUntil101 = 0;
    private boolean townAlive = true;
    private int totalOfRoundsForTenTowns = 0;
    private int averageRoundsToOverwhelmATown;

    @Autowired
    PeopleRepository peopleRepository;

    @Autowired
    ZombieRepository zombieRepository;

    @GetMapping("/index")
    public ModelAndView startZombieSim() {

            allHouses.clear();
            allHouses = peopleRepository.addPeopleToTheHouses();

        for (int i = 0; i < 10; i++) {
        totalOfRoundsForTenTowns +=1;
            while(townAlive){
                numbersOfRoundsUntil101 +=1;
                allHouses = zombieRepository.zombiesOnTheMove(allHouses);
                zombieRandom = zombieRepository.checkWhereTheZombiesWalked();
                allHouses = zombieRepository.checkIfZombesFoundPeople(zombieRandom, allHouses);
                allHouses = zombieRepository.checkIfZombesFoundAnEmptyHouse(zombieRandom, allHouses);
                zombieRandom.clear();
                townAlive=peopleRepository.checkIfPeopleLeft();
            }
            townAlive =peopleRepository.createNewTown();
            }
        allTowns = peopleRepository.allTownsRunOver();
        averageRoundsToOverwhelmATown = numbersOfRoundsUntil101/totalOfRoundsForTenTowns;
        return new ModelAndView("index").addObject("allTowns",allTowns)
                .addObject("numberOfRoundsUntil101",numbersOfRoundsUntil101)
                .addObject("totalOfRoundsForTenTowns",totalOfRoundsForTenTowns)
                .addObject("averageRoundsToOverwhelmATown",averageRoundsToOverwhelmATown);
    }
}
