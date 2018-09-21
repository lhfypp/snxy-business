package com.snxy.business.service;

import com.snxy.business.domain.IdentityType;

import java.util.List;

public interface IdentityTypeService {
    List<IdentityType> listAll(boolean excludeDeleted);
}
