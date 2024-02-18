package com.thoughtclan.TodoPro;

import com.thoughtclan.TodoPro.TodoDto.utils.PatchMapper;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodoProApplication {

	@Bean
	public ModelMapper modelMapper()
	{
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration ().setMatchingStrategy ( MatchingStrategies.STRICT );
		return modelMapper;
	}

	@Bean
	public PatchMapper patchMapper()
	{
		PatchMapper patchMapper = new PatchMapper();
		patchMapper.getConfiguration ().setMatchingStrategy ( MatchingStrategies.STRICT ).setPropertyCondition(Conditions.isNotNull());
		return patchMapper;
	}

	public static void main(String[] args) {
		SpringApplication.run(TodoProApplication.class, args);
	}

}
