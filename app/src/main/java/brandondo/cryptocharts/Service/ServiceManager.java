package brandondo.cryptocharts.Service;

import java.util.HashMap;

import brandondo.cryptocharts.Models.CryptoResponse;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Singleton service manager that handles all http requests.
 */
public class ServiceManager {
    private static ServiceManager serviceManager;
    private CoinService coinService;
    private PriceService priceService;
    private final String COIN_URL = "https://cryptocompare.com/api/";
    private final String PRICE_URL = "https://min-api.cryptocompare.com/";

    private ServiceManager() {
        OkHttpClient client = new OkHttpClient();

        final Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                // Important to set a client on the build to prevent .build() from making new ones
                .client(client);

        // Different service for each api since they have different base urls.
        priceService = retrofitBuilder.baseUrl(PRICE_URL).build().create(PriceService.class);
        coinService = retrofitBuilder.baseUrl(COIN_URL).build().create(CoinService.class);
    }

    public static ServiceManager getInstance() {
        if (serviceManager == null) {
            serviceManager = new ServiceManager();
            return serviceManager;
        }

        return serviceManager;
    }

    public Call<CryptoResponse> getCoins() {
        return coinService.getCoins();
    }

    public Call<HashMap<String, Double>> getPrices(String fromCurrency, String toCurrency) {
        return priceService.getPrices(fromCurrency, toCurrency);
    }
}
