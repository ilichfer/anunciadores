package com.anunciadores.dto;

import java.util.List;

public class RequestAgregarMinisterio {
    private List<PersonaMinisterio> listaSeleccionados;

    public List<PersonaMinisterio> getListaSeleccionados() {
        return listaSeleccionados;
    }

    public void setListaSeleccionados(List<PersonaMinisterio> listaSeleccionados) {
        this.listaSeleccionados = listaSeleccionados;
    }
}
