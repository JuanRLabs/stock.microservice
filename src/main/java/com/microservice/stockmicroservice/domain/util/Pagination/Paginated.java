package com.microservice.stockmicroservice.domain.util.Pagination;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class Paginated<T> {
    List<T> content;
    int totalPages;
    long totalElements;

    public Paginated(List<T> content, int totalPages, long totalElements) {
        this.content = content;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public Paginated() {
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public <U> Paginated<U> map(Function<T, U> mapper) {
        List<U> mappedContent = new ArrayList<>();
        for (T element : content) {
            mappedContent.add(mapper.apply(element));
        }
        return new Paginated<>(mappedContent, totalPages, totalElements);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paginated<?> that = (Paginated<?>) o;
        return totalPages == that.totalPages && totalElements == that.totalElements && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, totalPages, totalElements);
    }
}
