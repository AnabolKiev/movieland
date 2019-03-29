package com.anabol.movieland.web.controller;

import com.anabol.movieland.dao.CurrencyDao;
import com.anabol.movieland.web.utils.Currency;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testCurrencyContext.xml", "file:src/main/webapp/WEB-INF/action-servlet.xml",
        "file:src/main/webapp/WEB-INF/applicationContext.xml"})
@WebAppConfiguration
public class MovieControllerITest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private CurrencyDao currencyDao;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetById() throws Exception {
        when(currencyDao.getRate(Currency.UAH)).thenReturn(1.00);

        mockMvc.perform(get("/movie/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.nameRussian", is("Побег из Шоушенка")))
                .andExpect(jsonPath("$.nameNative", is("The Shawshank Redemption")))
                .andExpect(jsonPath("$.description", is("Успешный банкир Энди Дюфрейн обвинен в убийстве собственной " +
                        "жены и ее любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью " +
                        "и беззаконием, царящими по обе стороны решетки. Каждый, кто попадает в эти стены, становится " +
                        "их рабом до конца жизни. Но Энди, вооруженный живым умом и доброй душой, отказывается мириться " +
                        "с приговором судьбы и начинает разрабатывать невероятно дерзкий план своего освобождения.")))
                .andExpect(jsonPath("$.yearOfRelease", is("1994")))
                .andExpect(jsonPath("$.rating", is(8.89)))
                .andExpect(jsonPath("$.price", is(123.45)))
                .andExpect(jsonPath("$.picturePath", is("https://images-na.ssl-images-amazon.com/images/M/MV5BODU4Mj" +
                        "U4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg")))
                .andExpect(jsonPath("$.countries", hasSize(1)))
                .andExpect(jsonPath("$.countries[0].id", is(1)))
                .andExpect(jsonPath("$.countries[0].name", is("США")))
                .andExpect(jsonPath("$.genres", hasSize(2)))
                .andExpect(jsonPath("$.genres[0].id", is(1)))
                .andExpect(jsonPath("$.genres[0].name", is("драма")))
                .andExpect(jsonPath("$.genres[1].id", is(2)))
                .andExpect(jsonPath("$.genres[1].name", is("криминал")))
                .andExpect(jsonPath("$.reviews", hasSize(2)))
                .andExpect(jsonPath("$.reviews[0].id", is(1)))
                .andExpect(jsonPath("$.reviews[0].user.id", is(1)))
                .andExpect(jsonPath("$.reviews[0].user.nickName", is("Дарлин Эдвардс")))
                .andExpect(jsonPath("$.reviews[0].text", is("Гениальное кино! Смотришь и думаешь «Так не бывает!», " +
                        "но позже понимаешь, что только так и должно быть. Начинаешь заново осмысливать значение фразы," +
                        " которую постоянно используешь в своей жизни, «Надежда умирает последней». Ведь если ты не " +
                        "надеешься, то все в твоей жизни гаснет, не остается смысла. Фильм наполнен бесконечным числом " +
                        "правильных афоризмов. Я уверена, что буду пересматривать его сотни раз.")))
                .andExpect(jsonPath("$.reviews[1].id", is(2)))
                .andExpect(jsonPath("$.reviews[1].user.id", is(2)))
                .andExpect(jsonPath("$.reviews[1].user.nickName", is("Габриэль Джексон")))
                .andExpect(jsonPath("$.reviews[1].text", is("Кино это является, безусловно, «со знаком качества». " +
                        "Что же до первого места в рейтинге, то, думаю, здесь имело место быть выставление «десяточек» " +
                        "от большинства зрителей вкупе с раздутыми восторженными откликами кинокритиков. Фильм " +
                        "атмосферный. Он драматичный. И, конечно, заслуживает того, чтобы находиться довольно " +
                        "высоко в мировом кинематографе.")));
    }

    @Test
    public void testGetByIdAndCurrency() throws Exception {
        when(currencyDao.getRate(Currency.UAH)).thenReturn(1.00);
        when(currencyDao.getRate(Currency.USD)).thenReturn(27.15);

        mockMvc.perform(get("/movie/1?currency=usd"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.nameRussian", is("Побег из Шоушенка")))
                .andExpect(jsonPath("$.nameNative", is("The Shawshank Redemption")))
                .andExpect(jsonPath("$.description", is("Успешный банкир Энди Дюфрейн обвинен в убийстве собственной " +
                        "жены и ее любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью " +
                        "и беззаконием, царящими по обе стороны решетки. Каждый, кто попадает в эти стены, становится " +
                        "их рабом до конца жизни. Но Энди, вооруженный живым умом и доброй душой, отказывается мириться " +
                        "с приговором судьбы и начинает разрабатывать невероятно дерзкий план своего освобождения.")))
                .andExpect(jsonPath("$.yearOfRelease", is("1994")))
                .andExpect(jsonPath("$.rating", is(8.89)))
                .andExpect(jsonPath("$.price", is(4.55)))
                .andExpect(jsonPath("$.picturePath", is("https://images-na.ssl-images-amazon.com/images/M/MV5BODU4Mj" +
                        "U4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg")))
                .andExpect(jsonPath("$.countries", hasSize(1)))
                .andExpect(jsonPath("$.countries[0].id", is(1)))
                .andExpect(jsonPath("$.countries[0].name", is("США")))
                .andExpect(jsonPath("$.genres", hasSize(2)))
                .andExpect(jsonPath("$.genres[0].id", is(1)))
                .andExpect(jsonPath("$.genres[0].name", is("драма")))
                .andExpect(jsonPath("$.genres[1].id", is(2)))
                .andExpect(jsonPath("$.genres[1].name", is("криминал")))
                .andExpect(jsonPath("$.reviews", hasSize(2)))
                .andExpect(jsonPath("$.reviews[0].id", is(1)))
                .andExpect(jsonPath("$.reviews[0].user.id", is(1)))
                .andExpect(jsonPath("$.reviews[0].user.nickName", is("Дарлин Эдвардс")))
                .andExpect(jsonPath("$.reviews[0].text", is("Гениальное кино! Смотришь и думаешь «Так не бывает!», " +
                        "но позже понимаешь, что только так и должно быть. Начинаешь заново осмысливать значение фразы," +
                        " которую постоянно используешь в своей жизни, «Надежда умирает последней». Ведь если ты не " +
                        "надеешься, то все в твоей жизни гаснет, не остается смысла. Фильм наполнен бесконечным числом " +
                        "правильных афоризмов. Я уверена, что буду пересматривать его сотни раз.")))
                .andExpect(jsonPath("$.reviews[1].id", is(2)))
                .andExpect(jsonPath("$.reviews[1].user.id", is(2)))
                .andExpect(jsonPath("$.reviews[1].user.nickName", is("Габриэль Джексон")))
                .andExpect(jsonPath("$.reviews[1].text", is("Кино это является, безусловно, «со знаком качества». " +
                        "Что же до первого места в рейтинге, то, думаю, здесь имело место быть выставление «десяточек» " +
                        "от большинства зрителей вкупе с раздутыми восторженными откликами кинокритиков. Фильм " +
                        "атмосферный. Он драматичный. И, конечно, заслуживает того, чтобы находиться довольно " +
                        "высоко в мировом кинематографе.")));
    }
}