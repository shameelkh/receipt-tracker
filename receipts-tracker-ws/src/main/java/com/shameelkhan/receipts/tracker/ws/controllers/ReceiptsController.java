package com.shameelkhan.receipts.tracker.ws.controllers;

import com.shameelkhan.receipts.tracker.domain.ReceiptDto;
import com.shameelkhan.receipts.tracker.service.ReceiptsService;
import com.shameelkhan.receipts.tracker.service.seach.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class ReceiptsController {

    private ReceiptsService receiptsService;

    @Autowired
    public ReceiptsController(ReceiptsService receiptsService) {
        this.receiptsService = receiptsService;
    }

    @RequestMapping(value = "/users/{userId}/receipts", method = RequestMethod.GET)
    public ResponseEntity<List<ReceiptDto>> getReceiptsForUser(@PathVariable Long userId,
                                                               @RequestParam(required = false) String query) {
        List<ReceiptDto> receipts = new ArrayList<>();

        if(query == null) {
            receipts = receiptsService.getReceiptsForUser(userId);
        }
        else {
            List<SearchCriteria> searchCriteria = new ArrayList<>();
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\"([^\"]+)\")");
            Matcher matcher = pattern.matcher(query + ",");

            while (matcher.find()) {
                searchCriteria.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3).replaceAll("^\"|\"$", "")));
            }

            receipts = receiptsService.searchReceiptsForUser(userId, searchCriteria);
        }

        return new ResponseEntity<>(receipts, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{userId}/receipts", method = RequestMethod.POST)
    public ResponseEntity<ReceiptDto> createReceiptForUser(@RequestBody ReceiptDto receiptDto) {
        ReceiptDto savedReceipt = receiptsService.saveReceipt(receiptDto);

        return new ResponseEntity<>(savedReceipt, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/users/{userId}/receipts/{receiptId}", method = RequestMethod.PUT)
    public ResponseEntity<ReceiptDto> updateReceiptForUser(@RequestBody ReceiptDto receiptDto) {
        ReceiptDto updatedReceipt = receiptsService.saveReceipt(receiptDto);
        return new ResponseEntity<>(updatedReceipt, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{userId}/receipts/{receiptId}/date", method = RequestMethod.PATCH)
    public ResponseEntity patchReceiptDate(@PathVariable Long receiptId,
                                           @RequestBody LocalDate newDate) {

        receiptsService.patchReceiptDate(receiptId, newDate);
        return new ResponseEntity<ReceiptDto>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/users/{userId}/receipts/delete", method = RequestMethod.POST)
    public ResponseEntity batchDeleteReceiptsForUser(@RequestBody List<Long> receiptIds) {
        receiptsService.batchDeleteReceipts(receiptIds);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
