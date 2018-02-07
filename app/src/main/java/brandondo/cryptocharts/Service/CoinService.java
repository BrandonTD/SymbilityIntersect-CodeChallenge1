package brandondo.cryptocharts.Service;

import brandondo.cryptocharts.Models.CryptoResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CoinService {
    @GET("data/coinlist")
    Call<CryptoResponse> getCoins();
}
