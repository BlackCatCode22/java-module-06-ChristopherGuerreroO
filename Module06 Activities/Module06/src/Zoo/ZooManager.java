package Zoo;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ZooManager {

    private static final List<Animal> animals = new ArrayList<>();
    private static final Map<String, Queue<String>> nameMap = new HashMap<>();
    private static final Map<String, Integer> idCounters = new HashMap<>() {{
        put("hyena", 0);
        put("lion", 0);
        put("tiger", 0);
        put("bear", 0);
    }};

    public static void main(String[] args) {
        loadNames("src/resources/animalNames.txt");
        loadAnimals("src/resources/arrivingAnimals.txt");
        generateReport("src/resources/zooPopulation.txt");
    }

    private static void loadNames(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                String species = parts[0].trim().toLowerCase();
                Queue<String> names = new LinkedList<>(Arrays.asList(parts[1].split(", ")));
                nameMap.put(species, names);
            }
        } catch (IOException e) {
            System.out.println("Error loading names: " + e.getMessage());
        }
    }

    private static void loadAnimals(String filename) {
        LocalDate today = LocalDate.now();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");

                // Parse basic data
                String[] ageSexSpecies = parts[0].split(" ");
                int age = Integer.parseInt(ageSexSpecies[0]);
                String sex = ageSexSpecies[3];
                String species = ageSexSpecies[4].toLowerCase();

                String season = parts[1].replace("born in ", "").toLowerCase();
                String color = parts[2].replace(" color", "").toLowerCase();
                double weight = Double.parseDouble(parts[3].split(" ")[0]);
                String origin = parts[4];
                LocalDate birthDate = genBirthDate(age, season);
                String name = nameMap.get(species).poll();
                String id = genUniqueID(species);

                // Create animal object
                Animal animal;
                switch (species) {
                    case "hyena" -> animal = new Hyena(name, age, sex, weight, origin);
                    case "lion" -> animal = new Lion(name, age, sex, weight, origin, false);
                    case "tiger" -> animal = new Tiger(name, age, sex, weight, origin, false); // or true if appropriate
                    case "bear" -> animal = new Bear(name, age, sex, weight, origin);
                    default -> {
                        continue; // unknown species
                    }
                }

                // Set shared properties
                animal.setId(id);
                animal.setColor(color);
                animal.setBirthDate(birthDate);
                animal.setArrivalDate(today);

                animals.add(animal);
            }
        } catch (IOException e) {
            System.out.println("Error loading animals: " + e.getMessage());
        }
    }

    private static LocalDate genBirthDate(int age, String season) {
        int year = LocalDate.now().getYear() - age;
        return switch (season) {
            case "spring" -> LocalDate.of(year, 3, 21);
            case "summer" -> LocalDate.of(year, 6, 21);
            case "fall" -> LocalDate.of(year, 9, 21);
            case "winter" -> LocalDate.of(year, 12, 21);
            default -> LocalDate.of(year, 1, 1); // default fallback
        };
    }

    private static String genUniqueID(String species) {
        int count = idCounters.get(species) + 1;
        idCounters.put(species, count);
        String prefix = switch (species) {
            case "hyena" -> "Hy";
            case "lion" -> "Li";
            case "tiger" -> "Ti";
            case "bear" -> "Be";
            default -> "XX";
        };
        return String.format("%s%02d", prefix, count);
    }

    private static void generateReport(String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            Map<String, List<Animal>> grouped = new HashMap<>();
            for (Animal animal : animals) {
                grouped.computeIfAbsent(animal.getSpecies(), k -> new ArrayList<>()).add(animal);
            }

            for (String species : grouped.keySet()) {
                pw.println(capitalize(species) + " Habitat:");
                System.out.println("Species: " + species);
                for (Animal animal : grouped.get(species)) {
                    pw.printf("%s; %s; birth date: %s; %s color; %s; %.1f pounds; from %s; arrived %s%n",
                            animal.getId(),
                            animal.getName(),
                            animal.getBirthDate(),
                            animal.getColor(),
                            animal.getSex(),
                            animal.getWeight(),
                            animal.getOrigin(),
                            animal.getArrivalDate()
                    );
                }
                pw.println();
            }

            System.out.println("Report generated successfully.");

        } catch (IOException e) {
            System.out.println("Error writing report: " + e.getMessage());
        }
    }

    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) return "Unknown";
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
