package com.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Author author = (Author) object;
        return id == author.id || firstName.equalsIgnoreCase(author.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName);
    }
}