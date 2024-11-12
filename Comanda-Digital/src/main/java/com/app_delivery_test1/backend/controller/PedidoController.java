package com.app_delivery_test1.backend.controller;

import com.app_delivery_test1.backend.model.ItemPedido; // Importando a classe ItemPedido
import com.app_delivery_test1.backend.model.Pedido;
import com.app_delivery_test1.backend.model.Prato; // Certifique-se de importar a classe Prato
import com.app_delivery_test1.backend.model.StatusPedido;
import com.app_delivery_test1.backend.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
        // Vamos criar uma lista de itens a partir dos pratos do pedido
        List<ItemPedido> itens = new ArrayList<>();

        for (Prato prato : pedido.getPratos()) { // Supondo que você tenha uma lista de Prato em Pedido
            ItemPedido item = new ItemPedido();
            item.setNome(prato.getNome());
            item.setQuantidade(1); // Defina a quantidade como desejado
            item.setPedido(pedido); // Define o pedido para cada item
            itens.add(item);
        }

        // Agora definimos os itens no pedido
        pedido.setItens(itens);

        // Criaremos o pedido com os itens associados
        Pedido novoPedido = pedidoService.criarPedido(pedido);
        return ResponseEntity.ok(novoPedido);
    }


    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos() {
        List<Pedido> pedidos = pedidoService.listarPedidos();
        return ResponseEntity.ok(pedidos);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Pedido> atualizarStatus(@PathVariable Long id, @RequestBody StatusPedido status) {
        Pedido pedidoAtualizado = pedidoService.atualizarStatus(id, status);
        return ResponseEntity.ok(pedidoAtualizado);
    }
}
