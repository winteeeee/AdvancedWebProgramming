package com.kit.dormitory.book;

import com.kit.dormitory.fee.FeePolicy;
import com.kit.dormitory.fee.NewFeePolicy;
import com.kit.dormitory.fee.OldFeePolicy;
import com.kit.dormitory.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookServiceImpl implements BookService{
    //private final FeePolicy feePolicy = new OldFeePolicy();
    private final FeePolicy feePolicy;

    @Autowired
    public BookServiceImpl(FeePolicy feePolicy) {
        this.feePolicy = feePolicy;
    }

    @Override
    public Book assignRoom(Member member, DormName dormName, String roomNumber) {
        int fee = feePolicy.fee(member,dormName);
        return new Book(member,dormName,fee);
    }
}
