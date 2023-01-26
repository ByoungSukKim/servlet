package com.TestNice.servlet.repository;

import com.TestNice.servlet.entity.BssStation;

import java.util.List;
import java.util.Optional;

public interface StationRepository {

    List<Optional<BssStation>> ListBSS ();
    BssStation DetailBSS (String id);

}
