package com.selfspring.gimi.jv8;

import com.selfspring.gimi.jv8.entity.Apple;

import java.util.function.Predicate;

public class AppleGreenColorPredicate implements Predicate<Apple> {
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}