package com.dsdelivery.dto;

import java.io.Serializable;

import com.dsdelivery.entities.Product;

public class ProductDTO implements Serializable{
    private static final long serialVersionUID = 1L;
    
	private Long id;
    private String name;
    private String description;
    private String imageUri;
    private Double price;
    
    public ProductDTO() {
        
    }
    
    public ProductDTO(Long id, String name, String description, String imageUri, Double price) {
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
        this.setImageUri(imageUri);
        this.setPrice(price);
    }
    
    public ProductDTO(Product entity) {
        setId(entity.getId());
        setName(entity.getName());
        setDescription(entity.getDescription());
        setImageUri(entity.getImageUri());
        setPrice(entity.getPrice());
    }

    public Long getId() {
        return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUri() {
		return imageUri;
	}

	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}    
}
