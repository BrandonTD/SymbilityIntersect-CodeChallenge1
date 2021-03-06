package brandondo.cryptocharts;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import brandondo.cryptocharts.Models.CryptoCurrency;
import brandondo.cryptocharts.Models.CryptoResponse;
import brandondo.cryptocharts.Service.ServiceManager;
import brandondo.cryptocharts.Utility.LoadCryptoListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Singleton crypto provider that keeps track of all currency instances.
 * Also delegates service calls to the service manager.
 */
public class CryptoProvider {
    private static CryptoProvider cryptoProvider = null;
    private ServiceManager serviceManager;
    private List<CryptoCurrency> currencyData;
    private LoadCryptoListener loadCryptoListener;
    private int loadedData;
    private int numCurrencies;
    private int numFavourited;

    private CryptoProvider() {
        serviceManager = ServiceManager.getInstance();
        currencyData = new ArrayList<>();
        numFavourited = 0;
    }

    public static CryptoProvider getInstance() {
        if (cryptoProvider == null) {
            cryptoProvider = new CryptoProvider();
            return cryptoProvider;
        }

        return cryptoProvider;
    }

    public void getCryptoData() {
        loadedData = 0;
        currencyData.clear();

        // Get coins then use coins to get prices.
        serviceManager.getCoins()
                .enqueue(new Callback<CryptoResponse>() {

                    // Retrofit performs this in a worker thread.
                    @Override
                    public void onResponse(Call<CryptoResponse> call, Response<CryptoResponse> response) {
                        if (response.isSuccessful()) {
                            List<CryptoCurrency> currencies = new ArrayList<>();
                            currencies.addAll(response.body().getCurrencyList().values());
                            numCurrencies = currencies.size();

                            // Get the price for each currency.
                            for (CryptoCurrency currency : currencies) {
                                Log.d("GetCryptoData", "Successful: " + currency.getName());
                                retrieveCurrencyPrice(currency);
                            }
                        }
                        Log.d("GetCryptoData", "Unsuccessful: " + response.toString());
                    }

                    @Override
                    public void onFailure(Call<CryptoResponse> call, Throwable t) {
                        Log.d("GetCryptoData", "Error: CoinList response onFailure " + t.getMessage());
                    }
                });
    }

    private void retrieveCurrencyPrice(final CryptoCurrency currency) {
        serviceManager.getPrices(currency.getName(), "CAD")
                .enqueue(new Callback<HashMap<String, Double>>() {

                    @Override
                    public void onResponse(Call<HashMap<String, Double>> call, Response<HashMap<String, Double>> response) {
                        if (response.isSuccessful()) {
                            //We are only interested in the CAD conversion.
                            Double price = response.body().get("CAD");
                            Log.d("GetCryptoPrice", "Successful: " + currency.getName() + " : " + price);
                            currency.setPrice(price);
                            currencyData.add(currency);
                        }
                        Log.d("GetCryptoPrice", "Unsuccessful: " + response.toString());
                        handleLoadDataFinish();
                    }

                    @Override
                    public void onFailure(Call<HashMap<String, Double>> call, Throwable t) {
                        Log.d("GetCryptoPrice", "Error: CoinList response onFailure " + t.getMessage());
                        handleLoadDataFinish();
                    }
                });
    }

    // Once all queries have finished, notify to start main activity.
    private void handleLoadDataFinish() {
        loadedData += 1;
        Log.d("data load finish", "" + currencyData.size());
        if (numCurrencies == loadedData) {
            loadCryptoListener.notifyReady();
        }
    }

    public List<CryptoCurrency> getCurrencyData() {
        return currencyData;
    }

    public void setLoadCryptoListener(LoadCryptoListener loadCryptoListener) {
        this.loadCryptoListener = loadCryptoListener;
    }

    public void toggleFavourited(int position) {
        CryptoCurrency currency = currencyData.get(position);
        currency.toggleFavourited();

        if (currency.isFavourited()) {
            numFavourited++;
            if (position != 0) {
                currencyData.remove(position);
                currencyData.add(0, currency);
            }
        } else {
            numFavourited--;
            currencyData.remove(position);
            currencyData.add(numFavourited, currency);
        }
    }
}
