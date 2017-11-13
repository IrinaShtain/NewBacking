package ua.shtain.irina.newbacking.domain;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Irina Shtain on 13.11.2017.
 */

public abstract class NetworkRepository {
    private final static int THREAD_COUNT = 6;
    private ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(THREAD_COUNT);

    public <T> Observable<T> getNetworkObservable(Observable<T> observable) {
        return observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread());
    }

    public <T> Observable<T> getNetworkFixedObservable(Observable<T> observable) {
        return observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.from(threadPoolExecutor));
    }
}
