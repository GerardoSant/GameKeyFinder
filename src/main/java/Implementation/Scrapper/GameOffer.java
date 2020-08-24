package Implementation.Scrapper;

public class GameOffer implements Comparable<GameOffer> {
    private String link;
    private double price;
    private String shop;
    private String gameName;

    public GameOffer(String link, double price, String shop) {
        this.link = link;
        this.price = price;
        this.shop = shop;
    }

    public String getLink() {
        return link;
    }

    public double getPrice() {
        return price;
    }

    public String getShop() {
        return shop;
    }

    public String getName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String toString(){
        return "Shop: " + getShop() +  " | Price: " + getPrice() + " | Link: " + getLink() + "\n";
    }


    @Override
    public int compareTo(GameOffer o) {
        double value = this.price - o.getPrice();
        return (value<0) ? -1 : (value==0) ? 0 : 1;
    }
}
