package com.teste.repository;

import java.util.List;
import java.util.Optional;

import com.teste.model.Fornecedor;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class FornecedorRepository implements PanacheRepository<Fornecedor> {

    @Inject
    EntityManager em;

    public boolean temMovimentacao(Long codigo) {
        String query = "SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END FROM NotaFiscal i WHERE i.fornecedor.codigo = :codigo";
        return em.createQuery(query, Boolean.class)
                .setParameter("codigo", codigo)
                .getSingleResult();
    }

    public Optional<Fornecedor> findByDescricao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição não pode ser nula ou vazia");
        }
        return find("descricao", descricao).firstResultOptional();
    }

    public List<Fornecedor> findByRazaoSocialLike(String razaoSocial) {
        if (razaoSocial == null || razaoSocial.trim().isEmpty()) {
            throw new IllegalArgumentException("O Campo razão social não pode ser nula ou vazia");
        }
        return find("razaoSocial ILIKE ?1", "%" + razaoSocial.trim() + "%").list();
    }
}