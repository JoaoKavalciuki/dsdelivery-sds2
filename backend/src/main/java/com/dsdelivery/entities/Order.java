package com.dsdelivery.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.dsdelivery.entities.enums.OrderStatus;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private Double latitude;
    private Double longitude;
    private String address;
    private Instant moment;
    private OrderStatus status;

    @ManyToMany
    @JoinTable(name = "tb_order_product", 
    joinColumns = @JoinColumn(name = "order_id"), 
    inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products = new HashSet<>();

    public Order() {

    }

    public Order(Long id, Double latitude, Double longitude, String address, Instant moment, OrderStatus status) {
        this.setId(id);
        this.setLatitude(latitude);
        this.setLongitude(longitude);
        this.setAddress(address);
        this.setMoment(moment);
        this.setStatus(status);
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
    }
    
    public Set<Product> getProducts() {
        return this.products;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        
        if(object == null) {
            return false;
        }

        if(getClass() != object.getClass()) {
            return false;
        }

        Order other = (Order) object;

        if(id == null) {
            if(other.id != null) {
                return false;
            }
        } else if(!id.equals(other.id)) {
            return false;
        }

        return true;
    }
}
