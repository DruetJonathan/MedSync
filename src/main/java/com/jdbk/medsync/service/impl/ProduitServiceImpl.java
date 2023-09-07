package com.jdbk.medsync.service.impl;

import com.jdbk.medsync.exception.AlreadyExistException;
import com.jdbk.medsync.exception.InvalidQuantityException;
import com.jdbk.medsync.exception.NotFoundException;
import com.jdbk.medsync.model.entity.Produit;
import com.jdbk.medsync.repository.ProduitRepository;
import com.jdbk.medsync.service.notImpl.ProduitService;
import org.springframework.stereotype.Service;

import java.util.Collection;
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
            throw new AlreadyExistException(produit.getId(),ProduitServiceImpl.class.toString());
        }
        produit = produitRepository.save(produit);
        return produit.getId();
    }

    @Override
    public Produit updateProduit(long id, Produit produit) {
        if (produit.getQuantite() < 0) {
            throw new InvalidQuantityException(id);
        }
        if (produit == null && !produitRepository.existsById(id)){
            throw new NotFoundException(id,ProduitServiceImpl.class.toString());
        }
        produit.setId(id);
        return produitRepository.save(produit);
    }

    @Override
    public Produit removeProduit(Long produitId) {
//        if (produitId == null){
//            throw new NotFoundException("Product not found");
//        }
        Produit produit = getProduitById(produitId);
        produitRepository.delete(produit);
        return produit;
    }

    @Override
    public Produit getProduitById(Long produitId) {
        return produitRepository.findById(produitId).orElseThrow(() -> new NotFoundException(produitId,ProduitServiceImpl.class.toString()));
    }

    @Override
    public List<Produit> getAll() {
        return produitRepository.findAll().stream()
                .toList();
    }

    @Override
    public Produit updateStockProduit(Long produitId, Long nouvelleQuantite) {
        if (nouvelleQuantite < 0) {
            throw new InvalidQuantityException(produitId);
        }
        Produit produit = getProduitById(produitId);
        produit.setQuantite( nouvelleQuantite );
        return produitRepository.save(produit);
    }

    @Override
    public List<Produit> getAllById(Collection<Long> produitIds) {
        return produitRepository.findAllById(produitIds);
    }


}
