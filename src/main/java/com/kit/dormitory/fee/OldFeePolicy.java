package com.kit.dormitory.fee;

import com.kit.dormitory.book.DormName;
import com.kit.dormitory.member.Member;

public class OldFeePolicy implements FeePolicy{
    private int fee;
    @Override
    public int fee(Member member, DormName dormName) {
        if(dormName==DormName.PUREUM){
            fee =  1000;
        }else if(dormName==DormName.OREUM){
            fee =  2000;
        }else{
            fee = 500;
        }
        return fee;
    }
}
