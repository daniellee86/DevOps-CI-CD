package bpp.com.devops;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NameManager {

    private final List<String> adjectives;

    private final List<String> animals;

    public NameManager() {

        adjectives = new ArrayList<>();
        adjectives.add("Secret");

        animals = new ArrayList<>();
        animals.add("Squirrel");

    }

    public void addAnimal(String animal) {
        this.animals.add(animal);
    }

    public void addAdjective(String adjective) {
        this.adjectives.add(adjective);
    }

    public String createName() {
        return randomise(adjectives) + " " + randomise(animals);
    }

    private String randomise(List<String> list) {
        int rand = RandomUtils.insecure().randomInt(0, list.size());
        return list.get(rand);
    }

}
