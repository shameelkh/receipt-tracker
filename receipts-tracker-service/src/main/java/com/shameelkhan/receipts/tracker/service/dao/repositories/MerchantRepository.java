package com.shameelkhan.receipts.tracker.service.dao.repositories;

import com.shameelkhan.receipts.tracker.service.dao.entities.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {
}
