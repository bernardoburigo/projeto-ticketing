package com.dam.backend.shared.utils;

import com.dam.backend.shared.exceptions.ClasseNaoInstanciavelException;
import com.dam.backend.shared.utils.dto.PaginarDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public final class PaginationUtil {

    private PaginationUtil() {
        throw new ClasseNaoInstanciavelException();
    }

    public static PageRequest paginar(PaginarDTO dto) {
        Sort.Direction sortDirection = dto.direction().equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return PageRequest.of(dto.page(), dto.size(), Sort.by(sortDirection, dto.orderby()));
    }

    public static <T> Page<T> paginaVazia(PageRequest pageRequest) {
        return Page.empty(pageRequest);
    }
}
