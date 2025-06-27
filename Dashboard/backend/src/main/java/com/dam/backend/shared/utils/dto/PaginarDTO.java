package com.dam.backend.shared.utils.dto;

public record PaginarDTO(
        Integer page,
        Integer size,
        String orderby,
        String direction,
        String search
) {

    public static PaginarDTO valueDefaults(
            PaginarDTO dto
    ) {
        return new PaginarDTO(
                dto.page != null ? dto.page : 0,
                dto.size != null ? dto.size : 10,
                dto.orderby != null ? dto.orderby : "id",
                dto.direction != null ? dto.direction : "DESC",
                dto.search != null ? dto.search : ""
        );
    }
}
