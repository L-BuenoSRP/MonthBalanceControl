package br.com.monthbalancecontrolandroidapp.database.collection.document;

import java.util.HashMap;
import java.util.Map;

import br.com.monthbalancecontrolandroidapp.database.collection.document.base.FirebaseDocument;

public class PeriodoMesDocument extends FirebaseDocument {
    private String id;
    private String descricao;

    public PeriodoMesDocument(String id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
