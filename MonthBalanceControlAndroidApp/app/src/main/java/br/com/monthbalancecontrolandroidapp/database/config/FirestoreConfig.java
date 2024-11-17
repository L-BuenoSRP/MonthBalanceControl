package br.com.monthbalancecontrolandroidapp.database.config;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.MemoryCacheSettings;
import com.google.firebase.firestore.PersistentCacheSettings;

public class FirestoreConfig {
    private final FirebaseFirestore db;
    private static boolean useLocalDatabase;

    public FirestoreConfig() {
        db = FirebaseFirestore.getInstance();
//        if(useLocalDatabase){
//            String[] EMULATOR_HOST = {"10.0.2.2", "8080"};
//            db.useEmulator(EMULATOR_HOST[0], Integer.parseInt(EMULATOR_HOST[1]));
//        }

//        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
//                .setHost(String.format("%s:%s", EMULATOR_HOST[0], EMULATOR_HOST[1]))
//                .setLocalCacheSettings(PersistentCacheSettings.newBuilder()
//                        .setSizeBytes(localDatabasePersistenceSize).build()).build();
        if (useLocalDatabase) {
            String[] EMULATOR_HOST = {"10.0.2.2", "8080"};
            db.useEmulator(EMULATOR_HOST[0], Integer.parseInt(EMULATOR_HOST[1]));
            int localDatabasePersistenceSizeMb = 100;
            FirebaseFirestoreSettings settings =
                    new FirebaseFirestoreSettings.Builder(db.getFirestoreSettings())
                            .setHost(String.format("%s:%s", EMULATOR_HOST[0], EMULATOR_HOST[1]))
                            .setLocalCacheSettings(PersistentCacheSettings.newBuilder()
                                    // Set size to 100 MB
                                    .setSizeBytes(1024 * 1024 * localDatabasePersistenceSizeMb)
                                    .build())
                            .build();
            db.setFirestoreSettings(settings);
        }
//        else{
//            FirebaseFirestoreSettings settings =
//                    new FirebaseFirestoreSettings.Builder(db.getFirestoreSettings())
//                             Use memory-only cache
//                            .setLocalCacheSettings(MemoryCacheSettings.newBuilder().build())
//                             Use persistent disk cache (default)
//                            .setLocalCacheSettings(PersistentCacheSettings.newBuilder()
//                                    .build())
//                            .build();
//            db.setFirestoreSettings(settings);
//        }
    }

    public static void useLocalDatabaseSet(){
        useLocalDatabase = true;
    }
    public FirebaseFirestore getDb() {
        return db;
    }
}
