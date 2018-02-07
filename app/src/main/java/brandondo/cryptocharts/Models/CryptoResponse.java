package brandondo.cryptocharts.Models;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class CryptoResponse {
    @SerializedName("Response")
    private String status = "";

    @SerializedName("BaseImageUrl")
    private String baseImageUrl = "";

    @SerializedName("BaseLinkUrl")
    private String baseLinkUrl = "";

    @SerializedName("Data")
    private HashMap<String, CryptoCurrency> currencyList = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBaseImageUrl() {
        return baseImageUrl;
    }

    public void setBaseImageUrl(String baseImageUrl) {
        this.baseImageUrl = baseImageUrl;
    }

    public String getBaseLinkUrl() {
        return baseLinkUrl;
    }

    public void setBaseLinkUrl(String baseLinkUrl) {
        this.baseLinkUrl = baseLinkUrl;
    }

    public HashMap<String, CryptoCurrency> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(HashMap<String, CryptoCurrency> currencyList) {
        this.currencyList = currencyList;
    }
}
