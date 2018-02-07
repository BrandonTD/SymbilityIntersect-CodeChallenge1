package brandondo.cryptocharts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import brandondo.cryptocharts.ViewModel.CryptoViewModel;

public class CryptoActivity extends AppCompatActivity {

    CryptoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new CryptoViewModel(this);
        viewModel.getCrypto();
    }
}
