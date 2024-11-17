package br.com.monthbalancecontrolandroidapp.database.collection.document;

import java.util.HashMap;
import java.util.Map;

import br.com.monthbalancecontrolandroidapp.database.collection.document.base.FirebaseDocument;

public class PeriodoDocument extends FirebaseDocument {

    private String descricao;
    private int numeroPeriodo;
    private int diaFim;
    private boolean ativo;

    public PeriodoDocument(String descricao, int numeroPeriodo, int diaFim, boolean ativo, String userId) {
        this.setUserId(userId);
        this.descricao = descricao;
        this.numeroPeriodo = numeroPeriodo;
        this.diaFim = diaFim;
        this.ativo = ativo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getNumeroPeriodo() {
        return numeroPeriodo;
    }

    public void setNumeroPeriodo(int numeroPeriodo) {
        this.numeroPeriodo = numeroPeriodo;
    }

    public int getDiaFim() {
        return diaFim;
    }

    public void setDiaFim(int diaFim) {
        this.diaFim = diaFim;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public static String getDocumentKey() {
        return "NomeChaveParaValorUnico";
    }
}
