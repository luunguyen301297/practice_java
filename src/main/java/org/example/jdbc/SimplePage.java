package org.example.jdbc;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * @Author: luunguyen301297
 * @LastModified: 9/13/2024
 */
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SimplePage<T> {

    List<T> content;
    long totalElements;
    int page;
    int size;
    int totalPages;

    @JsonCreator
    public SimplePage(@JsonProperty("content") final List<T> content,
                      @JsonProperty("totalElements") final long totalElements,
                      @JsonProperty("page") final int page,
                      @JsonProperty("size") final int size) {
        this.content = content;
        this.totalElements = totalElements;
        this.page = page;
        this.size = size;
        this.totalPages = calculateTotalPages(totalElements, size);
    }

    @JsonProperty("content")
    public List<T> getContent() {
        return content;
    }

    @JsonProperty("page")
    public int getPage() {
        return page;
    }

    @JsonProperty("size")
    public int getSize() {
        return size;
    }

    @JsonProperty("totalElements")
    public long getTotalElements() {
        return totalElements;
    }

    @JsonProperty("totalPages")
    public int getTotalPages() {
        return totalPages;
    }

    @JsonProperty("number")
    public int getNumber() {
        return page;
    }

    private int calculateTotalPages(long totalElements, int size) {
        return (int) Math.ceil((double) totalElements / size);
    }

}
