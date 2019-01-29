package com.reptile.service;

import java.util.Map;

public interface ParperService {
    Map<String,Object> getData(int rows,int page,String type) throws Exception;

}
