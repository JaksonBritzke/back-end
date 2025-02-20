package com.teste.repository;

import java.util.List;
import java.util.Optional;

import com.teste.model.NotaFiscal;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NotaFiscalRepository implements PanacheRepository<NotaFiscal> {
    public List<NotaFiscal> buscarPorFornecedor(Long codigoFornecedor) {
        return list("fornecedor.codigo", codigoFornecedor);
    }

    public Optional<NotaFiscal> findByNumeroOptional(Long numero) {
        return find("numero", numero).firstResultOptional();
    }


}