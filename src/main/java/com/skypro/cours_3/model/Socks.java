package com.skypro.cours_3.model;


import lombok.Data;

import java.util.Objects;



public class Socks {
    private final int cottonPart;
    private final Size size;
    private final Color color;

    public Socks(int cottonPart, Size size, Color color) {
        this.cottonPart = cottonPart;
        this.size = size;
        this.color = color;
    }

    public int getCottonPart() {
        return cottonPart;
    }

    public Size getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Socks socks = (Socks) o;
        return cottonPart == socks.cottonPart && size == socks.size && color == socks.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cottonPart, size, color);
    }

    public Color getColor() {
        return color;
    }
}

