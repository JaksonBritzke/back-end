package com.teste.service.impl;

import java.util.ArrayList;
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
import jakarta.ws.rs.BadRequestException;
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
        validarCamposUnicos(dto);
        NotaFiscal nota = toNotaFiscal(dto);
        repository.persist(nota);
        return toDTO(nota);
    }

    @Transactional
    @Override
    public void deletar(long numero) {
        NotaFiscal nota = repository.findByNumeroOptional(numero)
                .orElseThrow(() -> new NotFoundException("Nota fiscal não encontrada"));
        repository.delete(nota);
    }

    @Transactional
    @Override
    public NotaFiscalDTO atualizar(NotaFiscalDTO dto) {
        NotaFiscal existente = repository.findByNumeroOptional(dto.getNumero())
                .orElseThrow(() -> new NotFoundException("Nota fiscal não encontrada"));

        // Limpa todos os itens existentes
        existente.getItens().clear();
        repository.persist(existente);

        // Atualiza a nota com os novos dados
        atualizarNota(existente, dto);
        repository.persist(existente);
        return toDTO(existente);
    }

    private void atualizarNota(NotaFiscal existente, NotaFiscalDTO dto) {
        existente.setDataEmissao(dto.getDataEmissao());
        existente.setNumero(dto.getNumero());
        existente.setValorTotal(dto.getValorTotal());

        Fornecedor fornecedor = fornecedorRepository.findById(dto.getFornecedorId());
        if (fornecedor == null) {
            throw new NotFoundException("Fornecedor não encontrado");
        }
        existente.setFornecedor(fornecedor);

        if (dto.getItens() != null && !dto.getItens().isEmpty()) {
            List<ItemNotaFiscal> novosItens = new ArrayList<>();

            for (ItemNotaFiscalDTO itemDto : dto.getItens()) {
                ItemNotaFiscal item = new ItemNotaFiscal();

                Produto produto = produtoRepository.findById(itemDto.getProdutoId());
                if (produto == null) {
                    throw new NotFoundException("Produto não encontrado: " + itemDto.getProdutoId());
                }

                item.setProduto(produto);
                item.setValorUnitario(itemDto.getValorUnitario());
                item.setQuantidade(itemDto.getQuantidade());
                item.setValorTotal(itemDto.getValorTotal());
                item.setNotaFiscal(existente);

                novosItens.add(item);
            }

            existente.getItens().addAll(novosItens);
        }
    }

    private NotaFiscalDTO toDTO(NotaFiscal nota) {
        return new NotaFiscalDTO(
                nota.getId(),
                nota.getNumero(),
                nota.getDataEmissao(),
                nota.getFornecedor().getCodigo(),
                nota.getFornecedor().getRazaoSocial(),
                nota.getValorTotal(),
                nota.getItens().stream()
                        .map(this::toItemDTO)
                        .collect(Collectors.toList()));
    }

    private NotaFiscal toNotaFiscal(NotaFiscalDTO dto) {
        NotaFiscal nota = new NotaFiscal();
        nota.setNumero(dto.getNumero());
        nota.setDataEmissao(dto.getDataEmissao());

        Fornecedor fornecedor = fornecedorRepository.findById(dto.getFornecedorId());
        if (fornecedor == null) {
            throw new NotFoundException("Fornecedor não encontrado");
        }
        nota.setFornecedor(fornecedor);
        nota.setValorTotal(dto.getValorTotal());

        if (dto.getItens() != null && !dto.getItens().isEmpty()) {
            nota.setItens(
                    dto.getItens().stream()
                            .map(itemDto -> {
                                ItemNotaFiscal item = new ItemNotaFiscal();

                                Produto produto = produtoRepository.findById(itemDto.getProdutoId());
                                if (produto == null) {
                                    throw new NotFoundException("Produto não encontrado: " + itemDto.getProdutoId());
                                }

                                item.setProduto(produto);
                                item.setValorUnitario(itemDto.getValorUnitario());
                                item.setQuantidade(itemDto.getQuantidade());
                                item.setValorTotal(itemDto.getValorTotal());
                                item.setNotaFiscal(nota);
                                return item;
                            })
                            .collect(Collectors.toList()));
        }

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

    private void validarCamposUnicos(NotaFiscalDTO dto) {
        if (repository.count("numero", dto.getNumero()) > 0) {
            throw new BadRequestException("Esse número de nota já existe cadastrado");
        }
    }
}