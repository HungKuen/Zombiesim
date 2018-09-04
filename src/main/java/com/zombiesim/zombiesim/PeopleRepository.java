package com.zombiesim.zombiesim;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class PeopleRepository {

    List<String> allHouses = new ArrayList();

    public List<String> addPeopleToTheHouses() {
        for (int i = 0; i < 100; i++) {
            allHouses.add("peopleInDaHouse");
        }
        return allHouses;
    }
}
