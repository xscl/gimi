package com.selfspring.gimi.jv8;

import com.selfspring.gimi.jv8.entity.Apple;
import com.selfspring.gimi.jv8.interf.AppleFormatter;

public class AppleSimpleFormatter implements AppleFormatter {
    public String accept(Apple apple) {
        return "An apple of " + apple.getWeight() + "g";
    }
}