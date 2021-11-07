package com.learn.chapter08;

import com.alibaba.fastjson.JSONObject;
import com.learn.chapter08.config.MyBatisConfig;
import com.learn.chapter08.config.SpringMvcConfig;
import com.learn.chapter08.controller.HomeController;
import com.learn.chapter08.controller.RoleController;
import com.learn.chapter08.dao.mapper.RoleMapper;
import com.learn.chapter08.dao.pojo.Role;
import com.learn.chapter08.service.RoleService;
import org.apache.ibatis.session.RowBounds;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * 测试 RoleController
 *
 * MockMvc control 层单元测试 参数传递问题：https://www.cnblogs.com/jpfss/p/10966481.html
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringMvcConfig.class, MyBatisConfig.class})
public class RoleControllerTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        //1.初始化
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleService roleService;

    @Test
    public void testRoleMapper() {
        Role one = roleMapper.findOne(2);
        System.out.println(one);

        Role role = new Role();
        role.setRoleName("新增");
        role.setNote("用户新增");
        roleMapper.insert(role);

        List<Role> list = roleMapper.list("新增", new RowBounds(0, 10));
        list.forEach(System.out::println);
    }


    @Test
    public void testRoleService() {
        Role one = roleService.findOne(2);
        System.out.println(one);

        Role role = new Role();
        role.setRoleName("新增");
        role.setNote("用户新增");
        roleService.insert(role);

        List<Role> list = roleService.list("新增", 0, 10);
        list.forEach(System.out::println);
    }

    @Autowired
    private RoleController roleController;

    @Test
    public void testRoleController() throws Exception {
        MvcResult mvcResult = null;
        MockMvc mockMvc = standaloneSetup(roleController).build();
        mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/role")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "19"))
                .andExpect(status().isOk())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String contentAsString = mvcResult.getResponse().getContentAsString();

        Assert.assertNotNull(contentAsString);
        Assert.assertEquals(contentAsString,"{\"id\":19,\"roleName\":\"新增\",\"note\":\"用户新增\"}");
        System.out.println(contentAsString);
        System.out.println("返回状态码：" + status);
    } /* Output:
        {"id":19,"roleName":"新增","note":"用户新增"}
        返回状态码：200
    *///~


    /*
     * https://blog.csdn.net/q957967519/article/details/88415507
     * https://www.cnblogs.com/lyy-2016/p/6122144.html
     *
     * 1.MockMvcRequestBuilders主要API
     *
     *  get(String urlTemplate, Object... urlVariables)：根据uri模板和uri变量值得到一个GET请求方式的MockHttpServletRequestBuilder；如get(/user/{id}, 1L)；
     *  post(String urlTemplate, Object... urlVariables)：POST方法；
     *  put(String urlTemplate, Object... urlVariables)：PUT方法；
     *  delete(String urlTemplate, Object... urlVariables) ：DELETE方法；
     *  options(String urlTemplate, Object... urlVariables)：OPTIONS方法；
     *  request(HttpMethod httpMethod, String urlTemplate, Object... urlVariables)： 提供自己的Http请求方法及uri模板和uri变量，如上API都是委托给这个API；
     * MockMultipartHttpServletRequestBuilder fileUpload(String urlTemplate, Object... urlVariables)：提供文件上传方式的请求，得到MockMultipartHttpServletRequestBuilder；
     * RequestBuilder asyncDispatch(final MvcResult mvcResult)：创建一个从启动异步处理的请求的MvcResult进行异步分派的RequestBuilder
     */
    @Test
    public void testControllerInsertRole() throws Exception {

        String url = "/role/save";

        Role role = new Role();
        role.setRoleName("新增2");
        role.setNote("用户新增2");
        String requestJson = JSONObject.toJSONString(role);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson.getBytes())
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print()) //打印调试结果到控制台
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        System.out.println("返回状态码：" + status);

        String contentAsString = mvcResult.getResponse().getContentAsString();
        Assert.assertNotNull(contentAsString);
        System.out.println(contentAsString);
    }

    @Test
    public void testHomePage() throws Exception {
        HomeController controller = new HomeController();
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(get("/"))
                .andExpect(status().isOk()) // 是否返回状态200
                .andExpect(view().name("home")); // 是否返回视图“home”
    }
}
