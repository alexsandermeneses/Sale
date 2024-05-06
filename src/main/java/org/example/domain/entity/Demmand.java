package org.example.domain.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getDataDemmand() {
        return dataDemmand;
    }

    public void setDataDemmand(LocalDate dataDemmand) {
        this.dataDemmand = dataDemmand;
    }

    public BigDecimal getTotalDemmand() {
        return totalDemmand;
    }

    public void setTotalDemmand(BigDecimal totalDemmand) {
        this.totalDemmand = totalDemmand;
    }

    public List<ItemOrder> getItemOrders() {
        return itemOrders;
    }

    public void setItemOrders(List<ItemOrder> itemOrders) {
        this.itemOrders = itemOrders;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", dataOrder=" + dataDemmand +
                ", totalOrder=" + totalDemmand +
                '}';
    }
}
