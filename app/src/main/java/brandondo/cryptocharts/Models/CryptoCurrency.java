package brandondo.cryptocharts.Models;

import com.google.gson.annotations.SerializedName;

public class CryptoCurrency {
    @SerializedName("Id")
    private String id = "";

    @SerializedName("Name")
    private String name = "";

    @SerializedName("CoinName")
    private String coinName = "";

    private Double price = null;

    private boolean isFavourited = false;

    public boolean isFavourited() {
        return isFavourited;
    }

    public void toggleFavourited() {
        isFavourited = !isFavourited;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
