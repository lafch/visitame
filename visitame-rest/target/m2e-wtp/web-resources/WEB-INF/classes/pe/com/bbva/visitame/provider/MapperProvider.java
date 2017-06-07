package pe.com.bbva.visitame.provider;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

@Provider
public class MapperProvider implements ContextResolver<ObjectMapper> {

	public final ObjectMapper mapper;
	
	public MapperProvider() {
		mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
	}
	
	@Override
	public ObjectMapper getContext(Class<?> arg0) {
		return mapper;
	}
}