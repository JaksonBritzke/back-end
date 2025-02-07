package com.teste.model;

import java.math.BigDecimal;

import org.hibernate.annotations.Formula;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ItemNotaFiscal {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private NotaFiscal notaFiscal;

    @ManyToOne
    private Produto produto;

    private BigDecimal valorUnitario;

    private Integer quantidade;

    @Formula("valor_unitario * quantidade")
    private BigDecimal valorTotal;
}