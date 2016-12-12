/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifc.converter;

import br.com.ifc.model.Times;
import br.com.ifc.negocio.TimeNegocio;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author fabricio
 */
@FacesConverter(forClass = Times.class)
public class TimeConverter implements Converter {

    @Override
    public Times getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            if (value == null) {
                return null;
            }
            TimeNegocio negocio = new TimeNegocio();
            return negocio.getById(Long.parseLong(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Times tipo = (Times) value;
            return Long.toString(tipo.getId());
        } else {
            return "";
        }
    }
}
