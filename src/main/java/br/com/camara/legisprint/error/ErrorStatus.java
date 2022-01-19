package br.com.camara.legisprint.error;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public class ErrorStatus implements ErrorViewResolver {
    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {

        ModelAndView modelAndView = new ModelAndView("error");

        modelAndView.addObject("status", status.value());

        switch (status.value()){
            case 404:
                modelAndView.addObject("error", "Página não encontrada.");
                modelAndView.addObject("message", "Por favor, verifique o caminho da página solicitada.");
                break;
            case 500:
                modelAndView.addObject("error", "Erro interno no servidor.");
                modelAndView.addObject("message", "O servidor não conseguiu processar a sua solicitação.");
                break;
            case 503:
                modelAndView.addObject("error", "Permissão negada.");
                modelAndView.addObject("message", "Por favor, verifique se você está logado ou se possui permissão.");
                break;
            default:
                modelAndView.addObject("error", model.get("error"));
                modelAndView.addObject("message", model.get("message"));
                break;
        }
        return modelAndView;
    }
}
