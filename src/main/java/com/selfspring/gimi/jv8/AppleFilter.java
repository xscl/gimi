package com.selfspring.gimi.jv8;

import com.selfspring.gimi.jv8.entity.Apple;
import com.selfspring.gimi.jv8.interf.AppleFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by ckyang on 2019/7/7.
 */
public class AppleFilter {

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }
    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public static List<Apple> filterHeavyApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > 150) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApples(List<Apple> inventory,
                                    Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory){ if (p.test(apple)) {
            result.add(apple);
        }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Apple> invertory = new ArrayList<>();
        invertory.add(createApple("green",130));
        invertory.add(createApple("red",190));
        invertory.add(createApple("white",170));
        System.out.println(filterApples(invertory,AppleFilter::isGreenApple));

        System.out.println(filterApples(invertory,
                (Apple x)->"green".equals(x.getColor())));

        System.out.println(filterApples(invertory,AppleFilter::isHeavyApple));

        System.out.println(filterApples(invertory,(Apple x)->x.getWeight()>150));
        System.out.println(filterApples(invertory,(x)->x.getWeight()>150));
    }

    public static void prettyPrintApple(List<Apple> inventory,
                                        AppleFormatter formatter){
        for(Apple apple: inventory) {
            String output = formatter.accept(apple);
            System.out.println(output);
        }
    }


    private static Apple createApple(String color, int weight) {
        Apple apple = new Apple();
        apple.setColor(color);
        apple.setWeight(weight);
        return apple;
    }
}
