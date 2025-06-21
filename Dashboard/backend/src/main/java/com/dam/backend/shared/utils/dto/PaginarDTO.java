package com.dam.backend.shared.utils.dto;

public record PaginarDTO(
        Integer page,
        Integer size,
        String orderby,
        String direction,
        String search
) {

    public static PaginarDTO valueDefaults(
            Integer page,
            Integer size,
            String orderby,
            String direction,
            String search
    ) {
        return new PaginarDTO(
                page != null ? page : 0,
                size != null ? size : 10,
                orderby != null ? orderby : "id",
                direction != null ? direction : "DESC",
                search != null ? search : ""
        );
    }
}
