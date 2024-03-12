package com.kit.dormitory.fee;

import com.kit.dormitory.book.DormName;
import com.kit.dormitory.member.Member;

public class NewFeePolicy implements FeePolicy{
    private int fee = 0;
    private int addFee = 10000;
    @Override
    public int fee(Member member, DormName dormName) {
        if(dormName==DormName.PUREUM){
            fee = 1000 + addFee;
        }else if(dormName==DormName.OREUM){
            fee = 2000 + addFee;
        }else{
            fee = 500 + addFee;
        }
        return fee;
    }
}
