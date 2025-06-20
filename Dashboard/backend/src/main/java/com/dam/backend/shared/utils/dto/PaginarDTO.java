package com.dam.backend.shared.utils.dto;

public record PaginarDTO(Integer page, Integer size, String orderby, String direction, String search) {
}
