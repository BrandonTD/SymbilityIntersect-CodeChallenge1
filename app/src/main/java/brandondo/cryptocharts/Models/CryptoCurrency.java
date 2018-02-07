package brandondo.cryptocharts.Models;

import com.google.gson.annotations.SerializedName;

public class CryptoCurrency {
    @SerializedName("Id")
    String id = "";

    @SerializedName("Name")
    String name = "";

    @SerializedName("CoinName")
    String coinName = "";

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
}
