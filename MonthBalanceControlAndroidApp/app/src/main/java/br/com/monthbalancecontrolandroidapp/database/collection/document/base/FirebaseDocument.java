package br.com.monthbalancecontrolandroidapp.database.collection.document.base;

import br.com.monthbalancecontrolandroidapp.database.collection.document.contract.DatabaseDocumentDefinitions;

public abstract class FirebaseDocument implements DatabaseDocumentDefinitions {

    private String userId;
    public FirebaseDocument(){

    }
    public FirebaseDocument(String userId){
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}



