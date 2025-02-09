package com.teste.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.teste.model.Fornecedor;
import com.teste.model.ItemNotaFiscal;
import com.teste.model.NotaFiscal;
import com.teste.model.Produto;
import com.teste.model.dto.ItemNotaFiscalDTO;
import com.teste.model.dto.NotaFiscalDTO;
import com.teste.repository.FornecedorRepository;
import com.teste.repository.NotaFiscalRepository;
import com.teste.repository.ProdutoRepository;
import com.teste.service.NotaFiscalService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class NotaFiscalServiceImpl implements NotaFiscalService {

    @Inject
    NotaFiscalRepository repository;

    @Inject
    FornecedorRepository fornecedorRepository;

    @Inject
    ProdutoRepository produtoRepository;

    @Override
    public NotaFiscalDTO buscarPorNumero(long numero) {
        return toDTO(repository.findByNumeroOptional(numero)
                .orElseThrow(() -> new NotFoundException("Nota fiscal não encontrada")));
    }

    @Override
    public List<NotaFiscalDTO> listarTodas() {
        return repository.listAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public NotaFiscalDTO salvar(NotaFiscalDTO dto) {
        NotaFiscal nota = toNotaFiscal(dto);
        repository.persist(nota);
        return toDTO(nota);
    }

@Transactional
@Override
public NotaFiscalDTO atualizar(NotaFiscalDTO dto) {
    NotaFiscal existente = repository.findByNumeroOptional(dto.getNumero())
            .orElseThrow(() -> new NotFoundException("Nota fiscal não encontrada"));

    // Limpa os itens existentes
    existente.getItens().clear();
    
    // Atualiza os dados da nota
    existente.setDataEmissao(dto.getDataEmissao());
    
    // Busca o fornecedor usando Panache
    Fornecedor fornecedor = fornecedorRepository.findById(dto.getFornecedorId());
    if (fornecedor == null) {
        throw new NotFoundException("Fornecedor não encontrado");
    }
    existente.setFornecedor(fornecedor);
    existente.setValorTotal(dto.getValorTotal());
    
    // Adiciona os novos itens
    if (dto.getItens() != null) {
        dto.getItens().forEach(itemDTO -> {
            ItemNotaFiscal item = new ItemNotaFiscal();
            
            // Busca o produto
            Produto produto = produtoRepository.findById(itemDTO.getProdutoId());
            if (produto == null) {
                throw new NotFoundException("Produto não encontrado");
            }
            item.setProduto(produto);
            
            item.setQuantidade(itemDTO.getQuantidade());
            item.setValorUnitario(itemDTO.getValorUnitario());
            item.setValorTotal(itemDTO.getValorTotal());
            item.setNotaFiscal(existente);
            existente.getItens().add(item);
        });
    }

    repository.persist(existente);
    return toDTO(existente);
}

    @Transactional
    @Override
    public void deletar(long numero) {
        NotaFiscal nota = repository.findByNumeroOptional(numero)
                .orElseThrow(() -> new NotFoundException("Nota fiscal não encontrada"));
        repository.delete(nota);
    }

    private void atualizarNota(NotaFiscal existente, NotaFiscalDTO dto) {
        existente.setDataEmissao(dto.getDataEmissao());
        existente.setFornecedor(fornecedorRepository.findById(dto.getFornecedorId()));
        existente.setValorTotal(dto.getValorTotal());
        existente.setNumero(dto.getNumero());

        // Limpar itens existentes
        existente.getItens().clear();

        // Adicionar novos itens
        existente.setItens(
                dto.getItens().stream()
                        .map(itemDto -> {
                            ItemNotaFiscal item = new ItemNotaFiscal();
                            item.setId(itemDto.getId());
                            item.setProduto(produtoRepository.findById(itemDto.getProdutoId()));
                            item.setValorUnitario(itemDto.getValorUnitario());
                            item.setQuantidade(itemDto.getQuantidade());
                            item.setValorTotal(itemDto.getValorTotal());
                            item.setNotaFiscal(existente);
                            return item;
                        })
                        .collect(Collectors.toList()));
    }

    private NotaFiscalDTO toDTO(NotaFiscal nota) {
        return new NotaFiscalDTO(
                nota.getId(),
                nota.getNumero(),
                nota.getDataEmissao(),
                nota.getFornecedor().getCodigo(),
                nota.getValorTotal(),
                nota.getItens().stream()
                        .map(this::toItemDTO)
                        .collect(Collectors.toList()));
    }

    private NotaFiscal toNotaFiscal(NotaFiscalDTO dto) {
        NotaFiscal nota = new NotaFiscal();
        nota.setNumero(dto.getNumero());
        nota.setDataEmissao(dto.getDataEmissao());
        nota.setFornecedor(fornecedorRepository.findById(dto.getFornecedorId()));
        nota.setValorTotal(dto.getValorTotal());

        nota.setItens(
                dto.getItens().stream()
                        .map(itemDto -> {
                            ItemNotaFiscal item = new ItemNotaFiscal();
                            item.setId(itemDto.getId());
                            item.setProduto(produtoRepository.findById(itemDto.getProdutoId()));
                            item.setValorUnitario(itemDto.getValorUnitario());
                            item.setQuantidade(itemDto.getQuantidade());
                            item.setNotaFiscal(nota);
                            return item;
                        })
                        .collect(Collectors.toList()));

        return nota;
    }

    private ItemNotaFiscalDTO toItemDTO(ItemNotaFiscal item) {
        return new ItemNotaFiscalDTO(
                item.getId(),
                item.getProduto().getCodigo(),
                item.getValorUnitario(),
                item.getQuantidade(),
                item.getValorTotal());
    }
}