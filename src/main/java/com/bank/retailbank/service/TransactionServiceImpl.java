package com.bank.retailbank.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.retailbank.dto.FundTransferRequestDto;
import com.bank.retailbank.dto.FundTransferResponseDto;
import com.bank.retailbank.entity.Account;
import com.bank.retailbank.entity.Transaction;
import com.bank.retailbank.repository.AccountRepository;
import com.bank.retailbank.repository.TransactionRepository;
import com.hcl.retailbanking.exception.CommonException;
import com.hcl.retailbanking.exception.MessageCode;
import com.hcl.retailbanking.util.StringConstant;

/**
 * The class TransactionServiceImpl.
 * 
 * @author Vasavi
 *
 */
@Service
public class TransactionServiceImpl implements TransactionService {

	/**
	 * The Constant log.
	 */
	private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
	/**
	 * The TransactionRepository methods are autowired with this
	 * TransactionServiceImpl..
	 */
	@Autowired
	TransactionRepository transactionRepository;

	/**
	 * The AccountRepository methods are autowired with this TransactionServiceImpl
	 */
	@Autowired
	AccountRepository accountRepository;

	/**
	 * 
	 * @description this method is used to do fund transfer in the application
	 * @param fundTransferRequestDTO the fundTransferRequestDTO which contains
	 *                               fromAccount,toAccount,amount,transactionType
	 *                               and benefactorName
	 * @return fundTransferResponseDto
	 */
	@Override
	public FundTransferResponseDto fundTransfer(FundTransferRequestDto fundTransferRequestDTO) {
		logger.info("Inside fundTransfer method");
		Optional<Account> fromAccount = accountRepository.findById(fundTransferRequestDTO.getFromAccount());
		Optional<Account> toAccount = accountRepository.findById(fundTransferRequestDTO.getToAccount());
		// checking from source account to source account we can not do fund
		// transactions
		if (fundTransferRequestDTO.getFromAccount().equals(fundTransferRequestDTO.getToAccount())) {
			throw new CommonException(MessageCode.SAME_ACCOUNT_INVALID);
		}

		// checking from source account to destination account we can not send negative
		// and zero balance
		if (fundTransferRequestDTO.getAmount() <= 0) {
			throw new CommonException(MessageCode.AMMOUNT_INVALID);
		}
		// checking source and destination accounts existed or not
		if (!fromAccount.isPresent() || !toAccount.isPresent()) {
			throw new CommonException(MessageCode.ACCOUNT_INVALID);
		}

		// source account should be maintain with the minimal balance
		if (!fromAccountBalanceVerification(fromAccount.get(), fundTransferRequestDTO)) {
			throw new CommonException(MessageCode.insufficentFunds(fromAccount.get().getBalance()));
		}

		fromAccount.get().setBalance(fromAccount.get().getBalance() - fundTransferRequestDTO.getAmount());
		toAccount.get().setBalance(toAccount.get().getBalance() + fundTransferRequestDTO.getAmount());

		Transaction transaction1 = getTransactionObject(fundTransferRequestDTO, StringConstant.DEBIT);
		Long fromAccountNumber = fundTransferRequestDTO.getFromAccount();
		Long toAccountNumber = fundTransferRequestDTO.getToAccount();

		fundTransferRequestDTO.setFromAccount(toAccountNumber);
		fundTransferRequestDTO.setToAccount(fromAccountNumber);

		Transaction transaction2 = getTransactionObject(fundTransferRequestDTO, StringConstant.CREDIT);
		transactionRepository.save(transaction1);
		transactionRepository.save(transaction2);
		accountRepository.save(fromAccount.get());
		accountRepository.save(toAccount.get());
		return new FundTransferResponseDto("Successfully Transferred", fundTransferRequestDTO.getFromAccount(),
				fundTransferRequestDTO.getToAccount(), fundTransferRequestDTO.getAmount());
	}

	private Transaction getTransactionObject(FundTransferRequestDto fundTransferRequestDTO, String debit) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean fromAccountBalanceVerification(Account account, FundTransferRequestDto fundTransferRequestDTO) {
		// TODO Auto-generated method stub
		return false;
	}

}
