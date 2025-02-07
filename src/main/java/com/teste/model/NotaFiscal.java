package com.teste.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class NotaFiscal {
    @Id
    private String numero;

    private LocalDateTime dataEmissao;

    @ManyToOne
    private Fornecedor fornecedor;

    private String endereco;

    private BigDecimal valorTotal;

    @OneToMany(mappedBy = "notaFiscal", cascade = CascadeType.ALL)
    private List<ItemNotaFiscal> itens;
}