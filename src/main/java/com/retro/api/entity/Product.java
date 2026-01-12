package com.retro.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "products")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Product extends BaseEntity {
    @Column(name = "sku")
    private String sku;

    @Column(name = "slug")
    private String slug;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "summary")
    private String summary;

    @Column(name = "descriptions")
    private String descriptions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_state")
    private ProductState productState;

    @Column(name = "sale_price")
    private BigDecimal salePrice;

    @Column(name = "discount")
    private Integer discount;

    @Column(name = "review_count")
    private Integer reviewCount;

    @Column(name = "total_rating")
    private Integer totalRating;

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<ProductImage> productImages;

    @Column(name = "is_delete")
    private boolean isDelete;

    public double calAverageRating() {
        return (reviewCount == 0) ? 0 : totalRating.floatValue() / reviewCount;
    }

    public void addRating(int rating) {
        //condition

        this.totalRating += rating;
        this.reviewCount++;
    }
}
