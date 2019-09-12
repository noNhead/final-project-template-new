package com.epam.rd.izh.service;

import com.epam.rd.izh.dto.User;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

/**
 * Данный класс имеет аннотацию {@link SessionScope}.
 *
 * Это значит, что данный бин является сиссионым. То есть он создается каждый раз для новой сессии.
 *
 * Сессия же в свою очередь создается при первом запросе из браузера к приложению.
 * Далее, приложение просит браузер установить специальную куку с помощью которой он будет определять, к какой именно сессии относится запрос.
 *
 * Соответственно, для каждого запроса будет определена сессия и найден конкретный экземпляр сессионного бина.
 */
@SessionScope
@Service
public class UserManager implements IUserManager {

    private User currentUser;

    /**
     * Данный метод возвращает текущего пользователя привязанного к сессии.
     */
    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    @Override
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
