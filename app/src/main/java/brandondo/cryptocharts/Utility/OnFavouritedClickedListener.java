package brandondo.cryptocharts.Utility;

import android.view.View;

/**
 * Interface for listener when favourite button for each currency is clicked.
 * Handles updating list.
 */
public interface OnFavouritedClickedListener {
    void onClick(View v, int position);
}
