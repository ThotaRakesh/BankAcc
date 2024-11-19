package com.bankapp.dao;

import com.bankapp.models.Interest;
import java.util.List;

public interface InterestDAO {
    void addInterest(Interest interest);
    List<Interest> getInterestByAccount(int accountNumber);
}
