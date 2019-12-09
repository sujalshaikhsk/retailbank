package com.bank.retailbank.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.retailbank.dto.FundTransferRequestDto;
import com.bank.retailbank.dto.FundTransferResponseDto;
import com.bank.retailbank.service.TransactionService;

/**
 * @author Vasavi
 *
 */
@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/transactions")
public class TransactionController {

	/**
	 * The Constant log.
	 */
	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
	/**
	 * The transactionServiceImpl.
	 */
	@Autowired
	TransactionService transactionService;

	/**
	 * 
	 * @description this method is used to do fund transfer in the application
	 * @param fundTransferRequestDto the fundTransferRequestDto which contains
	 *                               fromAccount,toAccount,amount,transactionType
	 *                               and benefactorName
	 * @return fundTransferResponseDto
	 */
	@PostMapping("/fundTransfer")
	public ResponseEntity<FundTransferResponseDto> fundTransfer(
			@RequestBody FundTransferRequestDto fundTransferRequestDto) {
		logger.debug("In TransactionController:fundTransfer");
		FundTransferResponseDto fundTransferResponseDto = transactionService.fundTransfer(fundTransferRequestDto);
		return new ResponseEntity<>(fundTransferResponseDto, HttpStatus.OK);
	}

}
