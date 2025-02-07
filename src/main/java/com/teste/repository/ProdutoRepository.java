package com.teste.repository;

import java.util.List;
import java.util.Optional;

import com.teste.model.Produto;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepository<Produto> {
    @Inject
    EntityManager em;

    public boolean temMovimentacao(Long codigo) {
        String query = "SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END FROM ItemNotaFiscal i WHERE i.produto.codigo = :codigo";
        return em.createQuery(query, Boolean.class)
                .setParameter("codigo", codigo)
                .getSingleResult();
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