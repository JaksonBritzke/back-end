package com.teste.repository;

import java.util.List;
import java.util.Optional;

import com.teste.model.Produto;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepository<Produto> {
    public boolean temMovimentacao(Long codigo) {
        return find("produto.codigo", codigo).count() > 0;
    }

    public Optional<Produto> findByDescricao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição não pode ser nula ou vazia");
        }
        return find("descricao", descricao).firstResultOptional();
    }

    public List<Produto> findByDescricaoLike(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição não pode ser nula ou vazia");
        }
        return find("descricao LIKE ?1", "%" + descricao + "%").list();
    }
}