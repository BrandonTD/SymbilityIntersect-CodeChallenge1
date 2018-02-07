package brandondo.cryptocharts;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import brandondo.cryptocharts.Models.CryptoCurrency;
import brandondo.cryptocharts.Models.CryptoResponse;
import brandondo.cryptocharts.Service.ServiceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by BrandonTD on 2018-02-06.
 */

public class CryptoProvider {
    private static CryptoProvider cryptoProvider;
    private ServiceManager serviceManager;

    private CryptoProvider() {
        serviceManager = ServiceManager.getInstance();
    }

    public static CryptoProvider getInstance() {
        if (cryptoProvider == null) {
            return new CryptoProvider();
        }

        return cryptoProvider;
    }

    public void getCryptoData() {
        serviceManager.getCoins()
                .enqueue(new Callback<CryptoResponse>() {

                    // this does not run on the main thread
                    @Override
                    public void onResponse(Call<CryptoResponse> call, Response<CryptoResponse> response) {
                        if(response.isSuccessful()) {
                            List<CryptoCurrency> currencies = new ArrayList<CryptoCurrency>();
                            currencies.addAll(response.body().getCurrencyList().values());

                            for (CryptoCurrency currency : currencies) {
                                Log.d("GetCryptoData","Successful: " + currency.getName());
                                String currencyName = currency.getName();
                                serviceManager.getPrices(currencyName, "CAD");
                            }
                        }
                        Log.d("GetCryptoData","Unsuccessful: " + response.toString());
                    }

                    @Override
                    public void onFailure(Call<CryptoResponse> call, Throwable t) {
                        Log.d("GetCryptoData","Error: CoinList response onFailure "+t.getMessage());
                    }
                });
    }
}
