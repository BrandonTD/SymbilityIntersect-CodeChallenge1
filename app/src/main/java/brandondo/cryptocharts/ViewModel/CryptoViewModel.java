package brandondo.cryptocharts.ViewModel;

import android.content.Context;

import java.util.List;

import brandondo.cryptocharts.CryptoProvider;
import brandondo.cryptocharts.Models.CryptoCurrency;
import brandondo.cryptocharts.Utility.LoadCryptoListener;

public class CryptoViewModel {
    private Context context;
    private CryptoProvider cryptoProvider;

    public CryptoViewModel(Context context) {
        this.context = context;
        cryptoProvider = CryptoProvider.getInstance();
   }

   public void loadCrypto() {
       cryptoProvider.getCryptoData();
   }

   public List<CryptoCurrency> getData() {
       return cryptoProvider.getCurrencyData();
   }

   public void setDataListener(LoadCryptoListener listener) {
       cryptoProvider.setLoadCryptoListener(listener);
   }
}
