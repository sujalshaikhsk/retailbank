package com.bank.retailbank.service;


import com.bank.retailbank.dto.FundTransferRequestDto;
import com.bank.retailbank.dto.FundTransferResponseDto;

/**
 * The interface TransactionService.
 * 
 * @author Vasavi
 *
 */
public interface TransactionService {
	/**
	 * 
	 * @description this method is used to do fund transfer in the application.
	 * @param fundTransferRequestDTO the fundTransferRequestDTO which contains
	 *                               fromAccount,toAccount,amount,transactionType
	 *                               and benefactorName.
	 * @return fundTransferResponseDto
	 */
	public FundTransferResponseDto fundTransfer(FundTransferRequestDto fundTransferRequestDTO);

}
