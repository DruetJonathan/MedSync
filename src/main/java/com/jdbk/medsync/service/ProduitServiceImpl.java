package com.jdbk.medsync.service;

import com.jdbk.medsync.exception.AlreadyExistException;
import com.jdbk.medsync.exception.InvalidQuantityException;
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
    public Long addProduit(Produit produit) {
        produit.setId(null);
        if (produitRepository.existsByLibele(produit.getLibele())) {
            throw new AlreadyExistException("Product with libele already exist");
        }
        produit = produitRepository.save(produit);
        return produit.getId();
    }

    @Override
    public Produit updateProduit(long id, Produit produit) {
        if (produit.getQuantite() < 0) {
            throw new InvalidQuantityException("nouvelleQuantite should be positive or 0");
        }
        if (produit == null && !produitRepository.existsById(id)){
            throw new NotFoundException("Product not found");
        }
        produit.setId(id);
        return produitRepository.save(produit);
    }

    @Override
    public Produit removeProduit(Long produitId) {
//        if (produitId == null){
//            throw new NotFoundException("Product not found");
//        }
        Produit produit = produitRepository.findById(produitId).orElseThrow(() -> new NotFoundException("Product not found"));
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
    public Produit updateStockProduit(Long produitId, Long nouvelleQuantite) {
        if (nouvelleQuantite < 0) {
            throw new InvalidQuantityException("nouvelleQuantite should be positive or 0");
        }
        Produit produit = getProduitById(produitId);
        produit.setQuantite( nouvelleQuantite );
        return produitRepository.save(produit);
    }


}
