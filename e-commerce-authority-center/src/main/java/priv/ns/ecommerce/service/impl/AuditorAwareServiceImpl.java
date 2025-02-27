package priv.ns.ecommerce.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import priv.ns.ecommerce.service.AuditorAwareService;

@Service
public class AuditorAwareServiceImpl implements AuditorAwareService {

    @Override
    public String getCurrentAuditor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null || !auth.isAuthenticated()) {
            return "anonymous";
        }
        return auth.getName();
    }
}
