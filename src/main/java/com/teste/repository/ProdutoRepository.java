package com.teste.repository;

import com.teste.model.Produto;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepository<Produto> {
    public boolean temMovimentacao(Long codigo) {
        return find("produto.codigo", codigo).count() > 0;
    }
}