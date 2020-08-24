package Model.Game;

public class GameSale {

    private String name;
    private double price;
    private String link;
    private String shop;

    public GameSale(String name, double price, String link, String shop) {
        this.name = name;
        this.price = price;
        this.link = link;
        this.shop = shop;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getLink() {
        return link;
    }
}
