package brandondo.cryptocharts.Models;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 * Model class for a http response
 */
public class CryptoResponse {
    @SerializedName("Response")
    private String status = "";

    @SerializedName("BaseImageUrl")
    private String baseImageUrl = "";

    @SerializedName("BaseLinkUrl")
    private String baseLinkUrl = "";

    @SerializedName("Data")
    private HashMap<String, CryptoCurrency> currencyList = null;

    public HashMap<String, CryptoCurrency> getCurrencyList() {
        return currencyList;
    }

}
