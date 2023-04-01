package com.payment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payment.model.Merchant;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long>{

	List<Merchant> findByIsActiveTrue();
}
