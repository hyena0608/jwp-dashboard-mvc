package webmvc.org.springframework.web.servlet.mvc.tobe;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import webmvc.org.springframework.web.servlet.ModelAndView;
import webmvc.org.springframework.web.servlet.exception.HandlerExecutionException;

import java.lang.reflect.Method;

import static java.util.Objects.isNull;

public class HandlerExecution {

    private final Method method;

    public HandlerExecution(final Object controller, final Method method) {
        if (controller == null) {
            throw new HandlerExecutionException("[ERROR] HandlerExecution 를 생성할 때 Object(Controller) 는 null 일 수 없습니다.");
        }
        if (method == null) {
            throw new HandlerExecutionException("[ERROR] HandlerExecution 를 생성할 때 Method 는 null 일 수 없습니다.");
        }

        this.method = method;
    }

    public ModelAndView handle(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final Object controller = method.getDeclaringClass()
                .getDeclaredConstructor()
                .newInstance();

        return (ModelAndView) method.invoke(controller, request, response);
    }
}
