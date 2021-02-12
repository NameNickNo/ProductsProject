package com.example.project.detail.service.analityc;

public class DetailNode {
    private Long id;
    private Long parentId;

    public DetailNode(Long id, Long parentId) {
        this.id = id;
        this.parentId = parentId;
    }

    public Long getId() {
        return id;
    }

    public Long getParentId() {
        return parentId;
    }
}
