package io.pivotal.pal.tracker;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {


    public String cfInstanceIndex;
    public String cfInstanceAddr;
    public String memoryLimit;
    public String port;
    public String githubAccessToken;



    public EnvController(@Value("${port:NOT SET}") String port,
                         @Value("${memory.limit:NOT SET}") String memoryLimit,
                         @Value("${cf.instance.index:NOT SET}") String cfInstanceIndex,
                         @Value("${cf.instance.addr:NOT SET}") String cfInstanceAddr,
                         @Value("${githubAccessToken:NOT SET}") String githubAccessToken)
    {
        this.cfInstanceIndex = cfInstanceIndex;
        this.cfInstanceAddr = cfInstanceAddr;
        this.memoryLimit = memoryLimit;
        this.port = port;
        this.githubAccessToken = githubAccessToken;

    }

    @GetMapping("/env")
    public Map<String, String> getEnv() {
        HashMap<String, String> envs = new HashMap<>();
        envs.put("CF_INSTANCE_INDEX", cfInstanceIndex);
        envs.put("CF_INSTANCE_ADDR", cfInstanceAddr);
        envs.put("MEMORY_LIMIT", memoryLimit);
        envs.put("PORT", port);
        envs.put("GITHUB_ACCESS_TOKEN", githubAccessToken);

        return envs;
    }
}
