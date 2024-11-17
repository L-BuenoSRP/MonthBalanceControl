package br.com.monthbalancecontrolandroidapp.database.collection.base;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.SetOptions;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import br.com.monthbalancecontrolandroidapp.database.collection.contract.DatabaseCollectionDefinitions;
import br.com.monthbalancecontrolandroidapp.database.collection.document.base.FirebaseDocument;
import br.com.monthbalancecontrolandroidapp.database.config.FirestoreConfig;

public abstract class BaseCollectionService<T extends FirebaseDocument> implements DatabaseCollectionDefinitions<T> {
    public BaseCollectionService() {
        this.setCollectionName();
        firestoreConfig = new FirestoreConfig();
    }

    private void setCollectionName() {
        this.collectionName = this.getClass()
                .getSimpleName().replace("Service", "");
    }

    private String collectionName;
    FirestoreConfig firestoreConfig;

    @Override
    public void add(T object) {
        DocumentReference newCityRef = firestoreConfig.getDb().collection(collectionName).document();

        // Later...
        newCityRef.set(object.getObjectAsMap());
    }

    @Override
    public void add(T object, ServiceCallback<com.google.firebase.firestore.DocumentReference> callback) {
//        try {
        firestoreConfig.getDb().collection(collectionName).add(object)
                    .addOnCompleteListener(callback.onComplete())
                .addOnSuccessListener(callback.onSuccess())
                .addOnFailureListener(callback.onFailure())
                    .addOnCanceledListener(callback.onCancelled())
        ;
//        } catch (ExecutionException | InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public void update(T object, String documentKey) {
        try {
            Tasks.await(firestoreConfig.getDb().collection(collectionName).document(documentKey).set(object));
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateMerge(Map<String, Object> objectMap, String documentKey) {
        try {
            Tasks.await(firestoreConfig.getDb().collection(collectionName).document(documentKey)
                    .set(objectMap, SetOptions.merge()));
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateFieldIncrement(String documentKey, String fieldName, int incrementNumber) {
        try {
            Tasks.await(firestoreConfig.getDb().collection(collectionName).document(documentKey)
                    .update(fieldName, FieldValue.increment(incrementNumber)));
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(String documentKey) {
        try {
            Tasks.await(firestoreConfig.getDb().collection(collectionName).document(documentKey)
                    .delete());
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getCollectionName() {
        return collectionName;
    }

    public interface ServiceCallback<TResult> {
        OnCompleteListener<TResult> onComplete();

        OnFailureListener onFailure();

        OnSuccessListener<TResult> onSuccess();

        OnCanceledListener onCancelled();
    }
}
