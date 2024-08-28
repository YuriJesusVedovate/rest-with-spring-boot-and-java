package br.com.yuri.mapper;

import br.com.yuri.data.vo.PersonVO;
import br.com.yuri.models.Person;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    private static final ModelMapper mapper = new ModelMapper();

    static {
        mapper.createTypeMap(Person.class, PersonVO.class)
                .addMapping(Person::getId, PersonVO::setKey);

        mapper.createTypeMap(PersonVO.class, Person.class)
                .addMapping(PersonVO::getKey, Person::setId);
    }

    public static <O, D> D parseObject(O origem, Class<D> destination) {
        return mapper.map(origem, destination);
    }

    public static <O, D> List<D> parseListObjects(List<O> origem, Class<D> destination) {
        List<D> destinationObjects = new ArrayList<>();
        origem.forEach(o -> destinationObjects.add(mapper.map(o, destination)));
        return destinationObjects;
    }
}
