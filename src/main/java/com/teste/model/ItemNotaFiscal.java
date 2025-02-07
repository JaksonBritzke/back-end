package com.teste.model;

import java.math.BigDecimal;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
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

    @JsonBackReference
    @ManyToOne
    private NotaFiscal notaFiscal;

    @ManyToOne
    private Produto produto;
    
    @Column(name = "valor_unitario")
    private BigDecimal valorUnitario;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Formula("valor_unitario * quantidade")
    private BigDecimal valorTotal;
}