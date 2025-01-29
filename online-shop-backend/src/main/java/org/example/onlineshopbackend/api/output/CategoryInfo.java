package org.example.onlineshopbackend.api.output;

import org.example.onlineshopbackend.model.entity.Category;

public record CategoryInfo(
        Long id,
        String name
) {

    public static CategoryInfo categoryToInfo(Category category) {
        return new CategoryInfo(category.getId(), category.getName());
    }

    public Category infoToCategory() {
        Category category = new Category();
        category.setName(name);
        return category;
    }
}
