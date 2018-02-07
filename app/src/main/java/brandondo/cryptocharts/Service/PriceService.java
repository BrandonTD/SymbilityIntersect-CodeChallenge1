package brandondo.cryptocharts.Service;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PriceService {
    @GET("data/price")
    Call<HashMap<String, Double>> getPrices(@Query("fsym") String currencies, @Query("tsyms") String toCurrencies);
}
