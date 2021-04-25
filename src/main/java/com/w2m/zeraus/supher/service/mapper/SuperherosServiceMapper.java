package com.w2m.zeraus.supher.service.mapper;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.w2m.zeraus.supher.entity.Superhero;
import com.w2m.zeraus.supher.service.model.SuperheroVO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, imports = StandardCharsets.class)
@Qualifier("superherosServiceMapper")
public interface SuperherosServiceMapper {

	SuperheroVO transformToVO(Superhero superhero);

	List<SuperheroVO> transformToVO(List<Superhero> superhero);

	Superhero transformToEntity(SuperheroVO superhero);

	default Page<SuperheroVO> transformPageToVO(Page<Superhero> superheros) {
		return new PageImpl<>(transformToVO(superheros.getContent()), superheros.getPageable(),
				superheros.getTotalElements());
	}

}
