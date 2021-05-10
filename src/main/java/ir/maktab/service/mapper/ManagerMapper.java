package ir.maktab.service.mapper;

import ir.maktab.data.domains.Manager;
import ir.maktab.dto.ManagerDto;

public interface ManagerMapper {
    public Manager toManager(ManagerDto managerDto);
    public ManagerDto toManagerDto(Manager manager);

}
