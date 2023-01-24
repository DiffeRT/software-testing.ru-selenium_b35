package app;

import java.util.HashMap;

public class DuckStyle {
    private String priceStyle;
    private HashMap<String, Integer> priceColor;
    private int priceCampFontWeight;
    private HashMap<String, Integer> priceCampColor;
    private float priceHeight;
    private float priceCampHeight;

    public void setPriceStyle(String priceStyle) {
        this.priceStyle = priceStyle;
    }

    public void setPriceColor(String value) {
        this.priceColor = Utils.getColor(value);
    }

    public void setPriceCampColor(String value) {
        this.priceCampColor = Utils.getColor(value);
    }

    public void setPriceHeight(String value) {
        this.priceHeight = Utils.getHeight(value);
    }

    public void setPriceCampHeight(String value) {
        this.priceCampHeight = Utils.getHeight(value);;
    }

    public void setPriceCampFontWeight(String value) {
        priceCampFontWeight = Integer.parseInt(value);
    }
    public int getPriceCampFontWeight() {
        return priceCampFontWeight;
    }

    public String getPriceStyle() {
        return priceStyle;
    }

    public HashMap<String, Integer> getPriceColor() {
        return priceColor;
    }

    public HashMap<String, Integer> getPriceCampColor() {
        return priceCampColor;
    }

    public float getPriceHeight() {
        return priceHeight;
    }

    public float getPriceCampHeight() {
        return priceCampHeight;
    }

}
