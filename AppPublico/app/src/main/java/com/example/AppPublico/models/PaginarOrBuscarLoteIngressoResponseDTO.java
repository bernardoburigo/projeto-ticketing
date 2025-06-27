package com.example.AppPublico.models;

import java.util.List;

public class PaginarOrBuscarLoteIngressoResponseDTO {
    private List<TipoIngressoComLotesResponseDTO> tiposIngresso;

    public List<TipoIngressoComLotesResponseDTO> getTiposIngresso() {
        return tiposIngresso;
    }

    public void setTiposIngresso(List<TipoIngressoComLotesResponseDTO> tiposIngresso) {
        this.tiposIngresso = tiposIngresso;
    }
}
