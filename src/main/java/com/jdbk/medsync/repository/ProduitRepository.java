package com.jdbk.medsync.repository;

import com.jdbk.medsync.model.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitRepository extends JpaRepository<Produit,Long> {

    boolean existsByLibele(String libele);
}
