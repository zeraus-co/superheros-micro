package com.w2m.zeraus.supher.web.mapper;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Qualifier;

import com.w2m.zeraus.supher.service.model.SuperheroVO;
import com.w2m.zeraus.supher.web.model.SuperheroTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, imports = StandardCharsets.class)
@Qualifier("superherosControllerMapper")
public interface SuperherosControllerMapper {

	SuperheroVO transformToVO(SuperheroTO superhero);

	SuperheroTO transformToTO(SuperheroVO superhero);

	List<SuperheroTO> transformToTO(List<SuperheroVO> superhero);

}
