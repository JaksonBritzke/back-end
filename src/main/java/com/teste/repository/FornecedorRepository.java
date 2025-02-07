package com.teste.repository;

import com.teste.model.Fornecedor;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FornecedorRepository implements PanacheRepository<Fornecedor> {
    public boolean temMovimentacao(Long codigo) {
        return find("fornecedor.codigo", codigo).count() > 0;
    }
}