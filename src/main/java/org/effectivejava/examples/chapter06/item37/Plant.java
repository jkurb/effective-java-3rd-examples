// Using EnumMap instead of ordinal indexing
package org.effectivejava.examples.chapter06.item37;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Simplistic class representing a culinary herb - Page 161
public class Plant {
    public enum LifeCycle {
        ANNUAL, PERENNIAL, BIENNIAL
    }

    private final String name;
    private final LifeCycle lifeCycle;

    Plant(String name, LifeCycle type) {
        this.name = name;
        this.lifeCycle = type;
    }

    @Override
    public String toString() {
        return name;
    }

    public static void main(String[] args) {
        Plant[] garden = {new Plant("Basil", LifeCycle.ANNUAL),
                new Plant("Carroway", LifeCycle.BIENNIAL),
                new Plant("Dill", LifeCycle.ANNUAL),
                new Plant("Lavendar", LifeCycle.PERENNIAL),
                new Plant("Parsley", LifeCycle.BIENNIAL),
                new Plant("Rosemary", LifeCycle.PERENNIAL)};

        // Using an EnumMap to associate data with an enum - Page 171
        Map<Plant.LifeCycle, Set<Plant>> plantsByLifeCycle = new EnumMap<>(Plant.LifeCycle.class);

        for (Plant.LifeCycle t : Plant.LifeCycle.values())
            plantsByLifeCycle.put(t, new HashSet<>());

        for (Plant h : garden)
            plantsByLifeCycle.get(h.lifeCycle).add(h);

        System.out.println(plantsByLifeCycle);


        // Naive stream-based approach - unlikely to produce an EnumMap!
//        System.out.println(Arrays.stream(garden)
//            .collect(groupingBy(p -> p.lifeCycle)));


        // Using a stream and an EnumMap to associate data with an enum
//        System.out.println(Arrays.stream(garden)
//                .collect(groupingBy(p -> p.lifeCycle,
//                    () -> new EnumMap<>(LifeCycle.class), toSet())));
    }
}
