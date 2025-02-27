package priv.ns.ecommerce.service.impl;

import org.springframework.data.domain.AuditorAware;
import priv.ns.ecommerce.service.AuditorAwareService;

import java.util.Optional;

/**
 * AuditorAwareImpl 实现 AuditorAware<String>，用于 JPA 自动填充 createdBy 和 lastModifiedBy。
 *
 * 设计模式：
 * 1. **依赖倒置原则（DIP）** - 只依赖 AuditorAwareService 接口，不直接依赖 authorities 模块。
 * 2. **策略模式（Strategy Pattern）** - 允许不同的 AuditorAwareService 实现，适配不同的用户管理方式。
 * 3. **依赖注入模式（DI）** - 通过 Spring 自动注入 AuditorAwareService，减少耦合。
 */
public class AuditorAwareImpl implements AuditorAware<String> {
    private final AuditorAwareService auditorAwareService;
    public AuditorAwareImpl(AuditorAwareService auditorAwareService) {
        this.auditorAwareService = auditorAwareService;
    }

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(auditorAwareService.getCurrentAuditor());
    }
}
