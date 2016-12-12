/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifc.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author fabricio
 */
public class MessageUtil {

    private static FacesMessage message;
    private static String campo = "form:avisos";
    private static String SAVE = "Salvo";
    private static String ERROR = "Erro";

    public MessageUtil() {
    }

    public static void addMessage(String id, String tipo, String mensagem) {
        try {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, tipo, mensagem);
            FacesContext.getCurrentInstance().addMessage(id, message);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            message = null;
        }
    }

    public static void addMessageError(String id, String tipo, String mensagem) {
        try {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, tipo, mensagem);
            FacesContext.getCurrentInstance().addMessage(id, message);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            message = null;
        }
    }

    public static void addSaveMessagePadrao() {
        MessageUtil.addMessage(campo, SAVE, "Salvo com Sucesso!");
    }

    public static void addSaveMessageErro() {
        MessageUtil.addMessageError(campo, SAVE, "Erro ao Salvar!");
    }

    public static void addDeleteMessagePadrao() {
        MessageUtil.addMessage(campo, ERROR, "Removido com Sucesso!");
    }

    public static void addDeleteMessageErro() {
        MessageUtil.addMessageError(campo, ERROR, "Não foi possível remover o registro!");
    }
}
