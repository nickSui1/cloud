package priv.ns.ecommerce.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class NacosClientService {
    private DiscoveryClient discoveryClient;

    public NacosClientService(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    /**
     * Print Nacos Client information to log
     * @param serviceId ClientId
     * @return Service Instance List
     */
    public List<ServiceInstance> getInstances(String serviceId) {
        log.info("request nacos client to get service instance info[{}]",serviceId);
        return discoveryClient.getInstances(serviceId);
    }
}
