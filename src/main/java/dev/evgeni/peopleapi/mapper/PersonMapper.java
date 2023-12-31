package dev.evgeni.peopleapi.mapper;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import dev.evgeni.peopleapi.model.Person;
import dev.evgeni.peopleapi.web.dto.CreatePersonRequest;
import dev.evgeni.peopleapi.web.dto.UpdatePersonRequest;

@Mapper(uses = {AddressMapper.class})
public abstract class PersonMapper {

        @Mapping(target = "id", ignore = true)
        @Mapping(source = "street", target = "address.street")
        @Mapping(source = "streetNo", target = "address.streetNo")
        @Mapping(source = "gender", target = "gender", defaultValue = "UNKNOWN")
        // @Mapping(target = "egn", expression = "java(formatEgn(req.getEgn()))")
        // See:
        // https://stackoverflow.com/questions/73687687/mapstruct-custom-mapping-function-is-applied-to-all-fields
        @Mapping(source = "egn", target = "egn", qualifiedByName = "egn-formatter")
        public abstract Person personFromCreateRequest(CreatePersonRequest req);

        @Mapping(target = "photos", ignore = true)
        @Mapping(target = "egn", ignore = true)
        @Mapping(target = "firstName",
                        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
        @Mapping(target = "lastName",
                        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
        @Mapping(target = "gender",
                        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
        @Mapping(target = "address",
                        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
        public abstract void updatePersonFromUpdateRequest(UpdatePersonRequest req,
                        @MappingTarget Person person);

        @BeforeMapping
        void updateGenderToUpperCase(CreatePersonRequest req) {
                if (req.getGender() != null) {
                        req.setGender(req.getGender().toUpperCase());
                }
        }

        @Named("egn-formatter")
        String formatEgn(String egn) {
                return "(" + egn + ")";
        }
}
