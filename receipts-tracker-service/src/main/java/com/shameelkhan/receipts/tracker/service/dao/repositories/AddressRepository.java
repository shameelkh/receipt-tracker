package com.shameelkhan.receipts.tracker.service.dao.repositories;

import com.shameelkhan.receipts.tracker.service.dao.entities.MerchantAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<MerchantAddress, Long> {
}
