package brandondo.cryptocharts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class CryptoActivity extends AppCompatActivity {

    private CryptoProvider cryptoProvider;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
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
        mRecyclerView.setAdapter(mAdapter);
    }
}
