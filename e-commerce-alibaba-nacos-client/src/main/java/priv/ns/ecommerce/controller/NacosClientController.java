package priv.ns.ecommerce.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import priv.ns.ecommerce.service.NacosClientService;

import java.util.List;

/**
 * nacos client controller
 */
@Slf4j
@RestController
@RequestMapping("/nacos-client")
public class NacosClientController {
    private NacosClientService nacosClientService;
    public NacosClientController(NacosClientService nacosClientService) {
        this.nacosClientService = nacosClientService;
    }

    /**
     * Get service instance by serviceId
     * @param serviceId
     * @return
     */
    @GetMapping("/service-instance")
    public List<ServiceInstance> getServiceInstances(
            @RequestParam(defaultValue = "e-commerce-nacos-client") String serviceId) {
        log.info("come in log nacos client info[{}]",serviceId);
        return  nacosClientService.getInstances(serviceId);
    }
}
