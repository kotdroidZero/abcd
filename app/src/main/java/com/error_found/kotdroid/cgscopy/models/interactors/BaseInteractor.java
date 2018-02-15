package com.error_found.kotdroid.cgscopy.models.interactors;

import com.error_found.kotdroid.cgscopy.models.networkrequests.NetworkRequestCallbacks;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by user12 on 15/2/18.
 */

public abstract class BaseInteractor<T> {

    public Disposable getDisposable(Observable<Response<T>> observable,
                                    final NetworkRequestCallbacks networkRequestCallbacks) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<?>>() {

                    @Override
                    public void onNext(Response<?> response) {
                        networkRequestCallbacks.onSuccess(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        networkRequestCallbacks.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
