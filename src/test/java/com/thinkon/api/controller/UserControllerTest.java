    package com.thinkon.api.controller;

    import com.fasterxml.jackson.databind.ObjectMapper;
    import com.thinkon.api.domain.UserRepository;
    import com.thinkon.api.dto.DataCreateUser;
    import com.thinkon.api.domain.model.User;
    import com.thinkon.api.dto.DataUpdateUser;
    import com.thinkon.api.service.UserService;
    import org.junit.jupiter.api.DisplayName;
    import org.junit.jupiter.api.Test;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
    import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
    import org.springframework.boot.test.context.SpringBootTest;
    import org.springframework.boot.test.json.JacksonTester;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.MediaType;
    import org.springframework.mock.web.MockHttpServletResponse;
    import org.springframework.security.test.context.support.WithMockUser;
    import org.springframework.test.web.servlet.MockMvc;

    import java.util.Date;

    import static org.assertj.core.api.Assertions.assertThat;

    import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
    import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

    @SpringBootTest
    @AutoConfigureMockMvc
    @WithMockUser
    @AutoConfigureJsonTesters
    class UserControllerTest {

        @Autowired
        private MockMvc mvc;

        @Autowired
        private JacksonTester<User> userJackson;

        @Autowired
        private JacksonTester<UserService> userServiceJacksonTester;

        @Autowired JacksonTester<UserController> userControllerJacksonJackson;
        @Autowired
        private UserService userService;
        @Autowired
        private UserRepository userRepository;

        @Test
        @DisplayName("Create User data, and response is CREATED Https status")
        @WithMockUser
        void PostValidUser_inputValidUserData_outputHttp201() throws Exception {
            var dateNow = new Date().getTime();
            var stringValue = Long.toString(dateNow);
            // Create the data for the new user
            var dataUser = new DataCreateUser(
                    stringValue + "@webmail.com",
                    stringValue,
                    stringValue,
                    stringValue + "@webmail.com",
                    stringValue
            );

            // Perform the POST request to create the user
            MockHttpServletResponse response = mvc.perform(post("/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(dataUser))) // Convert the object to JSON
                    .andReturn().getResponse();

            // Assert that the response status is 201 CREATED
            assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());


        }
        @Test
        @DisplayName("Update User data, and response is OK Https status")
        @WithMockUser
        void PutValidUser_inputValidUserData_outputHttp200() throws Exception {

            var dateNow = new Date().getTime();
            var stringValue = Long.toString(dateNow);
            Long id = null;
            // Create the data for the new user
            var dataCreateUser = new DataCreateUser(
                    stringValue + "@webmail.com",
                    stringValue,
                    stringValue,
                    stringValue + "@webmail.com",
                    stringValue
            );

            // Perform the POST request to create the user
            MockHttpServletResponse createResponse = mvc.perform(post("/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(dataCreateUser))) // Convert the object to JSON
                    .andReturn().getResponse();

                id = Long.parseLong(createResponse.getContentAsString());

            var dataUser = new DataUpdateUser(
                    id,
                    stringValue  + "updated@webmail.com",
                    stringValue,
                    stringValue,
                    stringValue + "updated@webmail.com",
                    stringValue
            );

            MockHttpServletResponse response = mvc.perform(put("/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(dataUser))) // Convert the object to JSON
                    .andReturn().getResponse();

            // Assert that the response status is 201 CREATED
            assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        }

        @Test
        @DisplayName("Find User data by Id, and response is FOUND Https status")
        @WithMockUser
         void GetUserById_validId_returnsHttpFoundAndUser() throws Exception {
                // Arrange
            var dateNow = new Date().getTime();
            var stringValue = Long.toString(dateNow);
            Long id = null;
            // Create the data for the new user
            var dataCreateUser = new DataCreateUser(
                    stringValue + "@webmail.com",
                    stringValue,
                    stringValue,
                    stringValue + "@webmail.com",
                    stringValue
            );

            // Perform the POST request to create the user
            MockHttpServletResponse createResponse = mvc.perform(post("/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(dataCreateUser))) // Convert the object to JSON
                    .andReturn().getResponse();

            id = Long.parseLong(createResponse.getContentAsString());

                // Act: Perform the GET request with a valid user ID
                MockHttpServletResponse response = mvc.perform(get("/users/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)) // Assert that the response status is 302 FOUND
                        .andReturn().getResponse();

            // Assert that the response status is 201 CREATED
            assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());

        }

        @Test
        @DisplayName("List all User, and response is list of users")
        @WithMockUser
        void GetAllUsers_validId_returnsList() throws Exception {

            // Act: Perform the GET request
            MockHttpServletResponse response = mvc.perform(get("/users")
                            .param("page", "0") // Specify page number
                            .param("size", "15") // Specify page size
                            .contentType("application/json"))
                    .andExpect(status().isOk()) // Assert that the response status is 200 OK
                    .andReturn().getResponse();

            // Assert: Check that the response body is not empty and contains list-like structure
            assertThat(response.getContentAsString()).isNotEmpty(); // Ensure response is not empty
            assertThat(response.getContentAsString()).contains("[", "]"); // Basic check for a list structure

        }

        @Test
        @DisplayName("Delete User data by Id, and response is OK Https status")
        @WithMockUser
        void DeleteUserById_validId_returnsHttpsOK() throws Exception {
            // Arrange
            var dateNow = new Date().getTime();
            var stringValue = Long.toString(dateNow);
            Long id = null;
            // Create the data for the new user
            var dataCreateUser = new DataCreateUser(
                    stringValue + "@webmail.com",
                    stringValue,
                    stringValue,
                    stringValue + "@webmail.com",
                    stringValue
            );

            // Perform the POST request to create the user
            MockHttpServletResponse createResponse = mvc.perform(post("/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(dataCreateUser))) // Convert the object to JSON
                    .andReturn().getResponse();

            id = Long.parseLong(createResponse.getContentAsString());

            // Act: Perform the GET request with a valid user ID
            MockHttpServletResponse response = mvc.perform(delete("/users/{id}", id)
                            .contentType(MediaType.APPLICATION_JSON)) // Assert that the response status is 302 FOUND
                    .andReturn().getResponse();

            // Assert that the response status is 201 CREATED
            assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        }


    }
