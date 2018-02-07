package brandondo.cryptocharts.Utility;

/**
 * Interface for listener for data loading. Notifies when data has been loaded.
 */
public interface LoadCryptoListener {
    void onDataReady();

    void notifyReady();
}
