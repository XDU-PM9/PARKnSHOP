import com.parknshop.controller.CustomerController;
import com.parknshop.entity.RoleEntity;
import com.parknshop.entity.UserEntity;
import com.parknshop.service.IUserBuilder;
import com.parknshop.service.IUserService;
import com.parknshop.service.serviceImpl.UserBuilder;
import com.parknshop.service.serviceImpl.UserService;
import org.hibernate.service.spi.InjectService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


/**
 * Created by wei on 16-12-1.
 */
public class CustomerControllerTest {
    private InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    private MockMvc mockMvc;
    @Mock
    private IUserService userService;
    @Mock
    private UserBuilder userBuilder;
    @InjectMocks
    private CustomerController customerController=new CustomerController(userService,userBuilder);

    @Before
    public void before() {
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        mockMvc = standaloneSetup(customerController).setViewResolvers(viewResolver).build();
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void login() throws Exception {
        mockMvc.perform(get("/customerLogin")).andExpect(view().name("customerLogin"));
    }

    @Test
    public void customerLogin() throws Exception {
        String json="{\"userName\":s,\"password\":s}";
        mockMvc.perform(post("/customerLogin").characterEncoding("UTF-8").contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(json.getBytes())).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void register() throws Exception {
        mockMvc.perform(get("/customerRegister")).andExpect(view().name("customerRegister"));
    }

    @Test
    public void customerRegister() throws Exception{
    String json="{\"userName\":s,\"password\":s,\"phone\":\"123456\",\"email\":qqq.com}";
    mockMvc.perform(post("/customerRegister").characterEncoding("UTF-8")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(json.getBytes())).andDo(MockMvcResultHandlers.print());
    }
}
