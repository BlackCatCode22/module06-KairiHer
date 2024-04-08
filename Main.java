import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Animal {
    private String name;
    private int arrivalOrder;

    public Animal(String name, int arrivalOrder) {
        this.name = name;
        this.arrivalOrder = arrivalOrder;
    }

    public String getName() {
        return name;
    }

    public int getArrivalOrder() {
        return arrivalOrder;
    }
}

public class ZookeeperChallenge {
    private Map<String, List<Animal>> animalMap; // Map to store animals grouped by species
    private List<String> animalNames; // List to store all animal names

    public ZookeeperChallenge() {
        animalMap = new HashMap<>();
        animalNames = new ArrayList<>();
    }

    public void readAnimalNames(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        while (scanner.hasNextLine()) {
            String name = scanner.nextLine();
            animalNames.add(name);
            animalMap.put(name, new ArrayList<>()); // Initialize empty list for each animal name
        }
        scanner.close();
    }

    public void readArrivingAnimals(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        int arrivalOrder = 1;
        while (scanner.hasNextLine()) {
            String species = scanner.nextLine();
            if (animalMap.containsKey(species)) {
                animalMap.get(species).add(new Animal(species, arrivalOrder++)); // Add animal to its species list
            } else {
                System.out.println("Unknown species: " + species);
            }
        }
        scanner.close();
    }

    public void displayAnimals() {
        for (String species : animalNames) {
            System.out.println(species + ":");
            List<Animal> animals = animalMap.get(species);
            for (Animal animal : animals) {
                System.out.println(" - " + animal.getName() + " (Arrival Order: " + animal.getArrivalOrder() + ")");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ZookeeperChallenge zookeeper = new ZookeeperChallenge();
        try {
            zookeeper.readAnimalNames("animalNames.txt");
            zookeeper.readArrivingAnimals("arrivingAnimals.txt");
            zookeeper.displayAnimals();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
