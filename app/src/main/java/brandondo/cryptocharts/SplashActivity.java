package brandondo.cryptocharts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import brandondo.cryptocharts.Utility.LoadCryptoListener;
import brandondo.cryptocharts.ViewModel.CryptoViewModel;

public class SplashActivity extends AppCompatActivity {

    CryptoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new CryptoViewModel(this);
        viewModel.loadCrypto();

        viewModel.setDataListener(new LoadCryptoListener() {
            @Override
            public void onDataReady() {
                Intent intent = new Intent(SplashActivity.this, CryptoActivity.class);

                // Start main activity
                startActivity(intent);

                // close splash activity
                finish();
            }

            @Override
            public void notifyReady() {
                onDataReady();
            }
        });
    }
}
