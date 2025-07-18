package com.rczy.lox;

public class LoxClass {
    final String name;

    LoxClass(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("<class %s>", name);
    }
}
