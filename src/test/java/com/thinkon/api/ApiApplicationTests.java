package com.thinkon.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.With;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import com.thinkon.api.user.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.json.JacksonTester;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ApiApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private JacksonTester<DataCreateUser> createUserJackson;

	@Autowired
	private JacksonTester<DataUpdateUser> updateUserJackson;

private ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;

	@Test
	@DisplayName("POST to create a new user. Expected http 200")
	@WithMockUser

	void inputJSONUserData_outputHTTP200() throws Exception{
		var userData = new DataCreateUser(
				username: "marvin.robot@domain.com",
				firstName:"Marvin",
				lastName: "Robot",
				email: "marvin.robot@domain.com",
				phoneNumber:"6477672430"
		);

		when(userRepository.save(any)).thenReturn(new User(new DataCreateUser());

	}

}
