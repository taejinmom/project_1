package com.project.product;

/**
 * packageName    : com.project.product
 * fileName       : ProductImage
 * author         : Administrator
 * date           : 2025-05-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-05-13        Administrator       최초 생성
 */
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_product_image")
public class ProductImage {

    @Id
    @Column(name = "image_id", length = 40)
    private String imageId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "image_url", columnDefinition = "TEXT", nullable = false)
    private String imageUrl;

    @Column(name = "is_thumbnail")
    private boolean isThumbnail = false;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
