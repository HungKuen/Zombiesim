package com.zombiesim.zombiesim;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    List<Integer> zombieRandom = new ArrayList<>();
    public List<String> allHouses = new ArrayList();


    @Autowired
    PeopleRepository peopleRepository;

    @Autowired
    ZombieRepository zombieRepository;

    @PostMapping("/index")
    public String generateNewFriends(){
        System.out.println("new friends");
        return "index";
    }
    @GetMapping("/index")
    public ModelAndView startZombieSim() {
        allHouses = peopleRepository.addPeopleToTheHouses();
        if (allHouses.contains("zombie")) {
            allHouses.clear();
            allHouses = peopleRepository.addPeopleToTheHouses();
        }
        for (int i = 0; i < 10; i++) {
            allHouses = zombieRepository.zombiesOnTheMove(allHouses);
            zombieRandom = zombieRepository.checkWhereTheZombiesWalked();
            allHouses = zombieRepository.checkIfZombesFoundPeople(zombieRandom, allHouses);
            allHouses = zombieRepository.checkIfZombesFoundAnEmptyHouse(zombieRandom, allHouses);
            zombieRandom.clear();
        }
        zombieRandom.clear();
        zombieRepository.setNumberOfZombiesToOne();
        return new ModelAndView("index").addObject("allHouses",allHouses);
    }
}
