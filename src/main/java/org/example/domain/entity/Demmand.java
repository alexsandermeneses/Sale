package org.example.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "demmand")
public class Demmand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "data_demand")
    private LocalDate dataDemmand;

    @Column(name = "total_demand", precision = 20, scale = 2)
    private BigDecimal totalDemmand;

    @OneToMany(mappedBy = "demmand")
    private List<ItemOrder> itemOrders;
}