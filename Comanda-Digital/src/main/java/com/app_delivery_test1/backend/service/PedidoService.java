package com.app_delivery_test1.backend.service;

import com.app_delivery_test1.backend.model.Pedido;
import com.app_delivery_test1.backend.model.StatusPedido;
import com.app_delivery_test1.backend.model.ItemPedido; // Importando a classe ItemPedido
import com.app_delivery_test1.backend.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido criarPedido(Pedido pedido) {
        pedido.setStatus(StatusPedido.A_PREPARAR);
        // Assumindo que o pedido contém uma lista de itens que precisam ser associados
        for (ItemPedido item : pedido.getItens()) {
            item.setPedido(pedido); // Define o pedido para cada item
        }
        return pedidoRepository.save(pedido); // Salva o pedido, incluindo os itens
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll(); // Retorna todos os pedidos
    }

    public Pedido atualizarStatus(Long id, StatusPedido novoStatus) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow();
        pedido.setStatus(novoStatus);
        return pedidoRepository.save(pedido); // Atualiza o status do pedido
    }
}
