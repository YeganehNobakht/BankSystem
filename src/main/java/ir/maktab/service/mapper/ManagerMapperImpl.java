package ir.maktab.service.mapper;

import ir.maktab.data.domains.Manager;
import ir.maktab.dto.ManagerDto;
import org.springframework.stereotype.Component;

@Component
public class ManagerMapperImpl implements ManagerMapper{

    @Override
    public Manager toManager(ManagerDto managerDto) {
        return new Manager().setUsername(managerDto.getUsername()).setPassword(managerDto.getPassword());
    }

    @Override
    public ManagerDto toManagerDto(Manager manager) {
        return new ManagerDto().setPassword(manager.getPassword()).setUsername(manager.getUsername());
    }
}
