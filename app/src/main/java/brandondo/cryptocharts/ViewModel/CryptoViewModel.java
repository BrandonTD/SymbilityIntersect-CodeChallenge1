package brandondo.cryptocharts.ViewModel;

import android.content.Context;

import brandondo.cryptocharts.CryptoProvider;

public class CryptoViewModel {
    private Context context;
    private CryptoProvider cryptoProvider;

    public CryptoViewModel(Context context) {
        this.context = context;
        cryptoProvider = CryptoProvider.getInstance();
   }

   public void getCrypto() {
       cryptoProvider.getCryptoData();
   }

}
