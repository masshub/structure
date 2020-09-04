package com.max.structure.data.repository;

import androidx.lifecycle.MediatorLiveData;

import com.max.structure.persistence.AppDataBase;

import java.util.List;

/**
 * Created by Maker on 2020/9/3.
 * Description:
 */
public class DataRepository {
    private static DataRepository sInstance;

    private final AppDataBase mDatabase;
    private MediatorLiveData<List<Object>> mObservableProducts;

    private DataRepository(final AppDataBase database) {
        mDatabase = database;
        mObservableProducts = new MediatorLiveData<>();
//
//        mObservableProducts.addSource(mDatabase.productDao().loadAllProducts(),
//                productEntities -> {
//                    if (mDatabase.getDatabaseCreated().getValue() != null) {
//                        mObservableProducts.postValue(productEntities);
//                    }
//                });
    }

    public static DataRepository getInstance(final AppDataBase database) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    sInstance = new DataRepository(database);
                }
            }
        }
        return sInstance;
    }

    /**
     * Get the list of products from the database and get notified when the data changes.
     */
//    public LiveData<List<ProductEntity>> getProducts() {
//        return mObservableProducts;
//    }
//
//    public LiveData<ProductEntity> loadProduct(final int productId) {
//        return mDatabase.productDao().loadProduct(productId);
//    }
//
//    public LiveData<List<CommentEntity>> loadComments(final int productId) {
//        return mDatabase.commentDao().loadComments(productId);
//    }
//
//    public LiveData<List<ProductEntity>> searchProducts(String query) {
//        return mDatabase.productDao().searchAllProducts(query);
//    }
}
