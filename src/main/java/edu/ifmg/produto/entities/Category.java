package edu.ifmg.produto.entities;

import edu.ifmg.produto.dtos.CategoryDTO;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;

@Entity //diz que Category é um "model" é uma tabela no banco
//@Table(name= "tb_category") é a diretiva pra nomear a tabela que não terá o mesmo nome da classe

public class Category { //logo a tabela será categories

    @Id //sempre importar do jakarta
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updatedAt;

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(){

    }

    public Category(CategoryDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return name;
    }

    public void setNome(String nome) {
        this.name = nome;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    @PrePersist
    private void prePersist() {
        createdAt = Instant.now();
    }

    @PreUpdate
    private void preUpdate() {
        updatedAt = Instant.now();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", nome='" + name + '\'' +
                '}';
    }
}
