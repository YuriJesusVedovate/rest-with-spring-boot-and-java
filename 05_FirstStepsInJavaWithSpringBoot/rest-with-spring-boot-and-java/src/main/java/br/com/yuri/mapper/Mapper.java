package br.com.yuri.mapper;


// import com.github.dozermapper.core.DozerBeanMapperBuilder;
// import com.github.dozermapper.core.Mapper;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    //private static final Mapper mapper = DozerBeanMapperBuilder.buildDefault();
    private static final ModelMapper mapper = new ModelMapper();

    public static <O, D> D parseObject(O origem, Class<D> destination) {
        return mapper.map(origem, destination);
    }

    public static <O, D> List<D> parseListObjects(List<O> origem, Class<D> destination) {
        List<D> destinationObjects = new ArrayList<>();
        origem.forEach(o -> destinationObjects.add(mapper.map(o, destination)));
        return destinationObjects;
    }
}
