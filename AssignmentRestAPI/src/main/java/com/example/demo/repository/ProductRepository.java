package com.example.demo.repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	// for pagination
	//Page<Product> findByNameStartingWith(@Param("namePrefix") String namePrefix, Pageable pageable);
	
//	@Query("SELECT p FROM Product p WHERE p.name LIKE %:searchPattern% AND p.category = :searchPattern")
//	Page<Product> searchProducts(@Param("name") String searchPattern, @Param("category") String category, Pageable pageable);

	
	
//	@Query("SELECT p FROM Product p JOIN p.categories c WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :pattern, '%')) AND (LOWER(c.name) LIKE LOWER(CONCAT('%', :pattern, '%')) OR p.attributes[CAST(:attributeKey AS string)] LIKE LOWER(CONCAT('%', :pattern, '%'))) AND LOWER(c.name) LIKE LOWER(CONCAT('%', :category, '%'))")
//    Page<Product> getList(
//            @Param("pattern") String pattern,
//            @Param("attributeKey") String attributeKey,
//            @Param("category") String category,
//            Pageable pageable
//    );
	
	

	 @Query("SELECT p FROM Product p WHERE " +
	           "(:name IS NULL OR p.name LIKE %:name%) AND " +
	           "(:category IS NULL OR :category IN elements(p.categories)) AND " +
	           "(:attributeKey IS NULL OR :attributeValue IS NULL OR " +
	           "EXISTS (SELECT 1 FROM p.attributes a WHERE KEY(a) = :attributeKey AND VALUE(a) = :attributeValue))")
	    Page<Product> searchProducts(@Param("name") String name, 
	                                 @Param("category") String category, 
	                                 @Param("attributeKey") String attributeKey, 
	                                 @Param("attributeValue") String attributeValue, 
	                                 Pageable pageable);
	
}
