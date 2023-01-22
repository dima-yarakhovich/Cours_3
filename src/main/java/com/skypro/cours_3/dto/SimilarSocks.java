package com.skypro.cours_3.dto;

import com.skypro.cours_3.model.Color;
import com.skypro.cours_3.model.Size;
import lombok.AllArgsConstructor;
import lombok.Data;


public class SimilarSocks {
    private int cottonPart;
    private Size size;
    private Color color;
    private int quantity;

    public void setCottonPart(int cottonPart) {
        this.cottonPart = cottonPart;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCottonPart() {
        return cottonPart;
    }

    public Size getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    public int getQuantity() {
        return quantity;
    }
}
