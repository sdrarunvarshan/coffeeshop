package com.example.coffeeshop;

class CoffeeItem {
    private final String coffeeName;
    private final int imageResourceId;
    private boolean isInCart;

    public CoffeeItem(String coffeeName, int imageResourceId) {
        this.coffeeName = coffeeName;
        this.imageResourceId = imageResourceId;
    }

    public String getCoffeeName() {
        return coffeeName;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public boolean isInCart() {
        return isInCart;
    }

    public void setInCart(boolean inCart) {
        isInCart = inCart;
    }

}
