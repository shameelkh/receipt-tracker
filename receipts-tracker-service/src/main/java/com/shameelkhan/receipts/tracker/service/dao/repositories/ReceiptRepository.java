package com.shameelkhan.receipts.tracker.service.dao.repositories;

import com.shameelkhan.receipts.tracker.service.dao.entities.Receipt;
import com.shameelkhan.receipts.tracker.service.dao.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long>, JpaSpecificationExecutor<Receipt> {

    List<Receipt> findByUser(User user);
}
