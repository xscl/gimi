package com.selfspring.gimi.jv8;

import com.selfspring.gimi.jv8.entity.Apple;
import com.selfspring.gimi.jv8.interf.AppleFormatter;

public class AppleFancyFormatter implements AppleFormatter {
    public String accept(Apple apple) {
        String characteristic = apple.getWeight() > 150 ? "heavy" :
                "light";
        return "A " + characteristic +
                " " + apple.getColor() + " apple";
    }
}