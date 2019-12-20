package com.yfl.service;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HelloWorldService {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public HelloWorldService(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String,Object>> list(){
        String sql = "SELECT * FROM project";
        return jdbcTemplate.queryForList(sql, Maps.newHashMap());
    }
}
