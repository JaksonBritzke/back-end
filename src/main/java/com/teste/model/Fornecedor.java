package com.teste.model;

import java.time.LocalDate;
import java.util.List;

import com.teste.model.enums.SituacaoFornecedor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Fornecedor {
    @Id
    @GeneratedValue
    private Long codigo;

    private String razaoSocial;

    private String email;

    private String telefone;

    @Column(unique = true)
    private String cnpj;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SituacaoFornecedor situacao = SituacaoFornecedor.ATIVO;

    private LocalDate dataBaixa;

    @OneToMany(mappedBy = "fornecedor")
    private List<NotaFiscal> notas;
}