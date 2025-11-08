package com.biblestudy.portal.model;

import jakarta.persistence.*;

@Entity
@Table(name = "verses")
public class Verse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reference;

    @Column(columnDefinition = "TEXT")
    private String content;

    public Verse() {}

    public Verse(String reference, String content) {
        this.reference = reference;
        this.content = content;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
