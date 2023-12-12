package dev.evgeni.peopleapi.json;

import static org.assertj.core.api.Assertions.assertThat;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import dev.evgeni.peopleapi.web.dto.CreatePersonRequest;

@JsonTest
public class PersonSerializationTest {

    @Autowired
    private JacksonTester<CreatePersonRequest> cpReqTester;

    @Test
    void serializeCreatePersonRequest() throws IOException {
        CreatePersonRequest req = CreatePersonRequest.builder().firstName("Ivan").egn("8888888888")
                .street("Moskovska").streetNo(5).build();

        JsonContent<CreatePersonRequest> json = cpReqTester.write(req);

        assertThat(json).extractingJsonPathStringValue("$.street").isEqualTo("Moskovska");
    }

}
