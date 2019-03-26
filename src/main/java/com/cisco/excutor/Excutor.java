package com.cisco.excutor;

import com.cisco.controller.HomeController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;

import javax.annotation.Resource;
import java.io.BufferedReader;

import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;
import java.util.stream.Collectors;

public class Excutor {
    private static final Logger LOG = LoggerFactory.getLogger(Excutor.class);

    public static Map<String, Object> execute(Map<String, Object> params) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String[] cmds = {"python", getFilePath(), objectMapper.writeValueAsString(params) };
        Process process = Runtime.getRuntime().exec(String.join(" ", cmds));
        process.waitFor();

        String error = new BufferedReader(new InputStreamReader(process.getErrorStream()))
                .lines().collect(Collectors.joining("\n"));
        String result = new BufferedReader(new InputStreamReader(process.getInputStream()))
                .lines().collect(Collectors.joining("\n"));

        LOG.info("execute: {} result: {} erro:{}", String.join(" ", cmds), result, error);
        return new ObjectMapper().readValue(result, Map.class);
    }

    @Cacheable
    private static String getFilePath() {
        URL resource = Excutor.class.getClassLoader().getResource("application.properties");
        File file = new File(resource.getPath());
        return file.getParent() + "/script/excutor.py";
    }
}