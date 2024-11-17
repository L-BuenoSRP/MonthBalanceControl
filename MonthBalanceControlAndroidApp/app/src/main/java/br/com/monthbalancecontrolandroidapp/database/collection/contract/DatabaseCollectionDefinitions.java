package br.com.monthbalancecontrolandroidapp.database.collection.contract;

import com.google.firebase.firestore.DocumentReference;

import java.util.Map;

import br.com.monthbalancecontrolandroidapp.database.collection.base.BaseCollectionService;
import br.com.monthbalancecontrolandroidapp.database.collection.impl.PeriodosMesService;

public interface DatabaseCollectionDefinitions<T> {
    void add(T object);

    void add(T object, BaseCollectionService.ServiceCallback<DocumentReference> callback);

    void update(T object, String documentKey);
    void updateMerge(Map<String, Object> objectMap, String documentKey);
    void updateFieldIncrement(String documentKey, String fieldName,int incrementNumber);
    void remove(String documentKey);
//    Class<R> getServiceClass();
}
