package com.jdbk.medsync.service;

import com.jdbk.medsync.exception.NotFoundException;
import com.jdbk.medsync.model.entity.Produit;
import com.jdbk.medsync.repository.ProduitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepository produitRepository;

    public ProduitServiceImpl(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    @Override
    public long addProduit(Produit produit) {
        produit = produitRepository.save(produit);
        return produit.getId();
    }

    @Override
    public Produit updateProduit(long id, Produit produit) {
        produit.setId(id);
        return produitRepository.save(produit);
    }

    @Override
    public Produit removeProduit(Long produitId) {
        Produit produit = getProduitById(produitId);
        produitRepository.delete(produit);
        return produit;
    }

    @Override
    public Produit getProduitById(Long produitId) {
        return produitRepository.findById(produitId).orElseThrow(() -> new NotFoundException("Product not found"));
    }

    @Override
    public List<Produit> getAll() {
        return produitRepository.findAll().stream()
                .toList();
    }

    @Override
    public Produit updateStockProduit(Long produitId, Long quantiteReduite) {
        if (quantiteReduite < 0) {
            throw new IllegalArgumentException("quantiteReduite should be positive or 0");
        }

        Produit produit = getProduitById(produitId);
        produit.setQuantite( quantiteReduite );
        return produitRepository.save(produit);
    }


}
