package br.com.monthbalancecontrolandroidapp.ui.home;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

//import com.google.firebase.Firebase;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.Firebase;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.LocalCacheSettings;
import com.google.firebase.firestore.PersistentCacheSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.model.Document;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import br.com.monthbalancecontrolandroidapp.database.collection.base.BaseCollectionService;
import br.com.monthbalancecontrolandroidapp.database.collection.document.PeriodoDocument;
import br.com.monthbalancecontrolandroidapp.database.collection.document.PeriodoMesDocument;
import br.com.monthbalancecontrolandroidapp.database.collection.impl.PeriodosMesService;
import br.com.monthbalancecontrolandroidapp.database.collection.impl.PeriodosService;
import br.com.monthbalancecontrolandroidapp.database.config.FirestoreConfig;
import br.com.monthbalancecontrolandroidapp.databinding.FragmentHomeBinding;
import br.com.monthbalancecontrolandroidapp.testein.impl.ClassMainTeste;
import br.com.monthbalancecontrolandroidapp.testein.impl.ServiceFactoryInstance;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private static final String TAG = "DocSnippets";

    private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(2, 4,
            60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        FirestoreConfig.useLocalDatabaseSet();
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ClassMainTeste t = ServiceFactoryInstance.getInstance().getClassImpl();

        t.teste();

        PeriodosService periodosService = new PeriodosService();
        PeriodosMesService periodosMesService = new PeriodosMesService();

        //Começo
//
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        db.useEmulator("10.0.2.2", 8080);
//
//
////        db.enableNetwork();
//
//        Map<String, Object> cityAsMap = new HashMap<>();
//        cityAsMap.put("name", "Los Angeles");
//        cityAsMap.put("state", "CA");
//        cityAsMap.put("country", "USA");
//
//
//        cityAsMap.put("capital", "ZZ");
//        cityAsMap.put("population", "2000");
//        cityAsMap.put("regions", Arrays.asList("1", "2", "3"));
//        db.collection("cities").document("LA")
//                .set(cityAsMap)
//                //.set(city, SetOptions.merge()); // Junta com dados ja existentes no doc
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Log.d(TAG, "DocumentSnapshot successfully written!");
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error writing document", e);
//                    }
//                });
//
//        City city = new City("Los Angeles", "CA", "USA",
//                false, 5000000L, Arrays.asList("west_coast", "sorcal"));
//        db.collection("cities").add(city).
//                addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error adding document", e);
//                    }
//                }).addOnCanceledListener(new Executor() {
//                    @Override
//                    public void execute(Runnable command) {
//                        Log.d(TAG, command.toString());
//                    }
//                }, new OnCanceledListener() {
//                    @Override
//                    public void onCanceled() {
//                        Log.d(TAG, "");
//                    }
//                });

//
//        DocumentReference docRef = db.collection("cities").document("BJ");
//        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                if (documentSnapshot.exists()) {
//                    try {
//                        City city = documentSnapshot.toObject(City.class);
//                        System.out.println(city.toString());
//
//                    } catch (Exception e) {
//                        System.out.println(e.getMessage());
//                    }
//
//                }
//            }
//        });

        //Fim


        BaseCollectionService.ServiceCallback callback = new BaseCollectionService.ServiceCallback<DocumentReference>() {
            @Override
            public OnCompleteListener<DocumentReference> onComplete() {
                return task -> {
                    if (task.isComplete()) {
                        Log.d("Complete", "Uhu");
                    } else {
                        Log.e("Err", "Not complete");
                    }
                };
            }

            @Override
            public OnFailureListener onFailure() {
                return e -> {
                    Log.e("Err", e.getLocalizedMessage());
                };
            }

            @Override
            public OnSuccessListener<DocumentReference> onSuccess() {
                return new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        if (Objects.nonNull(documentReference)) {
                            Log.d("OK", documentReference.getPath());
                        }
                    }
                };
            }

            @Override
            public OnCanceledListener onCancelled() {
                return new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        Log.d("Cancel", "Err");
                    }
                };
            }
        };

//        Objects.requireNonNull(requireActivity()).runOnUiThread(() -> {
//            try {
//        periodosService.add(
//                new PeriodoDocument("Periodo 1", 1,
//                        10, true));
//
//        periodosMesService.add(new PeriodoMesDocument("12", "Teste"));

        periodosService.add(
                new PeriodoDocument("Periodo 1", 1,
                        10, true, UUID.randomUUID().toString()),
                callback
        );
        periodosMesService.add(new PeriodoMesDocument("12", "Teste"),
                callback);

//            } catch (Exception e) {
//                Log.d("Err", e.getLocalizedMessage());
//            }
//        });

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}