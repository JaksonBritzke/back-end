package com.teste.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
    @GeneratedValue
    private Long id;

    private Long numero;

    private LocalDateTime dataEmissao;

    @ManyToOne
    private Fornecedor fornecedor;

    private BigDecimal valorTotal;

    @OneToMany(mappedBy = "notaFiscal", cascade = CascadeType.ALL)
    private List<ItemNotaFiscal> itens;
}