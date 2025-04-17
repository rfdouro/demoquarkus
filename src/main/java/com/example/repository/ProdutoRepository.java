package com.example.repository;

import com.example.model.Produto;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepository<Produto> {
    // Métodos customizados podem ser adicionados aqui
}
