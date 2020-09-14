package com.max.network.download;

import android.annotation.SuppressLint;


import com.max.network.base.BaseObserver;
import com.max.network.manage.RxHttpManager;

import java.io.IOException;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.ResponseBody;

public abstract class DownloadObserver extends BaseObserver<ResponseBody> {

    private String fileName;
    private String destFileDir;

    public DownloadObserver(String fileName) {
        this.fileName = fileName;
    }

    public DownloadObserver(String fileName, String destFileDir) {
        this.fileName = fileName;
        this.destFileDir = destFileDir;
    }


    /**
     * 失败回调
     *
     * @param errorMsg errorMsg
     */
    protected abstract void onError(String errorMsg);


    /**
     * 成功回调
     *
     * @param filePath filePath
     */
    /**
     * 成功回调
     *
     * @param bytesRead     已经下载文件的大小
     * @param contentLength 文件的大小
     * @param progress      当前进度
     * @param done          是否下载完成
     * @param filePath      文件路径
     */
    protected abstract void onSuccess(long bytesRead, long contentLength, float progress, boolean done, String filePath);


    @Override
    public void doOnError(String errorMsg) {
        onError(errorMsg);
    }

    @Override
    public void doOnSubscribe(Disposable d) {
        RxHttpManager.get().add(setTag(), d);
    }

    @SuppressLint("CheckResult")
    @Override
    public void doOnNext(ResponseBody responseBody) {
        Observable
                .just(responseBody)
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            new DownloadManager().saveFile(responseBody, fileName, destFileDir, new ProgressListener() {
                                @Override
                                public void onResponseProgress(final long bytesRead, final long contentLength, final int progress, final boolean done, final String filePath) {
                                    Observable
                                            .just(progress)
                                            .distinctUntilChanged()
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(new Consumer<Integer>() {
                                                @Override
                                                public void accept(@NonNull Integer integer) throws Exception {
                                                    onSuccess(bytesRead, contentLength, progress, done, filePath);
                                                }
                                            });
                                }

                            });

                        } catch (IOException e) {
                            Observable
                                    .just(e.getMessage())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Consumer<String>() {
                                        @Override
                                        public void accept(String s) throws Exception {
                                            doOnError(s);
                                        }
                                    });
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void doOnCompleted() {

    }
}
