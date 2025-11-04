package com.senac.aula_api.presentation;

import com.senac.aula_api.domain.entities.Pedido;
import com.senac.aula_api.domain.entities.Pagamento;
import com.senac.aula_api.domain.entities.Produto;
import com.senac.aula_api.domain.repository.PedidoRepository;
import com.senac.aula_api.domain.repository.PagamentoRepository;
import com.senac.aula_api.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/vendas")
    public Double totalVendas() {
        return pagamentoRepository.findAll().stream()
            .filter(p -> "aprovado".equalsIgnoreCase(p.getStatus()))
            .mapToDouble(Pagamento::getValor)
            .sum();
    }

    @GetMapping("/produtos-mais-vendidos")
    public List<String> produtosMaisVendidos() {
        Map<String, Integer> contagem = new HashMap<>();
        for (Pedido pedido : pedidoRepository.findAll()) {
            if (pedido.getProdutos() != null) {
                for (Produto produto : pedido.getProdutos()) {
                    contagem.put(produto.getNome(), contagem.getOrDefault(produto.getNome(), 0) + 1);
                }
            }
        }
        return contagem.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(5)
            .map(Map.Entry::getKey)
            .toList();
    }
}
