package com.anunciadores.dto;

import java.util.List;

public class ListasCombos {

    private List<ItemCombo> listaGenero;
    private List<ItemCombo> estadoCivil;
    private List<ItemCombo> listaEscolaridad;
    private List<ItemCombo> listaDocuemntos;

    public List<ItemCombo> getListaGenero() {
        return listaGenero;
    }

    public void setListaGenero(List<ItemCombo> listaGenero) {
        this.listaGenero = listaGenero;
    }

    public List<ItemCombo> getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(List<ItemCombo> estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public List<ItemCombo> getListaEscolaridad() {
        return listaEscolaridad;
    }

    public void setListaEscolaridad(List<ItemCombo> listaEscolaridad) {
        this.listaEscolaridad = listaEscolaridad;
    }

    public List<ItemCombo> getListaDocuemntos() {
        return listaDocuemntos;
    }

    public void setListaDocuemntos(List<ItemCombo> listaDocuemntos) {
        this.listaDocuemntos = listaDocuemntos;
    }
}
