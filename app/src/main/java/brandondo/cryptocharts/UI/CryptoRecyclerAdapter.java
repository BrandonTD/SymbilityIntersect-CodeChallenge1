package brandondo.cryptocharts.UI;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import brandondo.cryptocharts.Models.CryptoCurrency;
import brandondo.cryptocharts.R;
import brandondo.cryptocharts.Utility.OnFavouritedClickedListener;

import static android.view.View.GONE;

public class CryptoRecyclerAdapter extends RecyclerView.Adapter<CryptoRecyclerAdapter.CryptoViewHolder> {
    private List<CryptoCurrency> data;
    private OnFavouritedClickedListener favouritedClickedListener;

    public static class CryptoViewHolder extends RecyclerView.ViewHolder {
        TextView currencyName;
        TextView currencyPrice;
        ImageView favouritedStar;
        ImageView notFavouritedStar;
        FrameLayout favouriteButton;

        public CryptoViewHolder(View view, final OnFavouritedClickedListener listener) {
            super(view);
            currencyName = (TextView) view.findViewById(R.id.currency_name);
            currencyPrice = (TextView) view.findViewById(R.id.currency_price);
            favouritedStar = (ImageView) view.findViewById(R.id.filled_star);
            notFavouritedStar = (ImageView) view.findViewById(R.id.unfilled_star);
            favouriteButton = (FrameLayout) view.findViewById(R.id.favourite_button);
            favouriteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(view, getAdapterPosition());
                }
            });
        }
    }

    public CryptoRecyclerAdapter(List<CryptoCurrency> data) {
        this.data = data;
        Log.d("adapter data", "" + data.size());
    }

    @Override
    public CryptoRecyclerAdapter.CryptoViewHolder onCreateViewHolder(ViewGroup parent,
                                                                     int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_crypto_currency, parent, false);

        CryptoViewHolder viewHolder = new CryptoViewHolder(view, favouritedClickedListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CryptoViewHolder viewHolder, int position) {
        CryptoCurrency currency = data.get(position);
        String currencyName = currency.getCoinName();
        Double currencyPrice = currency.getPrice();
        boolean isFavourited = currency.isFavourited();

        viewHolder.currencyName.setText(currencyName);
        if (currencyPrice != null) {
            viewHolder.currencyPrice.setText(String.valueOf(currencyPrice));
        }

        updateFavouriteImage(viewHolder.favouriteButton, isFavourited);
    }

    // Return the size of your dataset
    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnFavouriteClickedListener(OnFavouritedClickedListener listener) {
        this.favouritedClickedListener = listener;
    }

    // update the favourite button image.
    public void updateFavouriteImage(FrameLayout view, boolean isFavourited) {
        ImageView favouritedStar = (ImageView) view.findViewById(R.id.filled_star);
        ImageView notFavouritedStar = (ImageView) view.findViewById(R.id.unfilled_star);
        if (isFavourited) {
            notFavouritedStar.setVisibility(GONE);
            favouritedStar.setVisibility(View.VISIBLE);
        } else {
            favouritedStar.setVisibility(GONE);
            notFavouritedStar.setVisibility(View.VISIBLE);
        }
    }
}