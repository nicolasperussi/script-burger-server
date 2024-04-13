package com.nicolasperussi.scriptburger.controllers;

import java.time.Instant;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicolasperussi.scriptburger.domain.Client;
import com.nicolasperussi.scriptburger.domain.Order;
import com.nicolasperussi.scriptburger.domain.OrderItem;
import com.nicolasperussi.scriptburger.domain.Product;
import com.nicolasperussi.scriptburger.domain.dtos.OrderByUserDTO;
import com.nicolasperussi.scriptburger.domain.dtos.OrderDTO;
import com.nicolasperussi.scriptburger.domain.dtos.OrderItemDTO;
import com.nicolasperussi.scriptburger.domain.enums.OrderStatus;
import com.nicolasperussi.scriptburger.repositories.OrderItemRepository;
import com.nicolasperussi.scriptburger.services.OrderService;
import com.nicolasperussi.scriptburger.services.ProductService;
import com.nicolasperussi.scriptburger.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/orders", produces = { "application/json" })
@Tag(name = "Orders")
public class OrderController {
    @Autowired
    private OrderService service;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @GetMapping
    @Operation(summary = "Fetch all orders", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders were fetched."),
            @ApiResponse(responseCode = "204", description = "No orders were found.", content = @Content)
    })
    public ResponseEntity<List<Order>> findAll() {
        List<Order> list = service.findAll();
        if (list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findById(@NonNull @PathVariable Long id) {
        Order order = service.findById(id);

        return ResponseEntity.ok().body(order);
    }

    @GetMapping(value = "/user/{clientId}")
    @Operation(summary = "Fetch orders by user", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders were fetched based on provided user id."),
            @ApiResponse(responseCode = "204", description = "No orders were found.", content = @Content)
    })
    public ResponseEntity<List<OrderByUserDTO>> findByClientId(@NonNull @PathVariable Long clientId) {
        List<OrderByUserDTO> orders = service.findByClientId(clientId);

        if (orders.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return ResponseEntity.ok().body(orders);
    }

    @PostMapping
    @Operation(summary = "Create a new order", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order successfully created."),
    })
    public ResponseEntity<OrderByUserDTO> create(@Valid @RequestBody OrderDTO order) {
        Order newOrder = new Order();
        newOrder.setMoment(Instant.now());
        newOrder.setStatus(OrderStatus.WAITING);
        Client client = (Client) userService.findById(order.getUserId());
        newOrder.setClient(client);

        newOrder.setDeliveryAddress(order.getAddress());

        service.create(newOrder);

        for (OrderItemDTO item : order.getItems()) {
            Product product = productService.findById(item.getProductId());
            OrderItem orderItem = new OrderItem(newOrder, product, item.getQuantity());
            newOrder.addItem(orderItem);
            orderItemRepository.save(orderItem);
        }

        simpMessagingTemplate.convertAndSend("/topic/orders", newOrder);

        return ResponseEntity.created(null).body(service.convertToOrderByUserDTO(newOrder));
    }

    @PatchMapping(value = "/{orderId}")
    @Operation(summary = "Advance order step", method = "PATCH")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order advanced to next step.", content = @Content),
    })
    public ResponseEntity<Order> nextOrderStep(@PathVariable Long orderId) {
        Order order = service.nextOrderStatus(orderId);
        simpMessagingTemplate.convertAndSend("/topic/orders/" + order.getClient().getId(), order);
        return ResponseEntity.ok().body(order);
    }

    @PatchMapping(value = "/cancel/{orderId}")
    @Operation(summary = "Cancel order", method = "PATCH")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order canceled successfully.", content = @Content),
    })
    public ResponseEntity<Order> cancel(@PathVariable Long orderId) {
        Order order = service.cancelOrder(orderId);
        simpMessagingTemplate.convertAndSend("/topic/orders/" + order.getClient().getId(), order);
        return ResponseEntity.ok().body(order);
    }

    @PatchMapping(value = "/{orderId}/courier/{courierId}")
    @Operation(summary = "Assign a courier to order", method = "PATCH")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Courier assigned successfully.", content = @Content),
    })
    public ResponseEntity<Order> assignCourier(@PathVariable Long orderId, @PathVariable Long courierId) {
        return ResponseEntity.ok().body(service.assignCourier(orderId, courierId));
    }
}
