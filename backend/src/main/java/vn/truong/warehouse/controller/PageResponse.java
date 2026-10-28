package vn.truong.warehouse.controller;
import java.util.List;
import org.springframework.data.domain.Page;
public record PageResponse<T>(List<T> content, int number, int totalPages, long totalElements) {
    public PageResponse(Page<T> page) { this(page.getContent(), page.getNumber(), page.getTotalPages(), page.getTotalElements()); }
}
