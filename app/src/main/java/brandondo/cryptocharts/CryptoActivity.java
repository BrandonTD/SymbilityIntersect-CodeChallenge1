package brandondo.cryptocharts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import java.util.function.Function;

import brandondo.cryptocharts.Models.CryptoCurrency;
import brandondo.cryptocharts.Utility.OnFavouritedClickedListener;

public class CryptoActivity extends AppCompatActivity {

    private CryptoProvider cryptoProvider;
    private RecyclerView mRecyclerView;
    private CryptoRecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cryptoProvider = CryptoProvider.getInstance();
        mRecyclerView = (RecyclerView) findViewById(R.id.crypto_recycler);

        // improve performance since content does not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter
        mAdapter = new CryptoRecyclerAdapter(cryptoProvider.getCurrencyData());
        mAdapter.setOnFavouriteClickedListener(new OnFavouritedClickedListener() {
            @Override
            public void onClick(View v, int position) {
                CryptoProvider cryptoProvider = CryptoProvider.getInstance();
                CryptoCurrency currency =
                        CryptoProvider.getInstance()
                                .getCurrencyData()
                                .get(position);
                cryptoProvider.toggleFavourited(position);
                mAdapter.updateFavouriteImage((FrameLayout)v, currency.isFavourited());
                mAdapter.notifyDataSetChanged();
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }
}
