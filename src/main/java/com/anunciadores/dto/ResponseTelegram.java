package com.anunciadores.dto;

import java.util.ArrayList;

public class ResponseTelegram {
    public boolean ok;
    public ArrayList<ResultDTO> result;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public ArrayList<ResultDTO> getResult() {
        return result;
    }

    public void setResult(ArrayList<ResultDTO> result) {
        this.result = result;
    }
}
